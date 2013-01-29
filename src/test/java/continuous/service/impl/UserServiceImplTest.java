package continuous.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Date;

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
	
	@Test
	public void testSave() {
		final User user = new User();
		user.setName("Test User1");
		user.setEmail("test@continuos.com");
		user.setPassword("password");
		user.setAboutMe("テストユーザーです。");
		Date date = new Date();
		user.setCreatedAt(date);
		user.setUpdatedAt(date);
		
		context.checking(new Expectations() {
			
			{
				oneOf(mock).insert(user);
				will(returnValue(1L));
			}
		});
		assertThat(service.store(user), is(1L));
	}
}
