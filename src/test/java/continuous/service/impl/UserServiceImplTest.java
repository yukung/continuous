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
	
	/**
	 * TODO for yukung
	 *
	 * @throws Exception 例外が発生した場合
	 */
	@Test
	public void testRemove() throws Exception {
		final User user = new User();
		user.setId(1L);
		context.checking(new Expectations() {
			
			{
				oneOf(mock).delete(user);
				will(returnValue(true));
			}
		});
		assertThat(service.remove(user), is(true));
	}
	
	@Test
	public void testStore() {
		final User user = new User();
		user.setName("Test User1");
		user.setEmail("test@continuous.com");
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
	
	@Test
	public void testUpdate() throws Exception {
		final User user = new User();
		user.setId(1L);
		user.setEmail("updated@continuous.com");
		context.checking(new Expectations() {
			
			{
				oneOf(mock).update(user);
				will(returnValue(true));
			}
		});
		assertThat(service.update(user), is(true));
	}
}
