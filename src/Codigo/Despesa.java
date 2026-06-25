package Codigo;


public class Despesa {

    private String descricao;
    private Double valor;


    public Despesa() {
    }

    public Despesa(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return descricao + " - R$ " + String.format("%.2f", valor);
    }
}