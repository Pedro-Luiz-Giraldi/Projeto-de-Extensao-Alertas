package school.sptech.sistema_alertas.infrastructure.out.http.dto;

import java.time.LocalDateTime;

public record SolicitacaoResponse(
        Integer id,
        LocalDateTime dataParaEnvio
) {}
