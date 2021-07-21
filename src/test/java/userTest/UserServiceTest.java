package userTest;

import static org.junit.Assert.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.models.User;
import com.examples.services.UserServices;
import org.junit.Test;

public class UserServiceTest {

	
	
		
			@InjectMocks
			public UserServices uServ;
			
			@Mock
			public UserDao uDao;
			
			@Before
			public void initMocks() {
				MockitoAnnotations.initMocks(this);
			}
			
			@Test
			public void testValidLogin() {
				User u1 = new User(1, "test", "user", "testuser", "test@email.com", "1111111111", "testpass");
				User not = new User(0, "test", "user", "testuser", "test@mail.com", "1111111111", "testpass");
				
				when(uDao.getUserByUsername(any())).thenReturn(u1);
				
				User loggedIn = uServ.signIn("testuser", "testpass");
				
				assertEquals(u1.getId(), loggedIn.getId());
			}
			
			@Test(expected = UserDoesNotExistException.class)
			public void testInvalidUsername() {
				User u1 = new User(1, "test", "user", "testuser", "test@email.com", "1111111111","testpass");
				User not = new User(0, "test", "user", "testuser", "test@mail.com","1111111111",  "testpass");
				
				when(uDao.getUserByUsername(any())).thenReturn(not);
				
				User loggedIn = uServ.signIn("testuser", "testpass");
			}
			
			@Test(expected = InvalidCredentialsException.class)
			public void testInvalidPassword() {
				User u1 = new User(1, "test", "user", "testuser", "test@email.com", "1111111111", "testpass");
				User not = new User(1, "test", "user", "testuser", "test@mail.com", "1111111111", "wrongpass");
				
				when(uDao.getUserByUsername(any())).thenReturn(not);
				
				uServ.signIn("testuser", "testpass");
			}
	}

