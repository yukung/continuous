package continuous.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import continuous.dao.UserDao;
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
	
	private UserDao mock;
	
	
	@Before
	public void setUp() throws Exception {
		mock = context.mock(UserDao.class);
		service = new UserServiceImpl(mock);
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFindByUserName() {
		context.checking(new Expectations() {
			
			{
				allowing(mock).selectById(1L);
				User user = new User();
				user.setId(1L);
				user.setName("testuser");
				will(returnValue(user));
			}
		});
		assertThat(service.findByUserName("testuser").getId(), is(1L));
	}
	
}
