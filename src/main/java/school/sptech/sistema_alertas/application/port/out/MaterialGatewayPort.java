package school.sptech.sistema_alertas.application.port.out;

import java.util.List;

import school.sptech.sistema_alertas.domain.material.Material;

public interface MaterialGatewayPort {

    List<Material> buscarMateriaisComLimites();

}
