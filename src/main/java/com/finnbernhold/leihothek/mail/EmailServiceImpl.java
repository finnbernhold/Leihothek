package com.finnbernhold.leihothek.mail;

import com.finnbernhold.leihothek.offer.Offer;
import groovy.util.logging.Slf4j;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Component
@Slf4j
public class EmailServiceImpl {
    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    String from;

    @SneakyThrows
    public void sendMessage(Offer offer, String username, String message, String contactData) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String to = offer.contact_email();
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("contactData", contactData);
        String process = templateEngine.process("email", context);
        String subject = String.format("Nachricht von %s Wegen deinem angebot %s", username, offer.title());
        helper.setText(process, true);
        helper.setTo(to);
        helper.setSubject(subject);
        emailSender.send(mimeMessage);
        System.out.printf("send email to " + to);
    }
}
