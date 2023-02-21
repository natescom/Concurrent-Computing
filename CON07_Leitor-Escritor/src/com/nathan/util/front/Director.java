package com.nathan.util.front;

import com.nathan.util.back.Movie;
import com.nathan.util.back.Writer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: Director
 * Funcao: Produzir novos filmes pro catalogo
 * ************************************************************** */
public class Director extends Writer {
  private final ArrayList<Movie> productions; // Lista de filmes a serem produzidos
  private final GuiDirector gui;              // Objeto que define a interface grafica
  private Movie currentMovie;                 // Filme a ser produzido
  private int progress;                       // Progesso da producao

  /**
   * @param db            Semaforo db
   * @param buffer        Buffer, zona critica
   * @param productions   Lista de filmes a serem produzidos
   * @param gui           Interface grafica
   */
  public Director(Semaphore db, ArrayList buffer, ArrayList<Movie> productions, GuiDirector gui) {
    super(db, buffer);
    this.productions = productions;
    this.gui = gui;
  }

  /**
   * Pega um novo filme a ser produzido da lista
   * @return
   */
  private Movie novaProducao() {
    int i = new Random().nextInt(productions.size());
    Movie movie = productions.get(i);
    productions.remove(i);
    return movie;
  }

  @Override
  public Object obtem_o_dado() {
    return currentMovie;
  }

  @Override
  protected void escreve_base_de_dados(Object dado) {
    int index = 0;
    // O if a seguir serve para liberar espaco no buffer //
    if (buffer.size() >= 6) {                         // Verifica se meu buffer atingiu o tamanho maximo
      index = new Random().nextInt(buffer.size());    // Caso tenha, pego um index aleatorio
      Movie movie = (Movie) buffer.get(index);        // Pego do buffer o filme que tem esse indice
      buffer.remove(movie);                           // Removo do buffer
      productions.add(movie);                         // E devolvo a lista de producoes para ser produzido posteriormente
    }
    buffer.add(index,dado);                           // Adiciono o novo filme no espaco livre do buffer
    currentMovie = novaProducao();                    // Troco o filme atual para iniciar uma nova producao

    // Aplico alteracoes na interface //
    gui.publishPoster(currentMovie);
    gui.publishBuffer(buffer);
  }

  /**
   * Executa a producao do filme
   * @return Se ainda esta produzindo
   */
  private boolean produzindo() {
    if (progress == currentMovie.getProductionTime()) {
      write();
      progress = 0;
    } else {
      progress++;
    }
    gui.publishProgress(progress,currentMovie.getProductionTime());
    return true;
  }

  @Override
  public void run() {
    currentMovie = novaProducao();
    gui.publishPoster(currentMovie);
    gui.publishBuffer(buffer);
    if(buffer.isEmpty()) write();
    while (produzindo()) {
      try {
        sleep((long) (1000 / gui.getSlider().getValue()));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void interrupt() {
    productions.add(currentMovie);
    super.stop();
  }

}
