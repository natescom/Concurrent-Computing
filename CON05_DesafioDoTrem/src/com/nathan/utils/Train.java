package com.nathan.utils;

import javafx.scene.control.Slider;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: Train
 * Funcao: Executa os processos de um trem, que sao basicamente
 * percorrer as linhas de trem.
 * ************************************************************** */

public abstract class Train extends Thread {
  private int processID;
  private final String name;        // Nome do trem
  protected TrainLine route;        // Linha atual que o trem esta percorrendo
  private final Slider slider;      // Usado pra pegar a velocidade do trem em trilhos por segundo
  private final boolean direction;  // Qual sentido do trem true: esquerda pra direita ; false: direita pra esquerda
  protected int mileages;           // Trilhos percorridos na linha, usado para fazer a contagem de quantos trilhos faltam para terminar a linha

  /**
   * Contrutor
   * @param name      Nome do trem
   * @param slider    Usado pra pegar a velocidade do trem
   * @param direction Sentido do trem True: (->)  False: (<-)
   * @param route     Linha em que o Trem partirah
   */
  public Train(int processID,String name, Slider slider, boolean direction, TrainLine route) {
    this.name = name;
    this.slider = slider;
    this.route = route;
    this.direction = direction;
    this.processID = processID;
  }

  /**
   * Aplica alteracoes da interface
   * @param direction sentido do movimento do trem
   */
  protected abstract void moveGUI(boolean direction);

  /**
   * Coloca o trem em movimento
   * @return True: (continua em percurso),  False: (chegou na estacao)
   */
  public boolean putFirewood() {
    if (route == null) {                                  // Se nao houver mais linhas pra percorrer
      System.out.println(name+" chegou na estacao");
      return false;
    } else {
      if (mileages < route.getLength()) {                 // Verifico se minha trilhometragem eh menor que o tamanho da linha
        mileages++;                                       // Aumento a minha trilhometragem, isto eh, percorro um trilho
        moveGUI(direction);                               // Executo alteracao na interface
      } else {                                            // caso minha trilhometragem seja igual ou maior ao tamanho da linha significa que eu cheguei ao seu final e devo ir para a proxima linha
        TrainLine line = route.nextLine(direction);       // Pego a proxima linha
        if(line instanceof Tunnel){                       // Verifico se ela eh um tunel, pois os tuneis sao zonas criticas
          Tunnel tunnel = (Tunnel) line;
          tunnel.getProtocolo().enter_region(processID);
        }else{                                            // Se a proxima linha nao for um tunel eu vou pra ela tranquilamente pois nao eh zona critica
          if(route instanceof Tunnel) {                   // Mas antes verifico se estou saindo de uma zona critica
            ((Tunnel) route).getProtocolo().leave_region(processID);
          }
        }
        route = line;                                     // troco a linha atual
        mileages = 0;                                     // reinicio a trilhometragem pra contar as passagens de trilho da linha atual
      }
    }
    return true;                                          // Retorno true para permanecer no loop
  }

  @Override
  public void run() {
    // O TRECHO ABAIXO EXECUTA O CODIGO EM LOOP COM PAUSA DEPENDENDO DA VELOCIDADE DO TREM //
    while (putFirewood()){
      try {
        sleep(1000/(int) slider.getValue());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public boolean getDirection() {
    return direction;
  }
}
