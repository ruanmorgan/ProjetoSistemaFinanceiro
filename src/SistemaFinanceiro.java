import javax.swing.JOptionPane;

public class SistemaFinanceiro {

    public static void main(String[] args) {

        Usuario usuario = null;

        while (true) {

            String opcao = JOptionPane.showInputDialog(
                    "1 - Criar usuário\n" +
                            "2 - Adicionar receita\n" +
                            "3 - Adicionar despesa\n" +
                            "4 - Ver relatório\n" +
                            "0 - Sair"
            );

            int op = Integer.parseInt(opcao);

            if (op == 1) {

                String nome = JOptionPane.showInputDialog("Nome do usuário:");
                usuario = new Usuario(nome);

            } else if (op == 2) {

                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Crie um usuário primeiro!");
                } else {

                    String desc = JOptionPane.showInputDialog("Descrição da receita:");
                    double valor = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor:")
                    );

                    usuario.adicionarReceita(new Receita(desc, valor));
                }

            } else if (op == 3) {

                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Crie um usuário primeiro!");
                } else {

                    String desc = JOptionPane.showInputDialog("Descrição da despesa:");
                    double valor = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor:")
                    );

                    usuario.adicionarDespesa(new Despesa(desc, valor));
                }

            } else if (op == 4) {

                if (usuario == null) {
                    JOptionPane.showMessageDialog(null, "Crie um usuário primeiro!");
                } else {

                    JOptionPane.showMessageDialog(null, usuario.gerarRelatorio());
                }

            } else if (op == 0) {
                break;
            }
        }
    }
}
