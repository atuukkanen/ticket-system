package fi.dalitso.ticketsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static fi.dalitso.ticketsystem.configuration.EnvironmentTool.getEnvironmentVariable;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Resource
    private Environment environment;

    @Bean
    public JavaMailSender mailSender() {
        try {
            return generateDefaultMailSender();
        } catch (Exception e) {
            return null;
        }
    }

    private JavaMailSender generateDefaultMailSender()
            throws IOException, RuntimeException {
        HashMap<String, String> mailSenderStuff = new HashMap<>();
        HashMap<String, String> mailProperties = new HashMap<>();

        mailSenderStuff.put("MAIL_HOSTNAME",
                getEnvironmentVariable("MAIL_HOSTNAME"));
        mailSenderStuff.put("MAIL_PORT",
                getEnvironmentVariable("MAIL_PORT", "587"));
        mailSenderStuff.put("MAIL_PROTOCOL",
                getEnvironmentVariable("MAIL_PROTOCOL", "smtp"));
        mailSenderStuff.put("MAIL_USERNAME",
                getEnvironmentVariable("MAIL_USERNAME"));
        mailSenderStuff.put("MAIL_PASSWORD",
                getEnvironmentVariable("MAIL_PASSWORD"));

        mailProperties.put("mail.smtp.auth",
                environment.getRequiredProperty("mail.smtp.auth"));
        mailProperties.put("mail.smtp.starttls.enable",
                environment.getRequiredProperty("mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.ssl.trust",
                getEnvironmentVariable("MAIL_HOSTNAME"));
        mailProperties.put("mail.smtp.quitwait",
                environment.getRequiredProperty("mail.smtp.quitwait"));

        return generateMailSender(mailSenderStuff, mailProperties);
    }

    public static JavaMailSender generateMailSender(
            Map<String, String> mailSenderStuff,
            Map<String, String> mailProperties) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(
                mailSenderStuff.get("MAIL_HOSTNAME"));

        String portStr = mailSenderStuff.get("MAIL_PORT");
        if (portStr == null)
            portStr = "587";
        mailSender.setPort(Integer.parseInt(portStr));

        String protocol = mailSenderStuff.get("MAIL_PROTOCOL");
        if (protocol == null)
            protocol = "smtp";
        mailSender.setProtocol(protocol);

        mailSender.setUsername(mailSenderStuff.get("MAIL_USERNAME"));
        mailSender.setPassword(mailSenderStuff.get("MAIL_PASSWORD"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth",
                mailProperties.get("mail.smtp.auth"));
        javaMailProperties.put("mail.smtp.starttls.enable",
                mailProperties.get("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.ssl.trust",
                mailSenderStuff.get("MAIL_HOSTNAME"));
        javaMailProperties.put("mail.smtp.quitwait",
                mailProperties.get("mail.smtp.quitwait"));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        return new ResourceBundleMessageSource();
    }

}
