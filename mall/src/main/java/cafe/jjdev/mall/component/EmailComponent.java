package cafe.jjdev.mall.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import cafe.jjdev.mall.vo.EmailContents;

@Component
public class EmailComponent {
	@Autowired
	public JavaMailSender emailSender;
	
	public void searchId (EmailContents emailContents) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailContents.getToEmailAddress());
		message.setSubject(emailContents.getSubject());
		String text = "요청하신 아이디는\n" + emailContents.getText() + "\n입니다."; 
		message.setText(text);
		emailSender.send(message);
	}
	
	public void searchPw (EmailContents emailContents) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(emailContents.getToEmailAddress());
		message.setSubject(emailContents.getSubject());
		String text = "요청하신 아이디의 비밀번호는\n" + emailContents.getText() + "\n입니다."; 
		message.setText(text);
		emailSender.send(message);
	}
}