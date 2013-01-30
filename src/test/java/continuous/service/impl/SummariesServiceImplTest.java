package continuous.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
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
		final long userId = 1L;
		final Achievement achievement = new Achievement();
		achievement.setId(10L);
		achievement.setUserId(1L);
		achievement.setName("毎日ジョギングする！");
		Date today = new Date();
		achievement.setCreatedAt(today);
		achievement.setUpdatedAt(today);
		
		context.checking(new Expectations() {
			
//			{
//				oneOf(achievementDaoMock).findByUserId(userId);
//				will(returnValue(achievement));
//			}
		});
		List<Practice> practices = new ArrayList<Practice>();
		for (int i = 0; i < 31; i++) {
			Practice practice = new Practice();
			practice.setId((long) i);
			practice.setUserId(userId);
			practice.setAchievementId(achievement.getId());
			practice.setPracticedOn((i % 2 == 0) ? new Date() : null);
			practice.setCreatedAt(new Date());
			practice.setUpdatedAt(new Date());
			practices.add(practice);
		}
		final List<Practice> result = Collections.unmodifiableList(practices);
		Calendar cal = Calendar.getInstance();
		// 月初
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		final Date from = cal.getTime();
		// 月末
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		final Date to = cal.getTime();
		
		context.checking(new Expectations() {
			
			{
				
				oneOf(achievementDaoMock).findByUserId(userId);
				will(returnValue(achievement));
				// 以下は引数を任意指定する例
				oneOf(practiceDaoMock).findByRange(with(equal(userId)), with(any(Date.class)), with(any(Date.class)));
//				will(returnValue(result));
			}
		});
		
		assertThat(service.summarize(userId), is(notNullValue()));
	}
}
