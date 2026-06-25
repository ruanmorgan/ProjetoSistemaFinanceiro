package Codigo;

import java.util.ArrayList;

public class Usuario {

    private String nome;

    private ArrayList<Receita> receitas;

    private ArrayList<Despesa> despesas;

    private double percentualNecessidades = 50;

    private double percentualDesejos = 30;

    private double percentualInvestimentos = 20;

    public Usuario() {
        receitas = new ArrayList<>();
        despesas = new ArrayList<>();
    }

    public Usuario(String nome) {
        this.nome = nome;
        receitas = new ArrayList<>();
        despesas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(ArrayList<Receita> receitas) {
        this.receitas = receitas;
    }

    public ArrayList<Despesa> getDespesas() {
        return despesas;
    }

    public void setDespesas(ArrayList<Despesa> despesas) {
        this.despesas = despesas;
    }

    public double getPercentualNecessidades() {
        return percentualNecessidades;
    }

    public void setPercentualNecessidades(double percentualNecessidades) {
        this.percentualNecessidades = percentualNecessidades;
    }

    public double getPercentualDesejos() {
        return percentualDesejos;
    }

    public void setPercentualDesejos(double percentualDesejos) {
        this.percentualDesejos = percentualDesejos;
    }

    public double getPercentualInvestimentos() {
        return percentualInvestimentos;
    }

    public void setPercentualInvestimentos(double percentualInvestimentos) {
        this.percentualInvestimentos = percentualInvestimentos;
    }

    public void adicionarReceita(Receita receita) {
        receitas.add(receita);
    }

    public void adicionarDespesa(Despesa despesa) {
        despesas.add(despesa);
    }

    public void removerReceita(int index) {
        receitas.remove(index);
    }

    public void removerDespesa(int index) {
        despesas.remove(index);
    }

    public double totalReceitas() {
        double total = 0;

        for (Receita receita : receitas) {
            total += receita.getValor();
        }

        return total;
    }

    public double totalDespesas() {
        double total = 0;

        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }

        return total;
    }

    public String gerarRelatorio() {

        String texto = "Usuário: " + nome + "\n\n";

        texto += "Receitas:\n";

        for (Receita receita : receitas) {
            texto += receita + "\n";
        }

        texto += "\nDespesas:\n";

        for (Despesa despesa : despesas) {
            texto += despesa + "\n";
        }

        double totalR = totalReceitas();
        double totalD = totalDespesas();
        double saldo = totalR - totalD;

        texto += "\nTotal Receitas: R$ " + String.format("%.2f", totalR);
        texto += "\nTotal Despesas: R$ " + String.format("%.2f", totalD);
        texto += "\nSaldo: R$ " + String.format("%.2f", saldo);

        texto += "\n\nDistribuição Financeira:";

        texto += "\n" + percentualNecessidades +
                "% Necessidades: R$ " +
                String.format("%.2f", totalR * percentualNecessidades / 100);

        texto += "\n" + percentualDesejos +
                "% Desejos: R$ " +
                String.format("%.2f", totalR * percentualDesejos / 100);

        texto += "\n" + percentualInvestimentos +
                "% Investimentos: R$ " +
                String.format("%.2f", totalR * percentualInvestimentos / 100);

        return texto;
    }
}
