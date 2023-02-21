package com.nathan.utils;

import javafx.scene.image.Image;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Gallery
 * Funcao: Lista e instanica todas as imagens para serem utilizadas
 * no ImageView da classe PeopleGUI
 * ***************************************************************/
public abstract class Gallery {

  private static final String path = "/com/nathan/res/img";

  public static final Image FLAGBOY = new Image(Gallery.class.getResourceAsStream(path+"/bandeiraboy.gif"));
  public static final Image FLAGBOYPARADO = new Image(Gallery.class.getResourceAsStream(path+"/bandeiraboyparado.gif"));
}
