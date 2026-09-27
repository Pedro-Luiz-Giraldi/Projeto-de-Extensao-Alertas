package school.sptech.sistema_alertas.infrastructure.out.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import school.sptech.sistema_alertas.application.port.out.MaterialGatewayPort;
import school.sptech.sistema_alertas.domain.material.Material;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.LimiteResponse;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.MaterialResponse;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.PageResponse;

@Component
public class MaterialGatewayAdapter implements MaterialGatewayPort {

    private final RestClient restClient;

    public MaterialGatewayAdapter(@Value("${app.monolito.base-url}") String baseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public List<Material> buscarMateriaisComLimites() {
        List<MaterialResponse> materiais = buscarTodosMateriais();
        List<LimiteResponse> limites = buscarTodosLimites();

        Map<Integer, Integer> limitePorMaterial = limites.stream()
                .filter(l -> l.materialId() != null
                        && l.tipoLimite() != null
                        && l.tipoLimite().tipo() != null
                        && (l.tipoLimite().tipo().toUpperCase().contains("MÍNIMO")
                                || l.tipoLimite().tipo().toUpperCase().contains("MINIMO")))
                .collect(Collectors.toMap(
                        LimiteResponse::materialId,
                        l -> Integer.parseInt(l.limite()),
                        Math::max));

        return materiais.stream()
                .map(m -> Material.criar(
                        m.id(),
                        m.nomeMaterial(),
                        m.quantidade(),
                        m.dataVencimento(),
                        limitePorMaterial.get(m.id())))
                .toList();
    }

    private List<MaterialResponse> buscarTodosMateriais() {
        List<MaterialResponse> todos = new ArrayList<>();
        int page = 0;

        while (true) {
            PageResponse<MaterialResponse> response = restClient.get()
                    .uri("/v1/materiais?page={page}&size=100", page)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            if (response == null || response.content() == null || response.content().isEmpty()) {
                break;
            }

            todos.addAll(response.content());
            page++;

            if (page >= response.totalPages()) {
                break;
            }
        }

        return todos;
    }

    private List<LimiteResponse> buscarTodosLimites() {
        List<LimiteResponse> limites = restClient.get()
                .uri("/v1/limites")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return limites != null ? limites : List.of();
    }
}
