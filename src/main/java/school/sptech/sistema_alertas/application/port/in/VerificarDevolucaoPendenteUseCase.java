package school.sptech.sistema_alertas.application.port.in;

import java.time.LocalDate;

public interface VerificarDevolucaoPendenteUseCase {

    public void verificar(LocalDate hoje);
    
}
