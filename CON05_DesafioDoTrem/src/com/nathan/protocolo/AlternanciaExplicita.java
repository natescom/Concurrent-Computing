package com.nathan.protocolo;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: AlternanciaExplicita
 * Funcao: Utiliza a solucao de Alternancia Explicita / Estrica Alternancia
 * para resolver o problema da regiao critica
 * ************************************************************** */
public class AlternanciaExplicita extends Protocol {
  private int turn;

  public AlternanciaExplicita() {
    this.turn = 0;
  }

  @Override
  public void enter_region(int processID) {
    while (turn != processID){
      System.out.print("");
    }
  }

  @Override
  public void leave_region(int processID) {
    turn = processID +1;
  }

}
