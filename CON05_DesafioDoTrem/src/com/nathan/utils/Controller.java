package com.nathan.utils;

import com.nathan.protocolo.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 18/04/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da interface
 * ************************************************************** */
public class Controller implements Initializable {

  @FXML
  public ImageView img_DuckTrain;     // Imagem do DuckTrain
  public ImageView img_ArmadilloTrain;// Imagem do ArmadilloTrain
  public ImageView img_flagboyTF01;   // Imagem do bandeirante01 do tunel frei
  public ImageView img_flagboyTF02;   // Imagem do bandeirante02 do tunel frei
  public ImageView img_flagboyTO01;   // Imagem do bandeirante01 do tunel olivia
  public ImageView img_flagboyTO02;   // Imagem do bandeirante02 do tunel olivia
  public Button btn_start;            // Botao para comecar
  public Ellipse ico_duck;            // Icone do DuckTrain
  public Ellipse ico_armadillo;       // Icone do ArmadilloTrain
  public Label lbl_speed_duck;        // Label com a velocidade do DuckTrain
  public Label lbl_speed_armadillo;   // Label com a velocidade do ArmadilloTrain
  public Line line_img_routeX;        // Linha com as coordenadas verticais do percurso das imagens
  public Line line_ico_routeX;        // Linha com as coordenadas horizontais do percurso do icone
  public Line line_ico_routeY;        // Linha com as coordenadas verticais do percurso do icone
  public Slider sld_DuckTrain;        // Medidor de velocidade do DuckTrain
  public Slider sld_ArmadilloTrain;   // Medidor de velocidade do ArmadilloTrain
  public ChoiceBox cbx_Algoritmo;     // ChoiceBox pra selecionar o algoritmo
  public static Train[] processos;    // Vetor com os processos, utilizo no protocolo das bandeiras para saber a direcao do trem

  /**
   * Define acoes a serem executadas ao iniciar a classe
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // DETERMINO OS VALORES DO MEDIDOR DE VELOCIDADE //
    sld_ArmadilloTrain.setMax(50);
    sld_ArmadilloTrain.setMin(1);
    sld_ArmadilloTrain.setValue(10);
    sld_DuckTrain.setMax(50);
    sld_DuckTrain.setMin(1);
    sld_DuckTrain.setValue(20);

    // MOSTRO OS VALORES NAS RESPECTIVAS LABELS //
    Platform.runLater(() -> {
      lbl_speed_duck.setText("Velocidade: " + (int)sld_DuckTrain.getValue() + " t/s");
      lbl_speed_armadillo.setText("Velocidade: " + (int)sld_ArmadilloTrain.getValue() + " t/s");
    });

    // PREENCHO A COMBOBOX COM MEUS PROTOCOLOS //
    String[] opcoes = {"Bandeiras", "Variavel de travamento","Estrita Alternancia","Solucao de Peterson"};
    cbx_Algoritmo.setItems(FXCollections.observableArrayList(opcoes));
    cbx_Algoritmo.setValue(opcoes[0]);
    cbx_Algoritmo.setTooltip(new Tooltip("Selecione o protocolo de gerenciamento de zonas criticas"));

  }

  /**
   * Controla os eventos de clique da interface
   * @param event
   */
  @FXML
  public void onClick(ActionEvent event){
    // INICIALIZA O MOVIMENTO //
    if(event.getSource().equals(btn_start)){
      // --- CRIANDO AS LINHAS DE TREM --- //
      TrainLine linhaM01 = new TrainLine("M01",50);
      TrainLine linhaM02 = new TrainLine("M02",50);
      TrainLine linhaM03 = new TrainLine("M03",50);
      TrainLine linhaC01 = new TrainLine("C01",50);
      TrainLine linhaC02 = new TrainLine("C02",50);
      TrainLine linhaC03 = new TrainLine("C03",50);
      Tunnel tunelFreiBeijamin = new Tunnel("T. Frei",100, getProtocolo(cbx_Algoritmo, img_flagboyTF01,img_flagboyTF02));
      Tunnel tunelOliviaFlores = new Tunnel("T. Olivia",100, getProtocolo(cbx_Algoritmo, img_flagboyTO01,img_flagboyTO02));

      // --- CRIANDO CONEXOES ENTRE ELAS ---//
      linhaM01.conect(tunelFreiBeijamin,null);
      linhaM02.conect(tunelOliviaFlores,tunelFreiBeijamin);
      linhaM03.conect(null,tunelOliviaFlores);
      tunelFreiBeijamin.conect(linhaM02,linhaC01);
      tunelOliviaFlores.conect(linhaM03,linhaC02);
      linhaC01.conect(tunelFreiBeijamin,null);
      linhaC02.conect(tunelOliviaFlores,tunelFreiBeijamin);
      linhaC03.conect(null,tunelOliviaFlores);

      // --- CRIANDO OS TREM --- //
      Train duckTrain = new TrainGUI(0,"DuckTrain",sld_DuckTrain,true, linhaM01, img_DuckTrain, ico_duck, line_img_routeX, line_ico_routeX, line_ico_routeY);
      Train armadilloTrain = new TrainGUI(1,"ArmadilloTrain",sld_ArmadilloTrain,false, linhaC03, img_ArmadilloTrain, ico_armadillo, line_img_routeX, line_ico_routeX, line_ico_routeY);

      // --- SALVO OS TREM EM UM VETOR --- //
      processos = new Train[2];
      processos[0] = duckTrain;
      processos[1] = armadilloTrain;

      // --- DESABILITANDO OS BOTOES --- //
      btn_start.setDisable(true);
      cbx_Algoritmo.setDisable(true);

      // --- DANDO PARTIDA NO TREM --- //
      duckTrain.start();
      armadilloTrain.start();

      // --- THREAD QUE VERIFICA A EXECUÇÃO DOS TREM --- //
      Thread thread = new Thread(() ->{
        do {
          if(!duckTrain.isAlive() && !armadilloTrain.isAlive()){
            Platform.runLater(() -> {
              // --- PERMITO REINICIAR O PROGRAMA --- //
              btn_start.setDisable(false);
              btn_start.setText("Reiniciar");
              cbx_Algoritmo.setDisable(false);
              img_flagboyTF01.setVisible(false);
              img_flagboyTF02.setVisible(false);
              img_flagboyTO01.setVisible(false);
              img_flagboyTO02.setVisible(false);
            });
            break;
          }
        }while(true);
      });
      thread.start();
    }
  }

  /**
   * Eventos de segurar o mouse, usado nos medidores de velocidade
   * @param e
   */
  public void onMouseDragged(MouseEvent e) {
    if(e.getSource().equals(sld_DuckTrain)) {
      lbl_speed_duck.setText("Velocidade: " + (int) sld_DuckTrain.getValue() + " t/s");
    }
    if(e.getSource().equals(sld_ArmadilloTrain)) {
      lbl_speed_armadillo.setText("Velocidade: " + (int) sld_ArmadilloTrain.getValue() + " t/s");
    }
  }

  /**
   * Necessario para pegar o protocolo da caixa de escolha
   * @param choiceBox Caixa com as opcoes de escolha de protocolos
   * @param flag_Right Bandeira Direita para o protocolo das bandeiras
   * @param flag_Left  Bandeira Esquerda para o protocolo das bandeiras
   * @return Protocolo selecionado na ChoiceBox
   */
  public Protocol getProtocolo(ChoiceBox choiceBox, ImageView flag_Right , ImageView flag_Left){
    Protocol retorno;
    switch (choiceBox.getSelectionModel().getSelectedIndex()){
      case 0:
        retorno = new Bandeiras(flag_Right,flag_Left);
        break;
      case 1:
        retorno = new VariavelDeTravamento();
        break;
      case 2:
        retorno = new AlternanciaExplicita();
        break;
      case 3:
        retorno = new Peterson(processos.length);
        break;
      default:
        retorno = new VariavelDeTravamento();
    }
    return retorno;
  }

}
