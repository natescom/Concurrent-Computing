package com.nathan;

import com.nathan.Principal;
import com.nathan.util.Round;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 26/02/2021
 * Ultima alteracao: 05/03/2021
 * Nome: Coloreh
 * Funcao: Jogo que mostra ao usuario uma sequencias de cores e
 * e solicita que ele repita essa sequencia na mesma ordem atraves
 * dos botoes coloridos
 * ************************************************************** */
public class FxmlController {
  private ArrayList<Integer> respostas;
  private Round round;
  private int score;

  @FXML
  public Button btnIniciar;
  public Button btnComoJogar;
  public Button btnVerde;
  public Button btnVermelho;
  public Button btnAmarelo;
  public Button btnAzul;
  public Label lblStatus;
  public Label lblScoreValue;
  public Label lblRoundValue;

  /***************************************************************
   * Metodo: btnOnClick
   * Funcao: Eh executado ao pressionar de qualquer botao na interface
   * Parametros: Recebe um ActionEvent, usado para identificar qual
   * botao foi pressionado
   * *************************************************************** */
  @FXML
  private void btnOnClick(ActionEvent event) {
    if (btnIniciar.equals(event.getSource())) { // Se o evento foi acionado pelo botão iniciar
      round = new Round(btnIniciar, lblStatus, btnVerde, btnVermelho, btnAmarelo, btnAzul); // Instancio um novo round
      lblRoundValue.setText("Round: " + String.valueOf(Round.getRound())); // Indico o round atual
      lblScoreValue.setText("Score: " + String.valueOf(score)); // Indico a pontuacao atual
      respostas = new ArrayList<>(); // Intancio um conjunto (ArrayList) de respostas
      Thread thread = new Thread(round);  // Instacio uma nova thread e passo o round para ser executado em plano de fundo
      thread.start(); // Inicio o thread
    }
    if (btnVerde.equals(event.getSource()))  // Se o evento foi acionado pelo botao Verde
      coloredBtnOnClick(0);  // Aciono o evento de clique num botao colorido e passo o codigo da cor Verde
    if (btnVermelho.equals(event.getSource()))  // Se o evento foi acionado pelo botao Vermelho
      coloredBtnOnClick(1);  // Aciono o evento de clique num botao colorido e passo o codigo da cor Vermelha
    if (btnAmarelo.equals(event.getSource())) // Se o evento foi acionado pelo botao Amarelo
      coloredBtnOnClick(2);  // Aciono o evento de clique num botao colorido e passo o codigo da cor Amarela
    if (btnAzul.equals(event.getSource())) // Se o evento foi acionado pelo botao Azul
      coloredBtnOnClick(3);  // Aciono o evento de clique num botao colorido e passo o codigo da cor Azul
    if (btnComoJogar.equals(event.getSource())) { //Se o evento foi acionado pelo botao Como Jogar
      Platform.runLater(() -> {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("res/layout/layoutComoJogar.fxml")); // Carrego o layout da janela
          Stage stage = new Stage(); // Instancio a janela
          stage.setTitle("Como jogar"); // Sou um titulo
          stage.setScene(new Scene(root)); // Passo o cenario a ser mostrado na janela
          stage.show(); // Mostro a janela
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  /***************************************************************
   * Metodo: resetInterface
   * Funcao: Prepara a interface para um novo round
   * *************************************************************** */
  private void resetInterface() {
    btnIniciar.setDisable(false); // Permito o clique no botao iniciar
    btnVerde.setDisable(true); // Bloqueio o clique no botao verde
    btnVermelho.setDisable(true);  // Bloqueio o clique no botao vermelho
    btnAmarelo.setDisable(true); // Bloqueio o clique no botao Amarelo
    btnAzul.setDisable(true);  // Bloqueio o clique no botao Azul
    if (score > 0)
      lblScoreValue.setText("Score: " + String.valueOf(score)); // Indico a pontuacao atual se score maior que zero para que em caso de fim de jogo, o jogador possa ver seu score final antes de voltar a zero
  }

  /***************************************************************
   * Metodo: nextRound
   * Funcao: Inicia o proximo round do jogo
   * *************************************************************** */
  private void nextRound() {
    score += 1000 / Round.getRound(); // Adicino uma pontuacao ao score
    btnIniciar.setText("Continuar"); // Altero o texto do botao iniciar para inicar proxima etapa do jogo
    lblStatus.setText("Acertou !!!"); // Indico estado de acerto para a label que apresenta os status
    resetInterface(); // Reinicio a interface
  }

  /***************************************************************
   * Metodo: restartGame
   * Funcao: Reinicia o jogo
   * *************************************************************** */
  private void restartGame() {
    score = 0;  // Zero a pontuacao
    Round.setRound(0); // Coloco o numero de rounds como zero igual ao inicio do game
    btnIniciar.setText("Jogar novamente");  // Altero o texto do botao iniciar para indicar nova tentativa
    lblStatus.setText("Game Over"); // Indico estado de fim de jogo para a label que apresenta os status
    resetInterface(); // Reinicio a interface
  }

  /***************************************************************
   * Metodo: isRightChoises
   * Funcao: Verifica se as escolhas batem com o gabarito
   * Retorno: True caso todas as escolhas sejam verdadeiras, False
   * caso sejam falsas
   * *************************************************************** */
  private boolean isAllChoisesRight() {
    for (int i = 0; i < round.getGabarito().length; i++) { // Para cada resposta existente verifico
      if (round.getGabarito()[i] != respostas.get(i)) // Se a resposta com indice i eh diferente da presente no gabarito
        return false; // Caso for, retorno falso e isso indica que ha uma resposta errada
    } // Caso nao for, verifico a proxima resposta
    return true; // Se nao houver nenhuma resposta diferente das do gabarito entao retorno verdadeiro
  }

  /****************************************************************
   * Metodo: checkChoices
   * Funcao: Verifica se a sua resposta esta completa e o que fazer
   * em seguida
   * ************************************************************** */
  private void checkChoices() {
    if (respostas.size() == round.getGabarito().length)  // Se ha tantas respostas quanto solucoes
      if (isAllChoisesRight())  // Verifico se as respostas estão corretas
        nextRound();  // Caso sim, inicio o proximo round
      else
        restartGame(); // Case nao, reinicio o jogo
  }

  /****************************************************************
   * Metodo: coloredBtnOnClick
   * Funcao: Adiciona o parametro inteiro i ao conjunto resposta e
   * verifica as respostas
   * Parametros: i = indica a cor que sera adicionada ao conjunto
   * resposta sendo 0 - Verde, 1 - Vermelho, 2 - Amarelo e 3 - Azul
   * ************************************************************** */
  private void coloredBtnOnClick(int i) {
    respostas.add(i); // Adiciono i ao conjunto resposta
    checkChoices(); // Verifico as respostas
  }
}
