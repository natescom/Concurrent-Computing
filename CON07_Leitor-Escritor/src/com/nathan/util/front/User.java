package com.nathan.util.front;

import com.nathan.util.back.Movie;
import com.nathan.util.back.Reader;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: User
 * Funcao: Define o papel do Leitor no programa, nesse caso, como
 * usuario
 * ************************************************************** */
public class User extends Reader {
  private final GuiUser gui;    // Objeto com os elementos da interface
  private Movie currentMovie;   // Filme que estah vendo no momento
  private int progress;         // Progresso do assistir ao filme

  /**
   * Construtor
   * @param mutex     Semaforo da exclusao mutua
   * @param db        Semaforo db
   * @param rc        Semaforo rc
   * @param buffer    Regiao critica
   * @param gui       Objeto com os elementos da interface
   */
  public User(Semaphore mutex, Semaphore db, Integer rc, ArrayList buffer, GuiUser gui) {
    super(mutex, db, rc, buffer);
    this.gui = gui;
  }

  @Override
  protected Object le_base_de_dados() {
    return buffer.get(new Random().nextInt(buffer.size()));
  }

  @Override
  public void utiliza_dado_lido(Object dado) {
    currentMovie = (Movie) dado;
    gui.publishMovie(currentMovie);
  }

  /**
   * Executa a acao de ver o filme
   * @return Se ainda esta vendo filmes
   */
  public boolean watch(){
    if(progress == currentMovie.getDuration()) {
      read();
      progress = 0;
    }else{
      progress++;
    }
    gui.publishProgress(progress, currentMovie.getDuration());
    return true;
  }

  @Override
  public void run() {
    read();
    while (watch()){
      try {
        sleep((long) (1000/gui.getSlider().getValue()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
