package school.sptech.sistema_alertas.application.port.out;

import school.sptech.sistema_alertas.application.dto.AlertaMessage;

public interface AlertaNotificationPort {

    void notificar(AlertaMessage mensagem);

}
