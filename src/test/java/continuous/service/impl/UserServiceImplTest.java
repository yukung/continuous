package continuous.service.impl;

import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dao.UserDao;
import continuous.dto.IndexDto;
import continuous.entity.Achievement;
import continuous.entity.Practice;
import continuous.entity.User;
import continuous.service.UserService;
import continuous.util.DateUtils;
import continuous.util.DateUtils.Period;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class UserServiceImplTest {

	private static final String username = "testuser1";

	private static final long userId = 1L;

	private static final long achievementId = 2L;

	private Mockery context = new JUnit4Mockery();

	private UserService service;

	private UserDao userDaoMock;

	private AchievementDao achievementDaoMock;

	private PracticeDao practiceDaoMock;


	@Before
	public void setUp() throws Exception {
		userDaoMock = context.mock(UserDao.class);
		achievementDaoMock = context.mock(AchievementDao.class);
		practiceDaoMock = context.mock(PracticeDao.class);
		service = new UserServiceImpl(userDaoMock, achievementDaoMock, practiceDaoMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSummary() {
		context.checking(new Expectations() {

			{
				allowing(userDaoMock).selectByUserName(username);
				User user = new User();
				user.setId(userId);
				user.setName(username);
				will(returnValue(user));
			}
			{
				allowing(achievementDaoMock).selectByUserId(userId);
				Achievement achievement = new Achievement();
				achievement.setId(achievementId);
				achievement.setUserId(userId);
				achievement.setName("断食");
				will(returnValue(achievement));
			}
			{
				Period current = DateUtils.currentPeriod();
				allowing(practiceDaoMock).selectRangeToPracticedOn(current.getFrom(), current.getTo());
				will(returnValue(new Practice()));
			}
		});

		IndexDto summary = service.getSummary(username);
//		assertThat(summary.getUser(), null);
//		assertThat(summary.getAchievement().getName(), null);
//		List<Date> daysOfMonth = new ArrayList<Date>();
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH) + 1;
//		int actualMaximum = cal.getActualMaximum(Calendar.DATE);
//		cal.clear();
//		cal.set(year, month - 1, 1);
//		for (int i = 0; i < actualMaximum; i++) {
//			daysOfMonth.add(cal.getTime());
//			cal.add(Calendar.DATE, 1);
//		}
//		for (Date date : daysOfMonth) {
//			assertThat(summary.getStatus().get(date), is(notNullValue()));
//		}
	}
}
