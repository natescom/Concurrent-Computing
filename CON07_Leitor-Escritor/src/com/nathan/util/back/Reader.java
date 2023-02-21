package com.nathan.util.back;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: Reader
 * Funcao: Aplica uma solucao para o problema do Leitor-Escritor
 * ************************************************************** */
public abstract class Reader extends Thread {
  protected ArrayList buffer;
  private Semaphore mutex;
  private Semaphore db;
  private Integer rc;

  /**
   * Construtor
   * @param mutex
   * @param db
   * @param rc
   * @param buffer
   */
  public Reader(Semaphore mutex, Semaphore db, Integer rc, ArrayList buffer) {
    this.mutex = mutex;
    this.db = db;
    this.rc = rc;
    this.buffer = buffer;
  }

  /**
   * Pega um objeto do buffer,
   * deve ser definido por heranca
   * @return O objeto do buffer
   */
  protected abstract Object le_base_de_dados();

  /**
   * Utiliza o dado lido,
   * deve ser definido por heranca
   * @param dado
   */
  protected abstract void utiliza_dado_lido(Object dado);

  /**
   * Executa a leitura do dado
   */
  protected void read(){
    try {
      mutex.acquire();
      rc++;
      if (rc == 1) db.acquire();
      mutex.release();
      Object dado = le_base_de_dados();
      mutex.acquire();
      rc--;
      if (rc == 0) db.release();
      mutex.release();
      utiliza_dado_lido(dado);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
