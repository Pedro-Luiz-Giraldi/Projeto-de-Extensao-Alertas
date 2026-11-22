package school.sptech.sistema_alertas.infrastructure.out.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import school.sptech.sistema_alertas.application.dto.EmailMessage;

@Component
public class SmtpEmailSender {

    private final JavaMailSender mailSender;
    private final String remetente;
    private final String destinatarioFixo;

    public SmtpEmailSender(JavaMailSender mailSender,
                           @Value("${app.email.remetente}") String remetente,
                           @Value("${app.email.destinatario-fixo}") String destinatarioFixo) {
        this.mailSender = mailSender;
        this.remetente = remetente;
        this.destinatarioFixo = destinatarioFixo;
    }

    public void enviar(EmailMessage email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(remetente);
        msg.setTo(destinatarioFixo);
        msg.setSubject(email.assunto());
        msg.setText(email.corpo());
        mailSender.send(msg);
    }
}