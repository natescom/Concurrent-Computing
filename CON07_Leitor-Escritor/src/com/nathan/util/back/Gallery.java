package com.nathan.util.back;

import javafx.scene.image.Image;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Gallery
 * Funcao: Lista e instanica todas as imagens para serem utilizadas
 * no ImageView da interface
 * ***************************************************************/
public abstract class Gallery {
  private static final String PATH = "/com/nathan/res/img";

  public static final Image USER_SMILE_GREEN = new Image(Gallery.class.getResourceAsStream(PATH +"/profile.png"));

  public static final Image POSTER_DARK = new Image(PATH +"/netflix-dark.jpg",false);
  public static final Image NETFLIX_SYMBOL = new Image(PATH +"/Netflix_Symbol_RGB.png",false);
  public static final Image POSTER_KIMETSU = new Image(PATH +"/netflix-castlevania.jpg",false);
  public static final Image POSTER_BIGMOUTH = new Image(PATH +"/netflix-big-mouth.jpg",false);
  public static final Image POSTER_DEMOLIDOR = new Image(PATH +"/netflix-demolidor.jpg",false);
  public static final Image POSTER_FINALSPACE = new Image(PATH +"/netflix-final-space.jpg",false);
  public static final Image POSTER_BLACKMIRROR = new Image(PATH +"/netlix-black-mirror.jpg",false);
  public static final Image POSTER_HOUSEOFCARDS = new Image(PATH +"/netflix-house-of-cards.jpg",false);
  public static final Image POSTER_LACASADEPAPEL = new Image(PATH +"/netlix-lacasadepapel.jpg",false);
  public static final Image POSTER_STRANGERTHINGS = new Image(PATH +"/netflix-stranger-things2.jpg",false);

  public static final Image MOVIE_DARK = new Image(Gallery.class.getResourceAsStream(PATH +"/play-dark.gif"));
  public static final Image MOVIE_KIMETSU = new Image(Gallery.class.getResourceAsStream(PATH +"/play_kimetsu.gif"));
  public static final Image MOVIE_BIGMOUTH = new Image(Gallery.class.getResourceAsStream(PATH +"/play-bigmouth.gif"));
  public static final Image MOVIE_DEMOLIDOR = new Image(Gallery.class.getResourceAsStream(PATH +"/play-demolidor.gif"));
  public static final Image MOVIE_FINALSPACE = new Image(Gallery.class.getResourceAsStream(PATH +"/play_finalspace.gif"));
  public static final Image MOVIE_BLACKMIRROR = new Image(Gallery.class.getResourceAsStream(PATH +"/play-blackmirror.gif"));
  public static final Image MOVIE_HOUSEOFCARDS = new Image(Gallery.class.getResourceAsStream(PATH +"/play_houseofcards.gif"));
  public static final Image MOVIE_LACASADEPAPEL = new Image(Gallery.class.getResourceAsStream(PATH +"/play_lacasadepapel.gif"));
  public static final Image MOVIE_STRANGERTHINGS = new Image(Gallery.class.getResourceAsStream(PATH +"/play_strangerthings.gif"));


}
