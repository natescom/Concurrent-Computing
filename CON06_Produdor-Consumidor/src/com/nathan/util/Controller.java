package com.nathan.util;

import com.nathan.util.college.Student;
import com.nathan.util.college.Teacher;
import com.nathan.util.model.Consumidor;
import com.nathan.util.model.Produtor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 14/05/2021
 * Ultima alteracao: 15/05/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da interface
 * ************************************************************** */
public class Controller implements Initializable {

  public Button btn_iniciar;
  public ProgressBar pgrb_prof;
  public ProgressBar pgrb_al;
  public ImageView img_al;
  public ImageView img_prof;
  public Slider sld_prof;
  public Slider sld_al;
  public ListView listView_atividades;
  public Label lbl_speed_prof;
  public Label lbl_tamanho_prof;
  public Label lbl_porcent_prof;
  public Label lbl_ensinando_prof;
  public Label lbl_quantidade_prof;
  public Label lbl_temporestante_prof;
  public Label lbl_speed_al;
  public Label lbl_tamanho_al;
  public Label lbl_porcent_al;
  public Label lbl_estudando_al;
  public Label lbl_quantidade_al;
  public Label lbl_temporestante_al;
  public Button btn_prof_minus;
  public Button btn_prof_plus;
  public Button btn_al_minus;
  public Button btn_al_plus;
  public TableColumn tableColumn_prof_person;
  public TableColumn tableColumn_prof_lesson;
  public TableColumn tableColumn_prof_size;
  public TableColumn tableColumn_prof_speed;
  public TableColumn tableColumn_prof_percent;
  public TableColumn tableColumn_al_person;
  public TableColumn tableColumn_al_lesson;
  public TableColumn tableColumn_al_size;
  public TableColumn tableColumn_al_speed;
  public TableColumn tableColumn_al_percent;
  public TableView table_prof;
  public TableView table_al;
  public AnchorPane pane_janela;

  private ArrayList<Produtor> produtors;
  private ArrayList<Consumidor> consumidors;

  private ObservableList lista;
  private ArrayList buffer;
  private Semaphore full;
  private Semaphore empty;
  private Semaphore mutex;

  private ObservableList<Person> table_prof_list;
  private ObservableList<Person> table_al_list;

  /**
   * Eh executado ao iniciar do programa
   * @param location
   * @param resources
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // INSTANCIAS //
    produtors = new ArrayList<>();
    consumidors = new ArrayList<>();

    buffer = new ArrayList();
    full = new Semaphore(0);
    empty = new Semaphore(11);
    mutex = new Semaphore(1);
    table_prof_list = FXCollections.observableArrayList();
    table_al_list = FXCollections.observableArrayList();

    // ATOS //
    lista = FXCollections.observableArrayList();
    listView_atividades.setItems(lista);

    img_prof.setImage(Gallery.PROF_01);
    img_al.setImage(Gallery.AL_01);

    // Configuracoes da tabela //
    tableColumn_prof_person.setCellValueFactory(new PropertyValueFactory<Person, String>("people"));
    tableColumn_prof_lesson.setCellValueFactory(new PropertyValueFactory<Person, String>("lesson"));
    tableColumn_prof_size.setCellValueFactory(new PropertyValueFactory<Person, String>("size"));
    tableColumn_prof_speed.setCellValueFactory(new PropertyValueFactory<Person, String>("speed"));
    tableColumn_prof_percent.setCellValueFactory(new PropertyValueFactory<Person, String>("percent"));

    tableColumn_al_person.setCellValueFactory(new PropertyValueFactory<Person, String>("people"));
    tableColumn_al_lesson.setCellValueFactory(new PropertyValueFactory<Person, String>("lesson"));
    tableColumn_al_size.setCellValueFactory(new PropertyValueFactory<Person, String>("size"));
    tableColumn_al_speed.setCellValueFactory(new PropertyValueFactory<Person, String>("speed"));
    tableColumn_al_percent.setCellValueFactory(new PropertyValueFactory<Person, String>("percent"));

    table_prof.setItems(table_prof_list);
    table_al.setItems(table_al_list);

    tableColumn_prof_speed.setCellFactory(TextFieldTableCell.forTableColumn());

    // Desabilito por padrao os botoes de diminuir a quantidade de prodruto/consumido //
    btn_prof_minus.setDisable(true);
    btn_al_minus.setDisable(true);

    // Determino os valores dos sliders  //
    int sldMAX = 10;
    sld_prof.setValue(2);
    sld_prof.setMax(sldMAX);
    sld_prof.setMin(1);
    sld_al.setValue(2);
    sld_al.setMax(sldMAX);
    sld_al.setMin(1);

    lbl_speed_prof.setText("Velocidade: " + ((int) sld_prof.getValue()));
    lbl_speed_al.setText("Velocidade: " + ((int) sld_al.getValue()));

    // COLOCO UM PRODUTOR NO CONJUNTO DE PRODUTORES //
    BodyGUI professorGUI = new BodyGUI(lbl_ensinando_prof, lbl_temporestante_prof, lbl_porcent_prof, lbl_tamanho_prof, sld_prof, pgrb_prof, lista);
    Teacher teacher = new Teacher(buffer, full, empty, mutex, professorGUI);
    produtors.add(teacher);
    // COLOCO UM CONSUMIDOR DO CONJUNTO DE CONSUMIDORES //
    BodyGUI alunoGUI = new BodyGUI(lbl_estudando_al, lbl_temporestante_al, lbl_porcent_al, lbl_tamanho_al, sld_al, pgrb_al, lista);
    Student student = new Student(buffer, full, empty, mutex, alunoGUI);
    consumidors.add(student);
  }

  /**
   * Cria e adiciona um slide nas coordenadas da tabela
   *
   * @param left Posicao do Slider, se eh na tabela da esquerda ou direita
   * @return O proprio slider criado
   */
  public Slider createSlider(boolean left) {
    Slider slider = new Slider(1, 10, 2);
    slider.setPrefWidth(66);
    slider.setPrefHeight(14);
    if (left) {
      slider.setLayoutX(238);
      slider.setLayoutY(426 + 24 * (produtors.size() - 1));
    } else {
      slider.setLayoutX(600);
      slider.setLayoutY(426 + 24 * (consumidors.size() - 1));
    }
    pane_janela.getChildren().add(slider);
    return slider;
  }

  /**
   * Eventos de segurar o mouse, usado nos medidores de velocidade
   *
   * @param event
   */
  public void onMouseDragged(MouseEvent event) {
    if (event.getSource().equals(sld_prof)) {
      int nDeImgs = 4;
      if (sld_prof.getValue() < sld_prof.getMax() / nDeImgs) img_prof.setImage(Gallery.PROF_01);
      if (sld_prof.getValue() < (sld_prof.getMax() * 2) / nDeImgs && sld_prof.getValue() >= sld_prof.getMax() / nDeImgs)
        img_prof.setImage(Gallery.PROF_02);
      if (sld_prof.getValue() < (sld_prof.getMax() * 3) / nDeImgs && sld_prof.getValue() >= (sld_prof.getMax() * 2) / nDeImgs)
        img_prof.setImage(Gallery.PROF_03);
      if (sld_prof.getValue() < (sld_prof.getMax() * 4) / nDeImgs && sld_prof.getValue() >= (sld_prof.getMax() * 3) / nDeImgs)
        img_prof.setImage(Gallery.PROF_04);
      lbl_speed_prof.setText("Velocidade: " + ((int) sld_prof.getValue()));
    }
    if (event.getSource().equals(sld_al)) {
      int nDeImgs = 6;
      if (sld_al.getValue() < sld_al.getMax() / nDeImgs) img_al.setImage(Gallery.AL_01);
      if (sld_al.getValue() < (sld_al.getMax() * 2) / nDeImgs && sld_al.getValue() >= sld_al.getMax() / nDeImgs)
        img_al.setImage(Gallery.AL_02);
      if (sld_al.getValue() < (sld_al.getMax() * 3) / nDeImgs && sld_al.getValue() >= (sld_al.getMax() * 2) / nDeImgs)
        img_al.setImage(Gallery.AL_03);
      if (sld_al.getValue() < (sld_al.getMax() * 4) / nDeImgs && sld_al.getValue() >= (sld_al.getMax() * 3) / nDeImgs)
        img_al.setImage(Gallery.AL_04);
      if (sld_al.getValue() < (sld_al.getMax() * 5) / nDeImgs && sld_al.getValue() >= (sld_al.getMax() * 4) / nDeImgs)
        img_al.setImage(Gallery.AL_05);
      if (sld_al.getValue() < sld_al.getMax() && sld_al.getValue() >= (sld_al.getMax() * 5) / nDeImgs)
        img_al.setImage(Gallery.AL_06);
      lbl_speed_al.setText("Velocidade: " + ((int) sld_al.getValue()));
    }
  }

  /**
   * Define os eventos de clique na interface, usado nos botoes
   *
   * @param event
   */
  public void onClick(ActionEvent event) {
    if (event.getSource().equals(btn_iniciar)) {
      btn_iniciar.setDisable(true);
      produtors.forEach(produtor -> produtor.start());
      consumidors.forEach(consumidor -> consumidor.start());
    }
    if (event.getSource().equals(btn_prof_minus)) {
      if (produtors.size() > 1) {
        Teacher teacher = (Teacher) produtors.get(produtors.size() - 1);
        teacher.stop();
        pane_janela.getChildren().remove(teacher.getGui().getSlider());
        produtors.remove(teacher);
        lbl_quantidade_prof.setText("Professores: " + produtors.size());
        table_prof_list.remove(table_prof_list.size() - 1);
        if (produtors.size() == 1 && consumidors.size() == 1) {
          Node node = (Node) event.getSource();
          node.getScene().getWindow().setHeight(420);
        }
        if (btn_prof_plus.isDisabled()) {
          btn_prof_plus.setDisable(false);
        }
        if (produtors.size() == 1)
          btn_prof_minus.setDisable(true);
      }
    }
    if (event.getSource().equals(btn_prof_plus)) {
      if (produtors.size() < 8) {
        Person person = new Person();
        BodyGUI bodyGUI = new BodyGUI(person, createSlider(true), lista);
        Teacher teacher = new Teacher(buffer, full, empty, mutex, bodyGUI);
        produtors.add(teacher);
        lbl_quantidade_prof.setText("Professores: " + produtors.size());
        table_prof_list.add(person);
        person.setPeople(String.valueOf(produtors.size()));

        if (produtors.size() > 1 || consumidors.size() > 1) {
          Node node = (Node) event.getSource();
          node.getScene().getWindow().setHeight(670);
        }
        if (btn_prof_minus.isDisabled()) {
          btn_prof_minus.setDisable(false);
        }
        if (produtors.size() == 8) {
          btn_prof_plus.setDisable(true);
        }
        if (btn_iniciar.isDisable()) {
          teacher.start();
        }
      }
    }
    if (event.getSource().equals(btn_al_minus)) {
      if (consumidors.size() > 1) {
        Student student = (Student) consumidors.get(consumidors.size() - 1);
        student.stop();
        pane_janela.getChildren().remove(student.getGui().getSlider());
        consumidors.remove(student);
        lbl_quantidade_al.setText("Alunos: " + consumidors.size());
        table_al_list.remove(table_al_list.size() - 1);
        if (produtors.size() == 1 && consumidors.size() == 1) {
          Node node = (Node) event.getSource();
          node.getScene().getWindow().setHeight(420);
        }
        if (btn_al_plus.isDisabled()) {
          btn_al_plus.setDisable(false);
        }
        if (consumidors.size() == 1)
          btn_al_minus.setDisable(true);
      }
    }
    if (event.getSource().equals(btn_al_plus)) {
      if (consumidors.size() < 8) {
        Person person = new Person();
        BodyGUI bodyGUI = new BodyGUI(person, createSlider(false), lista);
        Student student = new Student(buffer, full, empty, mutex, bodyGUI);
        consumidors.add(student);
        lbl_quantidade_al.setText("Alunos: " + consumidors.size());
        table_al_list.add(person);
        person.setPeople(String.valueOf(consumidors.size()));

        if (produtors.size() > 1 || consumidors.size() > 1) {
          Node node = (Node) event.getSource();
          node.getScene().getWindow().setHeight(670);
        }
        if (btn_al_minus.isDisabled()) {
          btn_al_minus.setDisable(false);
        }
        if (consumidors.size() == 8) {
          btn_al_plus.setDisable(true);
        }
        if (btn_iniciar.isDisable()) {
          student.start();
        }
      }
    }
  }
}


