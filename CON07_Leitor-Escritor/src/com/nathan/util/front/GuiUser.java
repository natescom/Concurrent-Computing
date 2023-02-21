package com.nathan.util.front;

import com.nathan.util.back.Movie;
import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: GuiUser
 * Funcao: Gerencia os objetos da interface e forcene metodos para
 * atualiza-los
 * ************************************************************** */
public class GuiUser {
  private final TitledPane pane;
  private final ImageView screen;
  private final Slider slider;
//  private final Rectangle poster;
  private final ImageView poster;

  /**
   * Construtor
   * @param pane             Painel com o tela do filme
   * @param screen           Imagem para mostrar o filme
   * @param poster           Retangulo com o poster do filme
   * @param slider           Slider com a velocidade de reproducao do filme
   */
  public GuiUser(TitledPane pane, ImageView screen, ImageView poster, Slider slider) {
    this.pane = pane;
    this.screen = screen;
    this.poster = poster;
    this.slider = slider;
  }

  /**
   * Publica o poster do filme a ser assistido e ele
   * @param movie
   */
  public void publishMovie(Movie movie) {
    Platform.runLater(() -> {
      poster.setImage(movie.getPoster());
//      poster.setFill(new ImagePattern(movie.getPoster()));
      screen.setImage(movie.getMovie());
    });
  }

  /**
   * Publica o nivel de conclusao do filme assistido
   * @param current Progresso corrente
   * @param total   Progresso maximo
   */
  public void publishProgress(double current, double total) {
    Platform.runLater(() -> pane.setText(formatSeconds((int) current) + " / " + formatSeconds((int) total)));
  }

  /**
   * Transforma um numero inteiro em uma string com minutos e segundos
   *
   * @param result
   * @return
   */
  private String formatSeconds(Integer result) {
    String string;
//    System.out.print(String.format("%02d",result/3600)+":");
    string = String.format("%02d", result / 60 % 60) + ":";
    string += String.format("%02d", result % 60);
    return string;
  }

  public Slider getSlider() {
    return slider;
  }
}
