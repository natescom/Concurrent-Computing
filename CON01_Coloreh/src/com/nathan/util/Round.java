package com.nathan.util;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

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
public class Round implements Runnable{
  private static int round = 0;
  private int[] gabarito;
  private Button[] paleta;
  private Button semaforo;
  private Label status;

  /***************************************************************
   * Metodo: Round
   * Funcao: Construtor do Round
   * Parametros: semaforo = Botao que sera usado para apresentar a
   * sequencia de cores; status = Label que indicara os estados do
   * jogo, como "aguarde", "sua vez"; paleta = Vetor com todos os
   * botoes que serao utilizados pelo usuario para indicar a sequencia
   * de cores correta
   * *************************************************************** */
  public Round(Button semaforo, Label status, Button ... paleta) {
    this.semaforo = semaforo;
    this.status = status;
    this.paleta = paleta;
    round++; // Round inicia pelo 1
    gabarito = new int[round]; // Gabarito será do tamanho do Round
    for (int i = 0; i < round; i++) { // Para cada espaco disponivel dentro do gabarito
        gabarito[i] = new Random().nextInt(4); // Preencho-o com um numero de 0 a 3 que indicara uma cor
    }
  }

  /***************************************************************
   * Metodo: Run
   * Funcao: Responsavel por apresentar ao usuario todas as cores
   * do gabarito
   * *************************************************************** */
  @Override
  public void run() {
    String styleDefault = semaforo.getStyle(); // Salvo o estilo padrao do botao
    semaforo.setDisable(true); // Desativo o botao que foi passado como semaforo para ele nao ser clicavel
    Platform.runLater(() -> {
      status.setText("Aguarde"); // Passo o estado do jogo para "aguarde"
      semaforo.setText(""); // Limpo o texto do semaforo para que nao haja texto
    });
    for (int i = 0; i < gabarito.length; i++) {  // Para cada cor marcada no gabarito
      try {
        Thread.sleep(2000); // Pauso a Thread por 2 segundos antes de mostrar a cor
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      switch (gabarito[i]){ // Caso o codigo marcado no gabarito de valor i seja
        case 0:  // Zero, aplico a cor verde ao semaforo
          semaforo.setStyle("-fx-background-color:   green");
          break;
        case 1:  // Um, aplico a cor vermelha ao semaforo
          semaforo.setStyle("-fx-background-color:   red");
          break;
        case 2:  // Dois, aplico a cor amarela ao semaforo
          semaforo.setStyle("-fx-background-color:   yellow");
          break;
        case 3:  // Tres, aplico a cor azul ao semaforo
          semaforo.setStyle("-fx-background-color:   blue");
          break;
      }
      try {
        Thread.sleep(1000);  // Pauso a thread para mostrar a cor atual do botao por 1 segundo
        } catch (InterruptedException e) {
          e.printStackTrace();
      }
      semaforo.setStyle(styleDefault); // Aplico o estilo original ao botao para nao mostrar mais a cor atual
    }
    Platform.runLater(() -> status.setText("Sua vez")); // Ao termino da apresentacao das cores, indico ao usuario que ele ja pode interagir
    for (int i = 0; i < paleta.length; i++) {  // Para cada botao da paleta de cores
      paleta[i].setDisable(false); // Ele se torna clicavel
    }
  }

  /***************************************************************
   * Metodo: getGabarito
   * Funcao: Utilizar o atributo privado gabarito
   * Retorno: Retorna o vetor de inteiros que descrevem a sequencia
   * correta de cores
   * *************************************************************** */
  public int[] getGabarito() {
      return gabarito;
    }
  /***************************************************************
   * Metodo: setRound
   * Funcao: Dar um valor ao atributo privado round
   * *************************************************************** */
  public static void setRound(int round) {
      Round.round = round;
    }
  /***************************************************************
   * Metodo: getRound
   * Funcao: Utilizar o atributo privado round
   * Retorno: Retorna o inteiro que descrevem o numero atual do Round
   * *************************************************************** */
  public static int getRound() {
    return round;
  }
}
