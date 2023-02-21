package com.nathan.util.front;

import com.nathan.util.back.Movie;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: GuiDirector
 * Funcao: Gerencia os objetos da interface e forcene metodos para
 * atualiza-los
 * ************************************************************** */
public class GuiDirector {
  private final ObservableList observableBuffer;
  private final ProgressBar progressBar;
  private final ImageView imageView;
  private final ImageView movieposter;
//  private final Rectangle rectangle;
  private final Slider slider;
  private final Label label;

  /**
   *  @param observableBuffer  Lista usada para imprimir o conteudo do buffer
   * @param progressBar       Barra de progresso
   * @param imageView         Imagem do diretor
   * @param movieposter       Objeto para imprimir o poster do filme
   * @param slider            Slider com a velocidade da producao
   * @param label             Label para publicar o tempo restante
   */
  public GuiDirector(ObservableList observableBuffer, ProgressBar progressBar, ImageView imageView, ImageView movieposter, Slider slider, Label label) {
    this.observableBuffer = observableBuffer;
    this.progressBar = progressBar;
    this.imageView = imageView;
    this.movieposter = movieposter;
    this.slider = slider;
    this.label = label;
  }

  /**
   * Publica o poster do filme a ser produzido na interface
   * @param movie
   */
  public void publishPoster(Movie movie) {
    Platform.runLater(() -> movieposter.setImage(movie.getPoster()));
//    Platform.runLater(() -> this.rectangle.setFill(new ImagePattern(movie.getPoster())));
  }

  /**
   * Publica o progresso da producao de filme na interface
   * @param current   Progresso atual
   * @param total     Progresso maximo
   */
  public void publishProgress(double current, double total) {
    double frequenciaAbsoluta = current / total;
    int tempoRestante = (int) ((total - current) / slider.getValue());
    Platform.runLater(() -> {
      progressBar.setProgress(frequenciaAbsoluta);
      label.setText("Termino em " + formatSeconds(tempoRestante));
    });
  }

  /**
   * Publica o buffer na interface
   * @param buffer
   */
  public void publishBuffer(ArrayList buffer) {
    Platform.runLater(() -> {
      observableBuffer.clear();
      buffer.forEach(o -> {
        ImageView poster = new ImageView(((Movie)o).getPoster());
        poster.setFitWidth(241);
        poster.setFitHeight(136);
        observableBuffer.add(poster);
//        Rectangle rectangle = new Rectangle(241,136);
//        rectangle.setArcHeight(15);
//        rectangle.setArcWidth(15);
//        rectangle.setFill(new ImagePattern(((Movie) o).getPoster()));
//        observableBuffer.add(rectangle);
     });
    });
  }

  /**
   * Transforma um numero inteiro em uma string com minutos e segundos
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
