package school.sptech.sistema_alertas.domain.material;

import java.time.LocalDate;

public class Material {

    private Integer id;
    private String nomeMaterial;
    private Integer quantidade;
    private LocalDate dataVencimento;
    private Integer limiteMinimo;

    private Material(Integer id, String nomeMaterial, Integer quantidade,
            LocalDate dataVencimento, Integer limiteMinimo) {
        this.id = id;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
        this.dataVencimento = dataVencimento;
        this.limiteMinimo = limiteMinimo;
    }

    public static Material criar(Integer id, String nomeMaterial, Integer quantidade,
            LocalDate dataVencimento, Integer limiteMinimo) {
        return new Material(id, nomeMaterial, quantidade, dataVencimento, limiteMinimo);
    }

    public boolean isEstoqueBaixo() {
        return limiteMinimo != null && quantidade <= limiteMinimo;
    }

    public boolean isPertoDoVencimento(LocalDate hoje, long diasAntecedencia) {
        return dataVencimento != null
                && dataVencimento.isAfter(hoje)
                && dataVencimento.isBefore(hoje.plusDays(diasAntecedencia));
    }

    public boolean isVencido(LocalDate hoje) {
        return dataVencimento != null && !dataVencimento.isAfter(hoje);
    }

    public Integer getId() {
        return id;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public Integer getLimiteMinimo() {
        return limiteMinimo;
    }
}
