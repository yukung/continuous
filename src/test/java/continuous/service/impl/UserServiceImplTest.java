package continuous.service.impl;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import continuous.dto.SummaryDto;
import continuous.service.UserService;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetSummary() throws Exception {
		UserService userService = new UserServiceImpl();
		SummaryDto summary = userService.getSummary("yukung");
		assertThat(summary, is(nullValue()));
	}
}
