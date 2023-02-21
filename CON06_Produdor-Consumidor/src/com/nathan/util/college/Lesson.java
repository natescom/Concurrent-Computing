package com.nathan.util.college;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 13/05/2021
 * Ultima alteracao: 14/05/2021
 * Nome: Lesson
 * Funcao: Fornecer um assunto e o seu tamanho
 * ************************************************************** */
public class Lesson {
  public static final int GRANDE  = 100;       // Valor padrao para tamnho grande
  public static final int MEDIA   =  50;       // Valor padrao para tamnho medio
  public static final int PEQUENA =  25;       // Valor padrao para tamnho pequeno
  public static final int CURTO   =  10;       // Valor padrao para tamnho curto

  private String name;
  private int length;

  /**
   * Construtor
   * @param name      Nome do Assunto
   * @param length    Tamanho do Assunto
   */
  public Lesson(String name, int length) {
    this.name = name;
    this.length = length;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }
}
