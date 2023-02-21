package com.nathan.util;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: BodyGUI
 * Funcao: 'Corpo' com todos os atributos e metodos utilizadados para
 * representar um Produtor ou Consumidor na interface
 * ************************************************************** */
public class BodyGUI {
  private Label time;                         // Label para informar estados da pessoa
  private Label status;                       // Label para informar estados da pessoa
  private Label percent;                      // Label para informar a porcentagem concluida
  private Label tamanho;                      // Label para informar o tamanho da materia
  private Slider slider;                      // Slider com a velocidade de ensinamento/aprendizagem
  private ProgressBar progressBar;            // Barra com o progresso de ensinamento/aprendizagem
  private ObservableList observableList;      // Lista que serve para imprimir o conteudo do buffer
  private Person person;                      // Modelo com os atributos para representacao na tabela

  /**
   * Construtor
   *
   * @param status         Label para informar estados da pessoa
   * @param time           Label para informar o tempo restante
   * @param percent        Label para informar a porcentagem concluida
   * @param tamanho        Label para informar o tamanho da materia
   * @param slider         Slider com a velocidade de ensinamento/aprendizagem
   * @param progressBar    Barra com o progresso de ensinamento/aprendizagem
   * @param observableList Lista que serve para imprimir o conteudo do buffer
   */
  public BodyGUI(Label status, Label time, Label percent, Label tamanho, Slider slider, ProgressBar progressBar, ObservableList observableList) {
    this.time = time;
    this.status = status;
    this.percent = percent;
    this.tamanho = tamanho;
    this.slider = slider;
    this.progressBar = progressBar;
    this.observableList = observableList;
  }

  /**
   * Contrutor para Produtor/Consumidor representado na tabela
   *
   * @param person         Modelo com os atributos para representacao na tabela
   * @param slider         Slider com a velocidade de ensinamento/aprendizagem
   * @param observableList Lista que serve para imprimir o conteudo do buffer
   */
  public BodyGUI(Person person, Slider slider, ObservableList observableList) {
    this.person = person;
    this.slider = slider;
    this.observableList = observableList;
  }

  /**
   * Publica um texto na label status
   *
   * @param status Texto a ser impresso
   */
  public void publishStatus(String status) {
    Platform.runLater(() -> {
      if (this.status == null) {
        person.setLesson(status.split(": ")[1]);
      } else {
        this.status.setText(status);
      }
    });
  }

  /**
   * Publica o progresso na ProgressBar
   *
   * @param current Numero que representa a parte concluida da acao
   * @param total   Numero que representa a parte total da acao
   */
  public void publishProgress(double current, double total) {
    Platform.runLater(() -> {
      double decimal = current / total;
      if (progressBar != null) {
        progressBar.setProgress(decimal);
      }
      if (percent != null) {
        percent.setText((int) (decimal * 100) + "%");
      } else {
        person.setPercent((int) (decimal * 100) + "%");
      }
      int tempoRestante = (int) ((total - current) / slider.getValue());
      if (time != null) {
        time.setText("Termino em: " + tempoRestante + "s");
      }
      if (tamanho != null) {
        tamanho.setText("Tamanho: " + (int) total);
      } else {
        person.setSize(String.valueOf((int) total));
      }
    });
  }

  /**
   * Publica um item no final da lista da interface
   *
   * @param o Item a ser impresso
   */
  public void publishItem(Object o) {
    Platform.runLater(() -> observableList.add(o));
  }

  /**
   * Remove o item que esta no topo da lista da interface
   */
  public void removeItem() {
    Platform.runLater(() -> {
      if (!observableList.isEmpty()) observableList.remove(0);
    });
  }

  public Slider getSlider() {
    return slider;
  }
}
