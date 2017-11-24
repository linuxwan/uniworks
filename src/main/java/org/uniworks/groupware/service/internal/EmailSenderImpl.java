/**
 * 박충완(gomoosin)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.uniworks.groupware.service.EmailSender;

/**
 * @author gomoosin
 *
 */
@Service
public class EmailSenderImpl implements EmailSender {
	private static final Logger logger = LoggerFactory.getLogger(EmailSenderImpl.class);
	@Autowired JavaMailSender javaMailSender;
	
	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.EmailSender#emailSender(java.lang.Object)
	 */
	@Override
	public <T> boolean emailSender(T report) {
		// TODO Auto-generated method stub
		logger.debug("Sending report by email....");
		boolean retVal = false;
		
		try {
            final String emailTo = "chungwan.park@gmail.com";
            final String emailFrom = "linuxwan@naver.com";
            final String subject = "test subject";
            final String message = (String) report;

            javaMailSender.send(new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage paramMimeMessage) throws Exception {
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(paramMimeMessage, true, "UTF-8");

                    mimeMessageHelper.setTo(emailTo);
                    mimeMessageHelper.setFrom(emailFrom);
                    mimeMessageHelper.setSubject(subject);
                    mimeMessageHelper.setText(message);

                    /*
                    final File file = new File("test filename");

                    mimeMessageHelper.addAttachment(MimeUtility.encodeText("filename"), new InputStreamSource() {

                        @Override
                        public InputStream getInputStream() throws IOException {
                            // TODO Auto-generated method stub
                            return new FileInputStream(file);
                        }
                    });
					*/                    
                };
            });

            retVal = true;
        } catch (Exception e) {
            logger.error("Can't send email... " + e.getMessage(), e);
        }
		
		return retVal;
	}

}
