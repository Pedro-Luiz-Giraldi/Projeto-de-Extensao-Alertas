package school.sptech.sistema_alertas.application.dto;

import java.time.LocalDateTime;

public class AlertaMapper {

    public static AlertaMessage toAlerta(Integer solicitacaoId) {
        return new AlertaMessage(
                "SOLICITAÇÃO",
                solicitacaoId, "PRAZO_EXPIRADO",
                "Solicitação Expirada", LocalDateTime.now()
        );
    }

    public static EmailMessage toEmail(AlertaMessage alerta) {
        return new EmailMessage(
                "ignorado",
                "[Alerta] " + alerta.descricao(),
                alerta.nome() + ": " + alerta.descricao() + " (ref. " + alerta.referenciaId() + ")",
                LocalDateTime.now());
    }
}