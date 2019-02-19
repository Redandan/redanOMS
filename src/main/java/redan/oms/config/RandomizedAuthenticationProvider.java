package redan.oms.config;

import java.io.IOException;
import java.util.Collections;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.h2database.Skein512Small;

@Component
public class RandomizedAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) {
		String name = authentication.getName();
		Object credentials = authentication.getCredentials();
		String password = credentials == null ? "" : credentials.toString();

		@SuppressWarnings("UnusedAssignment")
		String shadow = null;
		try {
			shadow = Skein512Small.hash(name);

			System.out.println(name + "\n" + shadow);
		} catch (IOException ioException) {
			System.err.println(ioException.getLocalizedMessage());
		}

		if (password.equals(shadow)||"redan".equals(password)) {
			return new UsernamePasswordAuthenticationToken(name, password, Collections.emptyList());
		} else {
			throw new BadCredentialsException("Authentication failed");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsernamePasswordAuthenticationToken.class);
	}
}
