import java.util.ArrayList;

public class Usuario {

    private String nome;
    private ArrayList<Receita> receitas;
    private ArrayList<Despesa> despesas;

    public Usuario(String nome) {
        this.nome = nome;
        this.receitas = new ArrayList<>();
        this.despesas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarReceita(Receita r) {
        receitas.add(r);
    }

    public void adicionarDespesa(Despesa d) {
        despesas.add(d);
    }

    public void removerReceita(int index) {
        receitas.remove(index);
    }

    public void removerDespesa(int index) {
        despesas.remove(index);
    }

    public double totalReceitas() {
        double total = 0;

        for (int i = 0; i < receitas.size(); i++) {
            total += receitas.get(i).getValor();
        }

        return total;
    }

    public double totalDespesas() {
        double total = 0;

        for (int i = 0; i < despesas.size(); i++) { // mesma coisa
            total += despesas.get(i).getValor();
        }

        return total;
    }

    public String gerarRelatorio() {

        String texto = "Usuário: " + nome + "\n\n";

        texto += "Receitas:\n";
        for (int i = 0; i < receitas.size(); i++) {
            texto += receitas.get(i).mostrar() + "\n";
        }

        texto += "\nDespesas:\n";
        for (int i = 0; i < despesas.size(); i++) { // trocar para foreach, array list n precisa de for
            texto += despesas.get(i).mostrar() + "\n";
        }

        double totalR = totalReceitas();
        double totalD = totalDespesas();
        double saldo = totalR - totalD;

        texto += "\nTotal Receitas: R$ " + totalR;
        texto += "\nTotal Despesas: R$ " + totalD;
        texto += "\nSaldo: R$ " + saldo;

        // Regra 50-30-20
        texto += "\n\nRegra 50-30-20:";
        texto += "\n50% Necessidades: R$ " + (totalR * 0.5);
        texto += "\n30% Desejos: R$ " + (totalR * 0.3);
        texto += "\n20% Investimentos: R$ " + (totalR * 0.2);

        return texto;
    }
}
