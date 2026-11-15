package school.sptech.sistema_alertas.infrastructure;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import school.sptech.sistema_alertas.application.dto.AlertaMessage;
import school.sptech.sistema_alertas.application.dto.EmailMessage;
import school.sptech.sistema_alertas.application.port.in.VerificarEstoqueBaixoUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarPrazoSolicitacoesUseCase;
import school.sptech.sistema_alertas.application.port.in.VerificarVencimentoMaterialUseCase;
import school.sptech.sistema_alertas.application.port.out.AlertaNotificationPort;
import school.sptech.sistema_alertas.application.port.out.EmailNotificationPort;

@Profile("dev")
@RestController
@RequestMapping("/dev")
public class DevController {

    private final VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase;
    private final VerificarEstoqueBaixoUseCase verificarEstoqueBaixoUseCase;
    private final VerificarVencimentoMaterialUseCase verificarVencimentoMaterialUseCase;
    private final AlertaNotificationPort alertaNotificationPort;
    private final EmailNotificationPort emailNotificationPort;

    public DevController(VerificarPrazoSolicitacoesUseCase verificarPrazoSolicitacoesUseCase,
            VerificarEstoqueBaixoUseCase verificarEstoqueBaixoUseCase,
            VerificarVencimentoMaterialUseCase verificarVencimentoMaterialUseCase,
            AlertaNotificationPort alertaNotificationPort,
            EmailNotificationPort emailNotificationPort) {
        this.verificarPrazoSolicitacoesUseCase = verificarPrazoSolicitacoesUseCase;
        this.verificarEstoqueBaixoUseCase = verificarEstoqueBaixoUseCase;
        this.verificarVencimentoMaterialUseCase = verificarVencimentoMaterialUseCase;
        this.alertaNotificationPort = alertaNotificationPort;
        this.emailNotificationPort = emailNotificationPort;
    }

    @PostMapping("/verificar-prazos")
    public void verificarPrazos() {
        verificarPrazoSolicitacoesUseCase.verificar(LocalDateTime.now());
    }

    @PostMapping("/verificar-estoque")
    public void verificarEstoque() {
        verificarEstoqueBaixoUseCase.verificar();
    }

    @PostMapping("/verificar-vencimento")
    public void verificarVencimento() {
        verificarVencimentoMaterialUseCase.verificar(LocalDate.now());
    }

    @PostMapping("/enviar-alerta-teste")
    public void enviarAlertaTeste() {
        alertaNotificationPort.notificar(new AlertaMessage(
                "ESTOQUE_BAIXO",
                999,
                "Material de Teste",
                "Material de Teste: 2 unidades (limite mínimo: 10)",
                LocalDateTime.now()));
    }

    @PostMapping("/enviar-email-teste")
    public void enviarEmailTeste(
            @RequestParam(defaultValue = "teste@sptech.school") String destinatario) {
        emailNotificationPort.enviar(new EmailMessage(
                destinatario,
                "Alerta de Teste - Sistema de Estoque",
                "Mensagem de teste publicada na fila de e-mail.",
                LocalDateTime.now()));
    }
}
