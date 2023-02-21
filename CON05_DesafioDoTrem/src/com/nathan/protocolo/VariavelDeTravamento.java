package com.nathan.protocolo;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: VariavelDeTravamento
 * Funcao: Utiliza variavel de travamento para resolver os problemas
 * da regiao critica
 * ************************************************************** */
public class VariavelDeTravamento extends Protocol {
  private int trava;

  public VariavelDeTravamento(){
    trava = 0;
  }

  @Override
  public void enter_region(int processID) {
    while (trava!=0){
      System.out.print("");
    }
    trava = 1;
  }

  @Override
  public void leave_region(int processID) {
    trava = 0;
  }
}
