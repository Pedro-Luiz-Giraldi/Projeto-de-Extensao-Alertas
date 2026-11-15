package school.sptech.sistema_alertas.application.port.out;

import school.sptech.sistema_alertas.application.dto.EmailMessage;

public interface EmailNotificationPort {

    void enviar(EmailMessage mensagem);

}
