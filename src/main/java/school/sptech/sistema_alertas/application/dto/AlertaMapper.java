package school.sptech.sistema_alertas.application.dto;

import java.time.LocalDateTime;

public class AlertaMapper {
    public static AlertaMessage toAlerta(Integer solicitacaoId){
        return new AlertaMessage(
                "SOLICITAÇÃO",
                solicitacaoId, "PRAZO_EXPIRADO",
                "Solicitação Expirada", LocalDateTime.now()
        );
    }
}
