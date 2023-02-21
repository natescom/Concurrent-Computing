package com.nathan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
public class Principal extends Application {
  /***************************************************************
   * Metodo: start
   * Funcao: Iniciar a janela
   * Parametros: primaryStage = Usado para definir as configuracoes
   * da janela
   * *************************************************************** */
  @Override
  public void start(Stage primaryStage) throws Exception{
    FxmlController fc = new FxmlController();
    Parent root = FXMLLoader.load(getClass().getResource("res/layout/layoutColoreh.fxml"));
    primaryStage.setTitle("Coloreh");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  /***************************************************************
   * Metodo: main
   * Funcao: Iniciar o programa
   * Parametros: args = Vetor de strings com os argumentos que darao
   * inicio ao programa
   * *************************************************************** */
  public static void main(String[] args) {
    launch(args);
  }
}
