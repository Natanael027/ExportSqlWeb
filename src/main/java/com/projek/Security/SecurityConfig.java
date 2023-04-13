package com.projek.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, 'ROLE_ADMIN' from users where username=?")
		;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.rememberMe().key("AbcDefgKLDSLmvop_0123456789")
				.tokenValiditySeconds(7 * 24 * 60 * 60)  // 7 days 24 hours 60 minutes 60 seconds -> 7days ;
		;
//        http.sessionManagement().in
	}
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/Audio/**", "/js/**", "/webjars/**");
	}

/*
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http)
			throws Exception {
		http.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/login")
				.permitAll()
				.and()
				.logout().permitAll();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/Audio/**", "/webjars/**");
	}
*/
}
