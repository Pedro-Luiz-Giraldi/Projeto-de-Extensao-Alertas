package school.sptech.sistema_alertas.application.service;

import java.time.LocalDateTime;
import java.util.List;

import school.sptech.sistema_alertas.application.dto.AlertaMessage;
import school.sptech.sistema_alertas.application.port.in.VerificarEstoqueBaixoUseCase;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.MaterialGatewayPort;
import school.sptech.sistema_alertas.domain.material.Material;

public class VerificarEstoqueBaixoInteractor implements VerificarEstoqueBaixoUseCase {

    private final MaterialGatewayPort materialGateway;
    private final AlertaNotificationPort alertaNotification;

    public VerificarEstoqueBaixoInteractor(MaterialGatewayPort materialGateway,
            AlertaNotificationPort alertaNotification) {
        this.materialGateway = materialGateway;
        this.alertaNotification = alertaNotification;
    }

    @Override
    public void verificar() {
        List<Material> materiais = materialGateway.buscarMateriaisComLimites();

        materiais.forEach(material -> {
            if (material.isEstoqueBaixo()) {
                alertaNotification.notificar(new AlertaMessage(
                        "ESTOQUE_BAIXO",
                        material.getId(),
                        material.getNomeMaterial(),
                        "%s: %d unidades (limite mínimo: %d)".formatted(
                                material.getNomeMaterial(),
                                material.getQuantidade(),
                                material.getLimiteMinimo()),
                        LocalDateTime.now()));
            }
        });
    }
}
