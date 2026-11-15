package school.sptech.sistema_alertas.application.dto;

import java.time.LocalDateTime;

public record EmailMessage(
        String destinatario,
        String assunto,
        String corpo,
        LocalDateTime criadoEm
) {}
