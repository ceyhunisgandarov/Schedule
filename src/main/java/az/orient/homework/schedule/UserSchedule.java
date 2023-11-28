package az.orient.homework.schedule;


import az.orient.homework.mail.MailSender;
import az.orient.homework.mail.MailSenderImpl;
import az.orient.homework.response.RespUser;
import az.orient.homework.response.Response;
import az.orient.homework.service.UserService;
import az.orient.homework.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserSchedule implements Runnable{

    private final UserService userService;
    private final Utility utility;
    private final MailSender sender;

    @Scheduled(cron = "*/10 * * * * *")
    public void run() {
        Response<List<RespUser>> response = userService.getActiveUsers();
        List<RespUser> respUsers = response.getT();
        if (respUsers == null) {
            System.out.println("List is empty");
        } else {
            for (RespUser respUser : respUsers) {
                utility.updateActiveToPending(respUser.getId());
                String email = respUser.getEmail();
                String token = respUser.getToken();
                sender.sendEmail(email, token);
                utility.updatePendingToSendingMail(respUser.getId());
            }
        }
    }
}
