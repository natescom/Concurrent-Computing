package com.nathan.util.back;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 04/06/2021
 * Ultima alteracao: 04/06/2021
 * Nome: Movie
 * Funcao: Define as propriedades de um filme no programa
 * ************************************************************** */
public class Movie {
  private final String name;          // Nome
  private final int duration;         // Duracao
  private final int productionTime;   // Tempo que leva para ser produzido
  private final Image poster;         // Poster do filme
  private final Image movie;          // Gif de cenas do filme

  /**
   * Construtor
   * @param name            Nome
   * @param duration        Duracao
   * @param productionTime  Tempo de producao
   * @param poster          Poster do filme
   * @param movie           Gif do filme
   */
  public Movie(String name, int duration, int productionTime, Image poster, Image movie) {
    this.name = name;
    this.duration = duration;
    this.productionTime = productionTime;
    this.poster = poster;
    this.movie = movie;
  }

  public String getName() {
    return name;
  }

  public int getDuration() {
    return duration;
  }

  public int getProductionTime() {
    return productionTime;
  }

  public Image getPoster() {
    return poster;
  }

  public Image getMovie() {
    return movie;
  }
}
