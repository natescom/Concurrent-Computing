package com.nathan.util.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: Produtor
 * Funcao: Reproduz a solucao classica para o problema do produtor/consumidor
 * ************************************************************** */

public abstract class Produtor extends Thread {
  private final ArrayList buffer;
  private final Semaphore full;
  private final Semaphore empty;
  private final Semaphore mutex;

  /**
   * Construtor
   * @param buffer    Variavel compartilhada
   * @param full      Semaforo para cheio
   * @param empty     Semaforo para vazio
   * @param mutex     Semaforo para exclusao mutua
   */
  public Produtor(ArrayList buffer, Semaphore full, Semaphore empty, Semaphore mutex) {
    this.buffer = buffer;
    this.full = full;
    this.empty = empty;
    this.mutex = mutex;
  }

  /**
   * Produz um item, como a producao ocorre deve ser definida por heranca
   * @return Item produzido
   */
  protected abstract Object produzir_item();

  /**
   * Insere um item no buffer
   * @param o
   */
  private void inserir_item(Object o){
    buffer.add(o);
  }

  /**
   * Executa a producao de um item com a utilizacao de semaforos para
   * solucionar os problemas com corrida
   * @throws InterruptedException
   */
  protected void produzir() throws InterruptedException {
    Object item = produzir_item();
    empty.acquire();
    mutex.acquire();
    inserir_item(item);
    mutex.release();
    full.release();
  }
}
