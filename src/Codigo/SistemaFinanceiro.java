package Codigo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SistemaFinanceiro extends Application {

    private Usuario usuario;

    private TextArea areaRelatorio;

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Sistema Financeiro");

        Button btnCriarUsuario = new Button("Criar Usuário");

        Button btnAdicionarReceita = new Button("Adicionar Receita");

        Button btnAdicionarDespesa = new Button("Adicionar Despesa");

        Button btnVerRelatorio = new Button("Ver Relatório");

        Button btnSalvarJSON = new Button("Salvar JSON");

        Button btnCarregarJSON = new Button("Carregar JSON");

        Button btnConfigurarRegra = new Button("Configurar Regra");

        areaRelatorio = new TextArea();

        areaRelatorio.setPrefHeight(300);

        btnCriarUsuario.setOnAction(e -> criarUsuario());

        btnAdicionarReceita.setOnAction(e -> adicionarReceita());

        btnAdicionarDespesa.setOnAction(e -> adicionarDespesa());

        btnVerRelatorio.setOnAction(e -> verRelatorio());

        btnSalvarJSON.setOnAction(e -> salvarJSON());

        btnCarregarJSON.setOnAction(e -> carregarJSON());

        btnConfigurarRegra.setOnAction(e -> configurarRegra());

        VBox layout = new VBox(10);

        layout.setPadding(new Insets(15));

        layout.getChildren().addAll(
                titulo,
                btnCriarUsuario,
                btnAdicionarReceita,
                btnAdicionarDespesa,
                btnVerRelatorio,
                btnSalvarJSON,
                btnCarregarJSON,
                btnConfigurarRegra,
                areaRelatorio
        );

        Scene scene = new Scene(layout, 500, 500);

        stage.setTitle("Sistema Financeiro");

        stage.setScene(scene);

        stage.show();
    }

    private void criarUsuario() {

        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle("Criar Usuário");

        dialog.setHeaderText("Digite o nome do usuário");

        dialog.showAndWait().ifPresent(nome -> {

            usuario = new Usuario(nome);

            areaRelatorio.setText("Usuário criado: " + nome);
        });
    }

    private void adicionarReceita() {

        if (usuario == null) {

            mostrarAlerta("Crie um usuário primeiro!");

            return;
        }

        TextInputDialog dialogDescricao = new TextInputDialog();

        dialogDescricao.setTitle("Receita");

        dialogDescricao.setHeaderText("Descrição da receita");

        String descricao = dialogDescricao.showAndWait().orElse("");

        TextInputDialog dialogValor = new TextInputDialog();

        dialogValor.setTitle("Receita");

        dialogValor.setHeaderText("Valor da receita");

        String valorTexto = dialogValor.showAndWait().orElse("0");

        double valor = Double.parseDouble(valorTexto);

        usuario.adicionarReceita(new Receita(descricao, valor));

        areaRelatorio.setText("Receita adicionada!");
    }

    private void adicionarDespesa() {

        if (usuario == null) {

            mostrarAlerta("Crie um usuário primeiro!");

            return;
        }

        TextInputDialog dialogDescricao = new TextInputDialog();

        dialogDescricao.setTitle("Despesa");

        dialogDescricao.setHeaderText("Descrição da despesa");

        String descricao = dialogDescricao.showAndWait().orElse("");

        TextInputDialog dialogValor = new TextInputDialog();

        dialogValor.setTitle("Despesa");

        dialogValor.setHeaderText("Valor da despesa");

        String valorTexto = dialogValor.showAndWait().orElse("0");

        double valor = Double.parseDouble(valorTexto);

        usuario.adicionarDespesa(new Despesa(descricao, valor));

        areaRelatorio.setText("Despesa adicionada!");
    }

    private void verRelatorio() {

        if (usuario == null) {

            mostrarAlerta("Crie um usuário primeiro!");

            return;
        }

        areaRelatorio.setText(usuario.gerarRelatorio());
    }

    private void salvarJSON() {

        if (usuario == null) {

            mostrarAlerta("Nenhum usuário criado!");

            return;
        }

        PersistenciaJSON.salvar(usuario);

        areaRelatorio.setText("Dados salvos em JSON!");
    }

    private void carregarJSON() {

        usuario = PersistenciaJSON.carregar();

        if (usuario != null) {

            areaRelatorio.setText(
                    "Usuário carregado: " + usuario.getNome()
            );

        } else {

            mostrarAlerta("Erro ao carregar JSON!");
        }
    }

    private void configurarRegra() {

        if (usuario == null) {

            mostrarAlerta("Crie um usuário primeiro!");

            return;
        }

        TextInputDialog dialogNecessidades = new TextInputDialog(
                String.valueOf(usuario.getPercentualNecessidades()));

        dialogNecessidades.setTitle("Configurar Regra");

        dialogNecessidades.setHeaderText(
                "Digite o percentual para Necessidades");

        String textoNecessidades =
                dialogNecessidades.showAndWait().orElse("");

        if (textoNecessidades.isEmpty()) {

            return;
        }

        TextInputDialog dialogDesejos = new TextInputDialog(
                String.valueOf(usuario.getPercentualDesejos()));

        dialogDesejos.setTitle("Configurar Regra");

        dialogDesejos.setHeaderText(
                "Digite o percentual para Desejos");

        String textoDesejos =
                dialogDesejos.showAndWait().orElse("");

        if (textoDesejos.isEmpty()) {

            return;
        }

        double necessidades =
                Double.parseDouble(textoNecessidades);

        double desejos =
                Double.parseDouble(textoDesejos);

        if (necessidades < 0 || desejos < 0) {

            mostrarAlerta(
                    "Os percentuais não podem ser negativos!");

            return;
        }

        if (necessidades + desejos > 100) {

            mostrarAlerta(
                    "A soma dos percentuais não pode passar de 100%!");

            return;
        }

        double investimentos =
                100 - necessidades - desejos;

        usuario.setPercentualNecessidades(necessidades);

        usuario.setPercentualDesejos(desejos);

        usuario.setPercentualInvestimentos(investimentos);

        areaRelatorio.setText(
                "Regra atualizada!\n\n" +
                        necessidades + "% Necessidades\n" +
                        desejos + "% Desejos\n" +
                        investimentos + "% Investimentos"
        );
    }

    private void mostrarAlerta(String mensagem) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Aviso");

        alert.setHeaderText(null);

        alert.setContentText(mensagem);

        alert.showAndWait();
    }

    public static void main(String[] args) {

        launch(args);
    }
}