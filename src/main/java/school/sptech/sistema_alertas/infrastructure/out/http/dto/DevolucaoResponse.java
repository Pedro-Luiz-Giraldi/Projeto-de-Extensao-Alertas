package school.sptech.sistema_alertas.infrastructure.out.http.dto;

import java.time.LocalDate;

public record DevolucaoResponse(
        Integer solicitacao,
        LocalDate dataCriacao
) {}
