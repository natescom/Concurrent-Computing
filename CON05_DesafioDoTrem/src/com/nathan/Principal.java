package com.nathan;

import com.nathan.utils.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 10/04/2021
 * Nome: Principal
 * Funcao: Inicializa o programa
 * ************************************************************** */
public class Principal extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("res/layout/sample.fxml"));
    primaryStage.setTitle("Desafio do Trem");
    primaryStage.setScene(new Scene(root));
    primaryStage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    }); // Ao fechar a janela todos os processos sao fechados tambem
    primaryStage.show();
    Controller controller = new Controller();
  }


  public static void main(String[] args) {
    launch(args);
  }
}
