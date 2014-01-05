package be.vdab.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
	
	private final JavaMailSender mailSender; 
	private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class); 
	
	@Autowired 
	public MailServiceImpl(JavaMailSender mailSender) { 
	    this.mailSender = mailSender; 
	} 
	
	@Override 
	@Async  // asynchrone oproep van de method sendMail 
	public void zendMail(String to, String subject, String text) { 
	 try { 
	      MimeMessage message = mailSender.createMimeMessage(); 
	      MimeMessageHelper helper = new MimeMessageHelper(message, true); 
	      helper.setTo(to); 
	      helper.setSubject(subject); 
	      helper.setText(text, true); 
	      mailSender.send(message); 
	      logger.info("mail is verstuurd"); 
	 }
	 catch (MessagingException ex) { 
	 } 
  }

}
