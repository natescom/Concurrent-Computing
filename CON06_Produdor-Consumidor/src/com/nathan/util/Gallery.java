package com.nathan.util;

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

  public static final Image PROF_01 = new Image(Gallery.class.getResourceAsStream(PATH +"/Prof_01.gif"));
  public static final Image PROF_02 = new Image(Gallery.class.getResourceAsStream(PATH +"/Prof_02.gif"));
  public static final Image PROF_03 = new Image(Gallery.class.getResourceAsStream(PATH +"/Prof_03.gif"));
  public static final Image PROF_04 = new Image(Gallery.class.getResourceAsStream(PATH +"/Prof_Barbie.gif"));
  public static final Image AL_01 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_0A.gif"));
  public static final Image AL_02 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_02.gif"));
  public static final Image AL_03 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_03.gif"));
  public static final Image AL_04 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_04.gif"));
  public static final Image AL_05 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_kira.gif"));
  public static final Image AL_06 = new Image(Gallery.class.getResourceAsStream(PATH +"/Al_06.gif"));
}
