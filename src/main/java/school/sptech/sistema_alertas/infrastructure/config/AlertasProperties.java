package school.sptech.sistema_alertas.infrastructure.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.alertas")
public record AlertasProperties(
        Vencimento vencimento
) {

    public record Vencimento(List<Long> diasAntecedencia) {}

}
