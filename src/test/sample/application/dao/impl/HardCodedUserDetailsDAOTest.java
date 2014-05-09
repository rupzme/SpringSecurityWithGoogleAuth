package sample.application.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(JUnit4.class)
public class HardCodedUserDetailsDAOTest {

	private HardCodedUserDetailsDAO hardCodedUserDetailsDAO;
	
	@Before
	public void setup(){
		
		String[] noRoles = new String[0];
		String[] userRoles = new String[]{"ROLE_USER"};
		String[] managerRoles = new String[]{"ROLE_USER", "ROLE_MANAGER"};

		HashMap<String, String[]>users = new HashMap<String, String[]>();
		users.put("test.noroles@gmail.com", noRoles);
		users.put("test.user@gmail.com", userRoles);
		users.put("test.manager@gmail.com", managerRoles);
		
		hardCodedUserDetailsDAO = new HardCodedUserDetailsDAO(users);
	}
	
	@Test
	public void testRetrieveUserEmptyAndNull(){		
		assertEquals(hardCodedUserDetailsDAO.retrieveUser(null), null);
		assertEquals(hardCodedUserDetailsDAO.retrieveUser(""), null);
	}
	
	@Test
	public void testRetrieveUserNoRoles(){		
		UserDetails userNoRoles = hardCodedUserDetailsDAO.retrieveUser("test.noroles@gmail.com"); 
		assertEquals(userNoRoles.getUsername(),"test.noroles@gmail.com");
		assertEquals(userNoRoles.getAuthorities().size(), 0);
	}
	
	@Test
	public void testRetrievedUserHasJustTheUserRole(){
		UserDetails standardUser = hardCodedUserDetailsDAO.retrieveUser("test.user@gmail.com"); 
		assertEquals(standardUser.getUsername(),"test.user@gmail.com");
		assertEquals(1, standardUser.getAuthorities().size());
		assertTrue(standardUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
	}
	
	@Test
	public void testRetrievedManagerHasBothUserAndManagerRoles(){
		UserDetails managerUser = hardCodedUserDetailsDAO.retrieveUser("test.manager@gmail.com"); 
		assertEquals(managerUser.getUsername(),"test.manager@gmail.com");
		assertEquals(2, managerUser.getAuthorities().size());
		assertTrue(managerUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
		assertTrue(managerUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MANAGER")));
	}
	
}
