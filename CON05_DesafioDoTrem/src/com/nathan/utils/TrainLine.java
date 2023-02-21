package com.nathan.utils;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 08/04/2021
 * Nome: TrainLines
 * Funcao: Defini uma linha a ser percorrida pelo trem
 * ************************************************************** */
public class TrainLine {
  private String name;         // Nome da linha
  private int length;          // Comprimento da linha, cada unidade corresponde a um trilho
  private TrainLine next;     // Proxima Linha
  private TrainLine previous; // Linha anterior

  /**
   * Contutor
   * @param name Nome
   * @param length Comprimento
   */
  public TrainLine(String name, int length) {
    this.name = name;
    this.length = length;
  }

  /**
   * Cria as conexoes da linha
   * @param next      Linha posterior
   * @param previous  Linha anterior
   */
  public void conect(TrainLine next, TrainLine previous){
    this.next = next;
    this.previous = previous;
  }

  /**
   * Retorna a proxima linha a ser seguida
   * @param direction Sentido do trem
   * @return Proxima linha
   */
  public TrainLine nextLine(boolean direction){
    if(direction)
      return next;
    else
      return previous;
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
