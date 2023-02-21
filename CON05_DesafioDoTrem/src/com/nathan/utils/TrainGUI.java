package com.nathan.utils;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 18/04/2021
 * Nome: TrainGUI
 * Funcao: Aplica as alteracoes na interface grafica a respeito
 * do trem
 * ************************************************************** */
public class TrainGUI extends Train {

  private final ImageView imageView;// Imagem do Trem
  private final Ellipse icon;       // Icone do trem na tela
  private final Line lineRouteTrain;
  private final Line lineRouteIcoX;
  private final Line lineRouteIcoY;

  // --- VARIAVEIS UTILIZADAS NO CALCULO DAS COORDENADAS --- //
  private double x0Img;           // Coordenada X inicial da imageView, usado no calculo do movimento da imagem
  private double x0Ico;           // Coordenada X inicial do Icone, usado no calculo do movimento do icone
  private double routeLength_T;   // Tamanho do percurso em unidades de Trilho
  private double routeImgSize_P;  // Tamanho do percurso da imagem em unidades pixels na tela
  private double routeIcoSize_P;  // Tamanho do percurso do icone em unidades pixels na tela
  private int mileagesTotal_T;    // Metragem total percorrida em unidades de Trilho

  /**
   * Construtor
   * @param name            Nome do trem
   * @param slider          Velocidade em Trilhos por segundo
   * @param diretion        Sentido do trem True: (->)  False: (<-)
   * @param trainLine       Linha em que o Trem partirah
   * @param imageView       Imagemview contendo a imagem do trem
   * @param icon            Icone do trem no mapa
   * @param lineRouteTrain  Linha com as coordenadas do percurso da imagem na tela
   * @param lineRouteIcoX   Linha com as coordenadas horizontais do percurso do icone na tela
   * @param lineRouteIcoY   Linha com as coordenadas verticais do percurso do icone na tela
   */
  public TrainGUI(int processID, String name, Slider slider, boolean diretion, TrainLine trainLine, ImageView imageView, Ellipse icon, Line lineRouteTrain, Line lineRouteIcoX, Line lineRouteIcoY) {
    super(processID, name, slider, diretion, trainLine);
    this.imageView = imageView;
    this.icon = icon;
    this.lineRouteTrain = lineRouteTrain;
    this.lineRouteIcoX = lineRouteIcoX;
    this.lineRouteIcoY = lineRouteIcoY;

    this.routeLength_T = 350;
    routeImgSize_P = lineRouteTrain.getEndX() - lineRouteTrain.getStartX();
    routeIcoSize_P = lineRouteIcoX.getEndX() - lineRouteIcoX.getStartX();
    if (diretion){
      x0Img = lineRouteTrain.getLayoutX()+ lineRouteTrain.getStartX();
      x0Ico = lineRouteIcoX.getLayoutX()+ lineRouteIcoX.getStartX();
      this.icon.setLayoutY(lineRouteIcoY.getLayoutY()+lineRouteIcoY.getStartY());
    }
    else{
      x0Img = lineRouteTrain.getLayoutX()+ lineRouteTrain.getEndX();
      x0Ico = lineRouteIcoX.getLayoutX()+ lineRouteIcoX.getEndX();
      this.icon.setLayoutY(lineRouteIcoY.getLayoutY()+lineRouteIcoY.getEndY());
    }
  }

  /**
   * Move a imagem do trem
   * @param direction Usado para definir o sentido do movimento
   */
  @Override
  protected void moveGUI(boolean direction) {
    Platform.runLater(()->{
      if(direction){
        imageView.setLayoutX(x0Img + (mileagesTotal_T / routeLength_T) * routeImgSize_P); // Calculo baseado nos principios de porcentagem e transformacao de unidades
        icon.setLayoutX(x0Ico + (mileagesTotal_T / routeLength_T) * routeIcoSize_P);
        if(route instanceof Tunnel){
          if(mileages>route.getLength()-25){    // Se esta perto da saida do tunel
            if(icon.getLayoutY() > lineRouteIcoY.getStartY()+lineRouteIcoY.getLayoutY()){     // Se to na linha do tunel
              icon.setLayoutY(icon.getLayoutY()-2);
            }
          }else{
            if(icon.getLayoutY() < -(lineRouteIcoY.getEndY()/2)+lineRouteIcoY.getLayoutY() + 7){  // Se to numa linha normal
              icon.setLayoutY(icon.getLayoutY()+2);
            }
          }
        }
      }else {
        imageView.setLayoutX(x0Img - (mileagesTotal_T / routeLength_T) * routeImgSize_P); // Calculo baseado nos principios de porcentagem e transformacao de unidades
        icon.setLayoutX(x0Ico - (mileagesTotal_T / routeLength_T) * routeIcoSize_P);
        if(route instanceof Tunnel){
          if(mileages>route.getLength()-25){    // Se esta perto da saida
            if(icon.getLayoutY() < lineRouteIcoY.getEndY()+lineRouteIcoY.getLayoutY()){     // Se to na linha do tunel
              icon.setLayoutY(icon.getLayoutY()+2);
            }
          }else{
            if(icon.getLayoutY() > -(lineRouteIcoY.getEndY()/2)+lineRouteIcoY.getLayoutY() + 8){  // Se to numa linha normal
              icon.setLayoutY(icon.getLayoutY()-2);
            }
          }
        }
      }
      mileagesTotal_T++;
    });
  }


}
