package school.sptech.sistema_alertas.application.dto;

import java.time.LocalDateTime;

public record AlertaMessage(
        String tipo,
        Integer referenciaId,
        String nome,
        String descricao,
        LocalDateTime criadoEm
) {}
