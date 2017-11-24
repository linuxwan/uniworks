/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.security;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Park Chungwan
 *
 */
public class PasswordEncoderTest {
	Logger logger = LoggerFactory.getLogger(PasswordEncoderTest.class);
	
	@Test
	public void testPasswordEncoderGenerator() {
		int i = 0;
		while (i < 10) {
			String password = "sksahffk";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);
			
			logger.debug(hashedPassword);
			i++;
		}
	}
}
