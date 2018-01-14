package fi.dalitso.ticketsystem.configuration;

import fi.dalitso.ticketsystem.service.MailContentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.Resource;

@Configuration
@PropertySource("classpath:mailtemplate.properties")
public class MailTemplateConfig {

    @Resource
    private Environment environment;
    private ApplicationContext applicationContext;

    @Bean
    public MailContentBuilder mailContentBuilder() {
        MailContentBuilder mailContentBuilder = new MailContentBuilder();

        mailContentBuilder.setAssigneeChangedTemplateName(
                environment.getProperty("mail.template.assigneechanged"));
        mailContentBuilder.setCommentCreatedTemplateName(
                environment.getProperty("mail.template.commentcreated"));
        mailContentBuilder.setCommentUpdatedTemplateName(
                environment.getProperty("mail.template.commentupdated"));
        mailContentBuilder.setTicketCreatedTemplateName(
                environment.getProperty("mail.template.ticketcreated"));
        mailContentBuilder.setTicketUpdatedTemplateName(
                environment.getProperty("mail.template.ticketupdated"));
        mailContentBuilder.setTicketClosedName(
                environment.getProperty("mail.template.ticketclosed"));

        mailContentBuilder.setTemplateEngine(templateEngine());
        return mailContentBuilder;
    }

    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setTemplateMode(TemplateMode.TEXT);
        resolver.setPrefix(environment.getRequiredProperty("mail.template.prefix"));
        resolver.setSuffix(environment.getRequiredProperty("mail.template.suffix"));
        resolver.setCacheable(false);
        return resolver;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
