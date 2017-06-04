package group15.cerebro;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserRepository userRepository;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer
				= auth.inMemoryAuthentication();

		List<Usr> users = userRepository.findAll();
		for (Usr user : users) {
			configurer = configurer
					.withUser(user.getLogin())
					.password(user.getPassword())
					.roles("USER").and();
		}
	}
}
