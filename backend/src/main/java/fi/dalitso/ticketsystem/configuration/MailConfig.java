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
import java.util.Properties;

import static fi.dalitso.ticketsystem.configuration.EnvironmentTool.getEnvironmentVariable;

@Configuration
@PropertySource("classpath:mail.properties")
public class MailConfig {

    @Resource
    private Environment environment;

    @Bean
    public JavaMailSender mailSender() throws IOException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(
                getEnvironmentVariable("MAIL_HOSTNAME"));
        mailSender.setPort(
                Integer.parseInt(getEnvironmentVariable("MAIL_PORT", "587")));
        mailSender.setProtocol(
                getEnvironmentVariable("MAIL_PROTOCOL", "smtp"));
        mailSender.setUsername(
                getEnvironmentVariable("MAIL_USERNAME"));
        mailSender.setPassword(
                getEnvironmentVariable("MAIL_PASSWORD"));

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth",
                environment.getRequiredProperty("mail.smtp.auth"));
        javaMailProperties.put("mail.smtp.starttls.enable",
                environment.getRequiredProperty("mail.smtp.starttls.enable"));
        javaMailProperties.put("mail.smtp.ssl.trust",
                getEnvironmentVariable("MAIL_HOSTNAME"));
        javaMailProperties.put("mail.smtp.quitwait",
                environment.getRequiredProperty("mail.smtp.quitwait"));
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        return new ResourceBundleMessageSource();
    }

}
