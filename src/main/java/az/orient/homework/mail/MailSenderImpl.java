package az.orient.homework.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender{

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String toEmail, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("email address");

        message.setTo(toEmail);
        message.setSubject("Verification Email");
        message.setText("If you are the one trying to open an account, click the link below." + "\n" + "http://localhost:9595/schedule/application/client/confirm-user-email/" + token);

        javaMailSender.send(message);

        System.out.println("message send successfully");
    }

}
