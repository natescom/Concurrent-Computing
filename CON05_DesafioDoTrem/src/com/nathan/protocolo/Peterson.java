package com.nathan.protocolo;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: Peterson
 * Funcao: Utiliza a solucao de Peterson para resolver os problemas
 * da regiao critica
 * ************************************************************** */
public class Peterson extends Protocol {
  private int turn;
  private boolean interested[];

  public Peterson(int i) {
    interested = new boolean[i];
  }

  @Override
  public void enter_region(int processID) {
    int other = 1 - processID;
    interested[processID] = true;
    turn = processID;
    while (turn == processID && interested[other]){
      System.out.print("");
    }
  }

  @Override
  public void leave_region(int processID) {
    interested[processID] = false;
  }

}
