package school.sptech.sistema_alertas.infrastructure.in.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import school.sptech.sistema_alertas.application.dto.EmailMessage;

@Component
public class EmailConsumer {

    private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);

    private final JavaMailSender mailSender;
    private final String remetente;
    private final String destinatarioFixo;

    public EmailConsumer(JavaMailSender mailSender,
                         @Value("${app.email.remetente}") String remetente,
                         @Value("${app.email.destinatario-fixo}") String destinatarioFixo) {
        this.mailSender = mailSender;
        this.remetente = remetente;
        this.destinatarioFixo = destinatarioFixo;
    }

    @RabbitListener(queues = "${app.rabbitmq.email_queue_name}")
    public void receber(EmailMessage email) {
        log.info("E-mail recebido da fila: {}", email.assunto());

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(remetente);
        msg.setTo(destinatarioFixo);
        msg.setSubject(email.assunto());
        msg.setText(email.corpo());
        mailSender.send(msg);

        log.info("E-mail enviado com sucesso");
    }
}