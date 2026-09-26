package school.sptech.sistema_alertas.infrastructure.out.http.dto;

import java.util.List;

public record PageResponse<T>(
    List<T> content,
    Integer number,
    Integer size,
    Integer totalPages
) {}
