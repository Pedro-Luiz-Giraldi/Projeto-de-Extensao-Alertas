package school.sptech.sistema_alertas.application.port.in;

import java.time.LocalDate;

public interface VerificarVencimentoMaterialUseCase {

    void verificar(LocalDate hoje);

}
