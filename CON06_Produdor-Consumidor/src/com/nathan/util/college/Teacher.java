package com.nathan.util.college;

import com.nathan.util.BodyGUI;
import com.nathan.util.model.Produtor;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: Teacher
 * Funcao: Em sintese aplica alteracoes na interface enquanto percorre
 * um Assunto ate que atinja seu tamanho e passe para o buffer
 * ************************************************************** */
public class Teacher extends Produtor {
  private final BodyGUI gui;
  private int conclusaoDoAssunto;
  private Lesson currentLesson;

  /**
   * Construtor
   *
   * @param buffer Variavel compartilhada
   * @param full   Semaforo para cheio
   * @param empty  Semaforo para vazio
   * @param mutex  Semaforo para exclusao mutua
   * @param gui    Componentes graficos
   */
  public Teacher(ArrayList buffer, Semaphore full, Semaphore empty, Semaphore mutex, BodyGUI gui) {
    super(buffer, full, empty, mutex);
    this.gui = gui;
    currentLesson = selectLesson();
    gui.publishStatus("Ensinando: " + currentLesson.getName());
  }

  /**
   * Retorna um objeto Lesson
   *
   * @return
   */
  private Lesson selectLesson() {
    switch (new Random().nextInt(8)) {
      case 0:
        return new Lesson("Produtor/Consumidor", Lesson.PEQUENA);
      case 1:
        return new Lesson("Jantar dos Filosofos", Lesson.GRANDE);
      case 2:
        return new Lesson("Barbeiro Dorminhoco", Lesson.MEDIA);
      case 3:
        return new Lesson("Leitor/Escritor", Lesson.CURTO);
      case 4:
        return new Lesson("Recurso Compartilhado", Lesson.PEQUENA);
      case 5:
        return new Lesson("Thread", Lesson.PEQUENA);
      case 6:
        return new Lesson("Fork", Lesson.CURTO);
      case 7:
        return new Lesson("Semaforo", Lesson.MEDIA);
      default:
        return new Lesson("Programacao Paralela", Lesson.MEDIA);
    }
  }

  /**
   * Verifica se terminou de percorrer o tamanho do assunto
   *
   * @return (True - > ensinou)
   * (False -> nao terminou de ensinar)
   */
  private boolean completed() {
    if (conclusaoDoAssunto >= currentLesson.getLength()) {
      conclusaoDoAssunto = 0;
      return true;
    }
    gui.publishProgress(conclusaoDoAssunto, currentLesson.getLength());
    conclusaoDoAssunto++;
    return false;
  }

  /**
   * Executa tudo que deve ocorrer durante a aula
   *
   * @return Se a aula acabou
   */
  private boolean classIsOver() {
    if (completed()) {
      try {
        gui.publishProgress(currentLesson.getLength(), currentLesson.getLength());
        produzir();
        gui.publishItem(currentLesson.getName());
        currentLesson = selectLesson();
        gui.publishStatus("Ensinando: " + currentLesson.getName());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  @Override
  protected Object produzir_item() {
    return currentLesson;
  }

  @Override
  public void run() {
    while (!classIsOver()) {
      try {
        sleep(1000 / (int) gui.getSlider().getValue());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public BodyGUI getGui() {
    return gui;
  }
}
