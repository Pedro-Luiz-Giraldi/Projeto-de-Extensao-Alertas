package school.sptech.sistema_alertas.infrastructure.out.http;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.domain.solicitacao.Solicitacao;

@Service
public class SolicitacaoGatewayAdapter implements SolicitacaoGatewayPort {

    private final RestClient restClient;

    public SolicitacaoGatewayAdapter(RestClient.Builder builder ) {
        this.restClient = builder.baseUrl("http://localhost:8081").build();
    }

    @Override
    public List<Solicitacao> buscarSolicitacoes() {
        List<SolicitacaoResponse> responses = restClient.get()
                .uri("/v1/solicitacoes")
                .retrieve()
                .body(new ParameterizedTypeReference<List<SolicitacaoResponse>>() {});

        return responses.stream()
            .map(res -> Solicitacao.criar(
                        res.id, 
                        res.dataParaEnvio))
            .toList();
    }

    public record SolicitacaoResponse(
        Integer id,
        LocalDateTime dataParaEnvio
    ) {}
}
