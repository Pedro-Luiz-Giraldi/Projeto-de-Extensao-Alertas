package school.sptech.sistema_alertas.application.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import school.sptech.sistema_alertas.application.dto.AlertaMessage;
import school.sptech.sistema_alertas.application.port.in.VerificarVencimentoMaterialUseCase;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.MaterialGatewayPort;
import school.sptech.sistema_alertas.domain.material.Material;

public class VerificarVencimentoMaterialInteractor implements VerificarVencimentoMaterialUseCase {

    private final MaterialGatewayPort materialGateway;
    private final AlertaNotificationPort alertaNotification;
    private final List<Long> diasAntecedencia;

    public VerificarVencimentoMaterialInteractor(MaterialGatewayPort materialGateway,
            AlertaNotificationPort alertaNotification, List<Long> diasAntecedencia) {
        this.materialGateway = materialGateway;
        this.alertaNotification = alertaNotification;
        this.diasAntecedencia = diasAntecedencia.stream().sorted().toList();
    }

    @Override
    public void verificar(LocalDate hoje) {
        List<Material> materiais = materialGateway.buscarMateriaisComLimites();

        materiais.forEach(material -> {
            if (material.isVencido(hoje)) {
                alertaNotification.notificar(new AlertaMessage(
                        "MATERIAL_VENCIDO",
                        material.getId(),
                        material.getNomeMaterial(),
                        "%s: vencido em %s".formatted(
                                material.getNomeMaterial(),
                                material.getDataVencimento()),
                        LocalDateTime.now()));
                return;
            }

            for (Long dias : diasAntecedencia) {
                if (material.isPertoDoVencimento(hoje, dias)) {
                    long diasRestantes = ChronoUnit.DAYS.between(hoje, material.getDataVencimento());
                    alertaNotification.notificar(new AlertaMessage(
                            "VENCIMENTO_PROXIMO",
                            material.getId(),
                            material.getNomeMaterial(),
                            "%s: vence em %d dias (%s)".formatted(
                                    material.getNomeMaterial(),
                                    diasRestantes,
                                    material.getDataVencimento()),
                            LocalDateTime.now()));
                    break;
                }
            }
        });
    }
}
