package pc_trabalho03_201911925;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pc_trabalho03_201911925.util.Controller;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Principal
 * Funcao: Inicia a janela do programa
 * ***************************************************************/
public class Principal extends Application  {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("res/layout/layout_main.fxml"));
    primaryStage.setTitle("Arvore Genealogica"); // Titulo da janela
    primaryStage.setScene(new Scene(root)); // Coloco a cena na janela
    primaryStage.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    }); // Ao fechar a janela todos os processos sao fechados tambem
    primaryStage.show(); // Mostro a janela
  }


  public static void main(String[] args) {
    Controller controller = new Controller();
    launch(args);
  }
}
