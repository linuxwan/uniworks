/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.uniworks.groupware.common.util.ApplicationConfigReader;
import org.uniworks.groupware.service.AuthenticationService;

/**
 * @author Park Chungwan
 *
 */
@Configuration
@EnableWebSecurity
@PropertySource(value = "classpath:application.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger("SecurityConfig");
	
	@Autowired
	AuthenticationService authenticationService;	
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {	
		auth.userDetailsService(authenticationService).passwordEncoder(passwordencoder());
	}
   
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(authenticationService).passwordEncoder(passwordencoder());
	}	
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//CharSet 설정
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
    	String charSet = ApplicationConfigReader.get("charset");
    	filter.setEncoding(charSet);
    	filter.setForceEncoding(true);
    	http.addFilterBefore(filter, CsrfFilter.class);    
    	
    	//iframe 사용을 허용하도록 변경.
    	http    
			.csrf()
			.and()
			.headers().frameOptions().sameOrigin() 
			//.headers().frameOptions().disable() //iframe 사용을 허용하도록 변경.
			.and()    		
    		.authorizeRequests()    	
    			.antMatchers("/loginForm").access("permitAll")
    			.antMatchers("/download").access("permitAll")
    			.antMatchers("/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_CHRG')")
    		.and()
    			.formLogin().loginPage("/loginForm").loginProcessingUrl("/loginForm").defaultSuccessUrl("/main", true).failureUrl("/loginForm?error")
    			.usernameParameter("username").passwordParameter("password")
    		.and()
    			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/loginForm?logout")
    			.deleteCookies("JSESSIONID").invalidateHttpSession(true)
    		.and()
    			.exceptionHandling().accessDeniedPage("/403")
    		.and()
    			.httpBasic()
    		.and()
    			.csrf();
    	
    	super.configure(http);
    }
	
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
    	return new BCryptPasswordEncoder();
    }
}
