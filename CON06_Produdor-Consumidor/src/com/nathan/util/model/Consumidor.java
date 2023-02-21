package com.nathan.util.model;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: Consumidor
 * Funcao: Reproduz a solucao classica para o problema do produtor/consumidor
 * ************************************************************** */
public abstract class Consumidor extends Thread {
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
  public Consumidor(ArrayList buffer, Semaphore full, Semaphore empty, Semaphore mutex) {
    this.buffer = buffer;
    this.full = full;
    this.empty = empty;
    this.mutex = mutex;
  }

  /**
   * Consome um item, como o consumo ocorre deve ser definida por heranca
   */
  protected abstract void consumir_item(Object o);

  /**
   * Remove um item do buffer
   * @return O item removido
   */
  private Object remover_item(){
    Object o = buffer.get(0);
    buffer.remove(0);
    return o;
  }

  /**
   * Executa a producao de um item com a utilizacao de semaforos para
   * solucionar os problemas com corrida
   * @throws InterruptedException
   */
  public void consumir() throws InterruptedException {
    full.acquire();
    mutex.acquire();
    Object item = remover_item();
    mutex.release();
    empty.release();
    consumir_item(item);
  }

}
