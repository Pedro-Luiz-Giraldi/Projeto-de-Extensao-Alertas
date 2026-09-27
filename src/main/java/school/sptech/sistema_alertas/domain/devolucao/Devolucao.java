package school.sptech.sistema_alertas.domain.devolucao;

import java.time.LocalDate;

public class Devolucao {

    private Integer id;
    private LocalDate dataCriacao;

    private Devolucao(Integer id, LocalDate dataCriacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
    }

    public static Devolucao criar(Integer id, LocalDate dataCriacao) {
        return new Devolucao(id, dataCriacao);
    }


    public boolean isPendente(LocalDate agora, Long limite) {
        return dataCriacao != null
            && dataCriacao.isBefore(agora.minusDays(limite));
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }
}
