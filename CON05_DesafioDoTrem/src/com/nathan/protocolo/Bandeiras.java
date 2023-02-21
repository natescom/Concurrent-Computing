package com.nathan.protocolo;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.nathan.utils.Controller;
import com.nathan.utils.Gallery;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: Bandeira
 * Funcao: Utiliza o sulocao por bandeiras para o problema da
 * regiao critica
 * ************************************************************** */
public class Bandeiras extends Protocol {
  private boolean flag_right;            // Bandeira que fica a direita true: bandeira abaixada tunel vazio
  private boolean flag_left;             // Bandeira que fica a esquerda  true: bandeira abaixada tubel vazio
  private ImageView flagboy_right;       // Imagem da bandeira direita
  private ImageView flagboy_left;        // Imagem da bandeira esquerda

  /**
   * Construtor
   * @param flagboy_right Imageview da bandeira da direita
   * @param flagboy_left  Imageview da bandeira da esquerda
   */
  public Bandeiras(ImageView flagboy_right, ImageView flagboy_left) {
    this.flagboy_right = flagboy_right;
    this.flagboy_left = flagboy_left;
    this.flagboy_right.setVisible(true);
    this.flagboy_left.setVisible(true);
    flag_right = true;
    flag_left = true;
  }

  @Override
  public void enter_region(int processID) {
    boolean direction = Controller.processos[processID].getDirection();
    while(!isEmpty(direction) || isExitFree(direction)){                    // Se a bandeira de entrada estiver levantada ou a da saida estiver abaixada significa que eu nao posso entrar na minha zona critica
      System.out.print("");
    }
    flagChanger(direction,false,Gallery.FLAGBOY);                   // Levanto a bandeira
  }

  @Override
  public void leave_region(int processID) {
    flagChanger(Controller.processos[processID].getDirection(),true,Gallery.FLAGBOYPARADO); // Abaixo a bandeira
  }

  /**
   * Verifica se a bandeira de entrada esta abaixada, ou seja se a entrada estah livre
   * @param direction Necessaria para saber se o trem quer entrar pela esquerda ou pela direita do tunel
   * @return
   */
  public boolean isEmpty(boolean direction) {
    if(direction)
      return flag_right;
    else
      return flag_left;
  }

  /**
   * Verifico bandeira de saida esta abaixada, ou seja se a saida esta livre
   * @param direction Necessaria para saber se o trem quer entrar pela esquerda ou pela direita do tunel
   * @return
   */
  public boolean isExitFree(boolean direction){
    if(direction)
      return !flag_left;
    else
      return !flag_right;
  }

  /**
   * Troca o estado da bandeira
   * @param direction   Direcao do trem
   * @param flagStatus  Estado da bandeira (True: abaixada) (False: astiada)
   * @param image       Imagem da bandeira
   */
  public void flagChanger(boolean direction, boolean flagStatus, Image image){
    Platform.runLater(()->{
      if(direction) {
        flag_right = flagStatus;
        flagboy_left.setImage(image);
      } else {
        flag_left = flagStatus;
        flagboy_right.setImage(image);
      }
    });
  }
}
