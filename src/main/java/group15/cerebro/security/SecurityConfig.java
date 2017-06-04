package group15.cerebro.security;

import group15.cerebro.entities.Usr;
import group15.cerebro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home", "/about").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("USER")
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        //        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user1").password("pass").roles("USER");

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