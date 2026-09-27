package school.sptech.sistema_alertas.infrastructure.out.http.dto;

public record LimiteResponse(
        Integer id,
        String limite,
        TipoLimiteResponse tipoLimite,
        Integer materialId
) {}
