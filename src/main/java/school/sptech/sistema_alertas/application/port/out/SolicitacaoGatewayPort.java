package school.sptech.sistema_alertas.application.port.out;

import java.util.List;

import school.sptech.sistema_alertas.domain.solicitacao.Solicitacao;

public interface SolicitacaoGatewayPort {

    List<Solicitacao> buscarSolicitacoes();

}
