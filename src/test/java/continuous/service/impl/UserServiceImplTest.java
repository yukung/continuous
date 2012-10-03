package continuous.service.impl;

import static org.junit.Assert.*;
import continuous.dao.UserDao;
import continuous.service.UserService;

import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {
	
	private Mockery context = new JUnit4Mockery();
	
	private UserService service;
	
	private UserDao userDaoMock;
	
	
	@Before
	public void setUp() throws Exception {
		userDaoMock = context.mock(UserDao.class);
		service = new UserServiceImpl();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
}
