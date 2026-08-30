package school.sptech.sistema_alertas.application.port.in;

import java.time.LocalDateTime;

public interface VerificarPrazoSolicitacoesUseCase {

    void verificar(LocalDateTime agora);
    
}
