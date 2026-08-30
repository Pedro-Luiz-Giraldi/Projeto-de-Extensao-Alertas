package school.sptech.sistema_alertas.domain.solicitacao;

import java.time.LocalDateTime;

public class Solicitacao {

    private Integer id;
    private LocalDateTime dataParaEnvio;

    private Solicitacao(Integer id, LocalDateTime dataParaEnvio) {
        this.id = id;
        this.dataParaEnvio = dataParaEnvio;
    }

    public static Solicitacao criar(Integer id, LocalDateTime dataParaEnvio) {
        return new Solicitacao(id, dataParaEnvio);
    }


    public boolean isExpirada(LocalDateTime agora) {
        return dataParaEnvio.isBefore(agora);
    }


    public Integer getId() {
        return id;
    }

    public LocalDateTime getDataParaEnvio() {
        return dataParaEnvio;
    }
}
