package com.nathan.util.front;

import com.nathan.util.back.Movie;
import com.nathan.util.back.Gallery;
import com.nathan.util.back.Writer;
import com.nathan.util.back.Reader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/05/2021
 * Ultima alteracao: 04/06/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da interface
 * ************************************************************** */
public class Controller implements Initializable {

  public ListView list_buffer;
  public ListView list_reader;
  public ListView list_writer;
  public Button btn_moreReader;
  public Button btn_moreWriter;
  public Button btn_minusReader;
  public Button btn_minusWriter;

  private ObservableList observableBuffer;
  private ObservableList observableReader;
  private ObservableList observableWriter;

  private ArrayList<Movie> movies;

  private ArrayList buffer;
  private Semaphore mutex;
  private Semaphore db;
  private Integer rc;

  private ArrayList<Writer> writers;
  private ArrayList<Reader> readers;

  /**
   * Cria leitores com sua respectiva representacao da interface
   */
  private void createReader() {
    // INSTANCIAS //
    GridPane gridPane = new GridPane();
    TitledPane titledPane = new TitledPane();
    ImageView imageView = new ImageView();
    ImageView poster = new ImageView();
//    Rectangle poster = new Rectangle(175, 80);
    Slider slider = new Slider();
    // ATOS //
//    poster.setArcHeight(15);
//    poster.setArcWidth(15);
    gridPane.setVgap(10);
    imageView.setFitWidth(175);
    imageView.setFitHeight(100);
    poster.setFitWidth(175);
    poster.setFitHeight(100);
    slider.setMin(1);
    slider.setMax(500);
    slider.setValue(100);
    titledPane.setText("Tela " + (observableReader.size() + 1) + ":");
    titledPane.setExpanded(true);
    titledPane.setContent(imageView);
    gridPane.add(titledPane, 0, 1);
    gridPane.add(slider, 0, 2);
    gridPane.add(poster, 0, 0);
    observableReader.add(gridPane);
    GuiUser guiUser = new GuiUser(titledPane, imageView, poster, slider);
    readers.add(new User(mutex, db, rc, buffer, guiUser));
  }

  /**
   * Cria escritores com sua respectiva representacao na interface
   */
  private void createWriter() {
    // INSTANCIAS //
    GridPane gridPane = new GridPane();
    Label label = new Label();
    Slider slider = new Slider();
    ImageView imageView = new ImageView();
    ImageView poster = new ImageView();
//    Rectangle poster = new Rectangle(175, 80);
    ProgressBar progressBar = new ProgressBar();
    // ATOS //
    imageView.setImage(Gallery.USER_SMILE_GREEN);
    imageView.setFitWidth(40);
    imageView.setFitHeight(40);
    poster.setFitWidth(175);
    poster.setFitHeight(100);
//    poster.setArcHeight(15);
//    poster.setArcWidth(15);
    label.setStyle("-fx-text-fill: #ffffff;");
    progressBar.setStyle("-fx-accent: red");
    slider.setMin(1);
    slider.setMax(1000);
    slider.setValue(250);
    gridPane.setVgap(10);
    gridPane.setHgap(10);
    gridPane.add(imageView, 0, 0,1,2);
    gridPane.add(label, 1, 0);
    gridPane.add(progressBar, 1, 1);
    gridPane.add(poster, 0, 2,2,1);
    gridPane.add(slider, 0, 3,2,1);
    observableWriter.add(gridPane);
    GuiDirector guiDirector = new GuiDirector(observableBuffer, progressBar, imageView, poster, slider, label);
    writers.add(new Director(db, buffer, movies, guiDirector));
  }

  /**
   * Eh executado ao iniciar o programa
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Thread para indicar carregamento na inicializacao //
    Thread carregando = new Thread(){
      int i = 0;
      @Override
      public void run() {
        System.out.println("- Tudum -");
        while (true){
          System.out.print("Carregando ");
          for (int j = 0; j < i; j++) System.out.print(".");
          try {
            sleep(500);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          for (int m = 0; m < 4; m++) System.out.print(" ");
          for (int j = 0; j < 15 + i; j++) System.out.print("\b");
          if(i>=3) i = 0;
          else i++;
        }
      }
      @Override
      public void interrupt() {
        for (int j = 0; j < 15 + i; j++) System.out.print("\b");
        System.out.print("Bem vindo");
        for (int m = 0; m < 5; m++) System.out.print(" ");
        System.out.print("\n");
        super.stop();
      }
    };
    carregando.start();


    // INSTANCIAS //
    buffer = new ArrayList();
    mutex = new Semaphore(1);
    db = new Semaphore(1);
    rc = 0;

    writers = new ArrayList<>();
    readers = new ArrayList<>();
    movies = new ArrayList<>();

    observableBuffer = FXCollections.observableArrayList();
    list_buffer.setItems(observableBuffer);

    observableReader = FXCollections.observableArrayList();
    list_reader.setItems(observableReader);

    observableWriter = FXCollections.observableArrayList();
    list_writer.setItems(observableWriter);


    // ATOS //
    movies.add(new Movie("Dark", 1100, 4000, Gallery.POSTER_DARK, Gallery.MOVIE_DARK));
    movies.add(new Movie("Big Mouth", 1200, 4000, Gallery.POSTER_BIGMOUTH, Gallery.MOVIE_BIGMOUTH));
    movies.add(new Movie("Demolidor", 1125, 2500, Gallery.POSTER_DEMOLIDOR, Gallery.MOVIE_DEMOLIDOR));
    movies.add(new Movie("Final Space", 1009, 4000, Gallery.POSTER_FINALSPACE, Gallery.MOVIE_FINALSPACE));
    movies.add(new Movie("Demon Slayer", 1200, 5000, Gallery.POSTER_KIMETSU, Gallery.MOVIE_KIMETSU));
    movies.add(new Movie("Black Mirror", 1050, 2500, Gallery.POSTER_BLACKMIRROR, Gallery.MOVIE_BLACKMIRROR));
    movies.add(new Movie("House of Cards", 800, 3000, Gallery.POSTER_HOUSEOFCARDS, Gallery.MOVIE_HOUSEOFCARDS));
    movies.add(new Movie("Stranger Things", 2400, 5000, Gallery.POSTER_STRANGERTHINGS, Gallery.MOVIE_STRANGERTHINGS));
    movies.add(new Movie("La Casa de Papel", 3000, 7000, Gallery.POSTER_LACASADEPAPEL, Gallery.MOVIE_LACASADEPAPEL));

    createReader();
    createWriter();

    writers.forEach(writer -> writer.start());
    new Thread(() -> {
      while (buffer.isEmpty()) {
        System.out.print("");
      }
      if (!readers.isEmpty())
        readers.forEach(reader -> reader.start());
    }).start();

    carregando.interrupt();
  }

  /**
   * Aciona os eventos de botao da interface
   * @param event
   */
  public void onPressed(ActionEvent event) {
    if (event.getSource().equals(btn_moreReader)) {
      createReader();
      readers.get(readers.size() - 1).start();
    }
    if (event.getSource().equals(btn_minusReader)) {
      if (!readers.isEmpty()) {
        readers.get(0).stop();
        readers.remove(0);
        observableReader.remove(0);
      }
    }
    if (event.getSource().equals(btn_moreWriter)) {
      if (writers.size() < 3) {
        createWriter();
        writers.get(writers.size() - 1).start();
      }
    }
    if (event.getSource().equals(btn_minusWriter)) {
      if (!writers.isEmpty()) {
        writers.get(0).interrupt();
        writers.remove(0);
        observableWriter.remove(0);
      }
    }
  }
}
