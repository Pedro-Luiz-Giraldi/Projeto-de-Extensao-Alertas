package school.sptech.sistema_alertas.infrastructure.out.http;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import school.sptech.sistema_alertas.application.port.out.SolicitacaoGatewayPort;
import school.sptech.sistema_alertas.domain.devolucao.Devolucao;
import school.sptech.sistema_alertas.domain.solicitacao.Solicitacao;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.DevolucaoResponse;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.PageResponse;
import school.sptech.sistema_alertas.infrastructure.out.http.dto.SolicitacaoResponse;

@Component
public class SolicitacaoGatewayAdapter implements SolicitacaoGatewayPort {

    private final RestClient restClient;

    public SolicitacaoGatewayAdapter() {
        this.restClient = RestClient.builder()
            .baseUrl("http://localhost:8081")
            .build();
    }

    @Override
    public List<Solicitacao> buscarSolicitacoes() {
        PageResponse<SolicitacaoResponse> response = restClient.get()
                .uri("/v1/solicitacoes")
                .retrieve()
                .body(new ParameterizedTypeReference<PageResponse<SolicitacaoResponse>>() {});

        return response.content().stream()
            .map(res -> Solicitacao.criar(
                        res.id(), 
                        res.dataParaEnvio()))
            .toList();
    }

    @Override
    public List<Devolucao> buscarDevolucoes() {
        List<DevolucaoResponse> response = restClient.get()
            .uri("/v1/solicitacoes/devolucoes/pendentes")
            .retrieve()
            .body(new ParameterizedTypeReference<List<DevolucaoResponse>>() {});

        return response.stream()
            .map(res -> Devolucao.criar(
                        res.id(),
                        res.dataCriacao()))
            .toList();
    }
}
