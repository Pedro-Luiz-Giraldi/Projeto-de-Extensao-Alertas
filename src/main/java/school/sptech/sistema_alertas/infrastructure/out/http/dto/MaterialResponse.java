package school.sptech.sistema_alertas.infrastructure.out.http.dto;

import java.time.LocalDate;

public record MaterialResponse(
        Integer id,
        String nomeMaterial,
        Integer quantidade,
        LocalDate dataVencimento
) {}
