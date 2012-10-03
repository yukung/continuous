package continuous.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import continuous.dao.AchievementDao;
import continuous.dao.PracticeDao;
import continuous.dao.UserDao;
import continuous.entity.Achievement;
import continuous.entity.User;
import continuous.service.impl.UserServiceImpl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMock.class)
public class UserServiceTest {
	
	private Mockery context = new JUnit4Mockery();
	
	private UserService service;
	
	private UserDao userDaoMock;
	
	private AchievementDao achievementDaoMock;
	
	private PracticeDao prcticeDaoMock;
	
	
	@Before
	public void setUp() throws Exception {
		userDaoMock = context.mock(UserDao.class);
		achievementDaoMock = context.mock(AchievementDao.class);
		service = new UserServiceImpl();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindByUserName() {
		// モックでテスト
		final long userId = 1L;
		context.checking(new Expectations() {
			
			{
				allowing(userDaoMock).selectByUserName("testuser1");
				User user = new User();
				user.setId(userId);
				user.setName("testuser1");
				will(returnValue(user));
			}
			{
				allowing(achievementDaoMock).selectByUserId(userId);
				Achievement achievement = new Achievement();
				achievement.setId(1L);
				achievement.setUserId(userId);
				achievement.setName("目標だぜ");
				will(returnValue(achievement));
			}
		});
		assertThat(service.getSummary("testuser1"), is(notNullValue()));
		assertThat(service.getSummary("testuser1").getUser().getId(), is(1L));
	}
	
}
