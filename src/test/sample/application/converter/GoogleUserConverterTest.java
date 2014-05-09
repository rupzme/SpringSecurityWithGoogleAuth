package sample.application.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.security.core.userdetails.UserDetails;

@RunWith(JUnit4.class)
public class GoogleUserConverterTest {

	@Test
	public void testConvert() throws Exception {

		String jsonGoogleUser = "{\"id\": \"12345\",\"email\": \"john.dinger@gmail.com\","
				+ "\"verified_email\": true,\"name\": \"John Dinger\",\"given_name\": \"John\",\"family_name\": "
				+ "\"Dinger\",\"link\": \"https://plus.google.com/12345\",\"picture\":"
				+ " \"https://lh4.googleusercontent.com/photo.jpg\","
				+ "\"gender\": \"male\",\"locale\": \"en\"}";

		GoogleUserConverter googleUserConverter = new GoogleUserConverter();
		UserDetails googleUser = googleUserConverter.convert(jsonGoogleUser);

		assertEquals(googleUser.getUsername(), "john.dinger@gmail.com");
	}

}
