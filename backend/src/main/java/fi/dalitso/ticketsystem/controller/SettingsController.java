package fi.dalitso.ticketsystem.controller;

import fi.dalitso.ticketsystem.configuration.MailConfig;
import fi.dalitso.ticketsystem.service.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private MailService mailService;

    /**
     * Changes the MailSender of MailService. Ie. changes the used mail
     * sender.
     * @param newSettings Settings for the new mail sender.
     */
    @ApiOperation(value = "Changes mail server.")
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public void setMailSettings(
            @RequestBody Map<String, Map<String, String>> newSettings) {
        Map<String, String>
                newMailSenderStuff = newSettings.get("mailSenderStuff"),
                newMailProperties = newSettings.get("mailProperties");

        if (newMailSenderStuff != null && newMailProperties != null) {
            JavaMailSender newMailSender =
                    MailConfig.generateMailSender(newMailSenderStuff, newMailProperties);
            mailService.setMailSender(newMailSender);
        }
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
