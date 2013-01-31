package continuous.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dto.Summary;
import continuous.entity.Achievement;
import continuous.entity.Practice;
import continuous.service.SummariesService;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class SummariesServiceImplTest {
	
	private Mockery context = new JUnit4Mockery();
	
	private SummariesService service;
	
	private AchievementDao achievementDaoMock;
	
	private PracticeDao practiceDaoMock;
	
	
	@Before
	public void setUp() throws Exception {
		achievementDaoMock = context.mock(AchievementDao.class);
		practiceDaoMock = context.mock(PracticeDao.class);
		service = new SummariesServiceImpl(achievementDaoMock, practiceDaoMock);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSummarize() throws Exception {
		// Achievement の戻り値
		final Achievement achievement = new Achievement();
		achievement.setId(100L);
		achievement.setUserId(1L);
		achievement.setName("目標が入っている");
		Date date = new SimpleDateFormat("yyyy/MM/dd").parse("2013/01/01");
		achievement.setCreatedAt(date);
		achievement.setUpdatedAt(date);
		
		final long userId = 1L;
		// AchievementDao のモック定義
		context.checking(new Expectations() {
			
			{
				oneOf(achievementDaoMock).findByUserId(userId);
				will(returnValue(achievement));
			}
		});
		
		// Practice の戻り値
		List<Practice> list = new ArrayList<Practice>();
		// 1ヶ月分のデータ作成
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.SECOND);
//		System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(calendar.getTime()));
		for (int i = 0; i < calendar.getActualMaximum(Calendar.DATE); i++) {
			Practice practice = new Practice();
			practice.setId(Long.valueOf(i * 10000)); // 他のIDと区別しやすいように増やしてるだけ
			practice.setUserId(userId);
			practice.setAchievementId(achievement.getId());
			practice.setPracticedOn((i % 2 == 0) ? new Date() : null);
			practice.setCreatedAt(calendar.getTime());
			practice.setUpdatedAt(calendar.getTime());
			list.add(practice);
			calendar.add(Calendar.DATE, 1);
		}
		final List<Practice> practices = Collections.unmodifiableList(list);
		
		// PracticeDao のモック定義
		context.checking(new Expectations() {
			
			{
				
				oneOf(practiceDaoMock).findByRange(with(equal(userId)), with(equal(achievement.getId())),
						with(aNonNull(Date.class)), with(aNonNull(Date.class)));
				will(returnValue(practices));
			}
		});
		
		Summary summarize = service.summarize(userId);
		assertThat(summarize, is(notNullValue(Summary.class)));
	}
}
