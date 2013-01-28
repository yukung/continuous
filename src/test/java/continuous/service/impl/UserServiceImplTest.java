package continuous.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import continuous.dao.UserDao;
import continuous.entity.User;
import continuous.service.UserService;

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
	
	private Mockery context = new JUnit4Mockery();
	
	private UserService service;
	
	private UserDao mock;
	
	
	@Before
	public void setUp() {
		mock = context.mock(UserDao.class);
		service = new UserServiceImpl(mock);
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testFind() {
		final User user = new User();
		context.checking(new Expectations() {
			
			{
				oneOf(mock).find(0L);
				will(returnValue(user));
			}
		});
		assertThat(service.find(0L), is(user));
	}
}
