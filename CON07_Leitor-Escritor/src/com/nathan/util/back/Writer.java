package com.nathan.util.back;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: Writer
 * Funcao: Aplica uma solucao para o problema do Leitor-Escritor
 * ************************************************************** */
public abstract class Writer extends Thread{
  protected ArrayList buffer;
  private Semaphore db;

  /**
   * Construtor
   * @param db      Semaforo dp
   * @param buffer  Buffer
   */
  public Writer(Semaphore db, ArrayList buffer) {
    this.db = db;
    this.buffer = buffer;
  }

  /**
   * Obtem um dado para ser adicionado ao buffer,
   * deve ser definido por heranca
   * @return
   */
  protected abstract Object obtem_o_dado();

  /**
   * Escreve o dado obtido no buffer,
   * deve ser definido por heranca
   * @param dado
   */
  protected abstract void escreve_base_de_dados(Object dado);

  /**
   * Executa a acao de escrever
   */
  protected void write() {
    try {
      Object dado = obtem_o_dado();
      db.acquire();
      escreve_base_de_dados(dado);
      db.release();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
