package com.nathan.util.college;

import com.nathan.util.model.Consumidor;
import com.nathan.util.BodyGUI;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: Aluno
 * Funcao: Em sintese aplica alteracoes na interface apos remover um
 * assunto do buffer, percorre seu tamanho antes de pegar outro assunto
 * ************************************************************** */
public class Student extends Consumidor {
  private final BodyGUI gui;
  private Lesson currentLesson;
  private int conclusaoDoAssunto;

  /**
   * Construtor
   *
   * @param buffer Variavel compartilhada
   * @param full   Semaforo para cheio
   * @param empty  Semaforo para vazio
   * @param mutex  Semaforo para exclusao mutua
   * @param gui    Componentes graficos
   */
  public Student(ArrayList buffer, Semaphore full, Semaphore empty, Semaphore mutex, BodyGUI gui) {
    super(buffer, full, empty, mutex);
    this.gui = gui;
  }

  /**
   * Verifica se terminou de percorrer o tamanho do assunto
   *
   * @return (True - > ensinou)
   * (False -> nao terminou de ensinar)
   */
  public boolean completed() {
    if (currentLesson == null) return true;
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
  private boolean studying() {
    if (completed()) {
      try {
        gui.publishProgress(0, 0);
        gui.publishStatus("Estudando: ... ");
        consumir();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return true;
  }

  @Override
  protected void consumir_item(Object o) {
    currentLesson = (Lesson) o;
    gui.publishStatus("Estudando: " + currentLesson.getName());
    gui.removeItem();
  }

  @Override
  public void run() {
    while (studying()) {
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
