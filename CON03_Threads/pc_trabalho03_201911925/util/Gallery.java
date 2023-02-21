package pc_trabalho03_201911925.util;

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

  private static final String path = "/pc_trabalho03_201911925/res/img";

  public static final Image BEBE01 = new Image(Gallery.class.getResourceAsStream(path+"/bebe1.gif"));
  public static final Image BEBE02 = new Image(Gallery.class.getResourceAsStream(path+"/bebe2.gif"));
  public static final Image JOVEM01 = new Image(Gallery.class.getResourceAsStream(path+"/jovem1.gif"));
  public static final Image JOVEM02 = new Image(Gallery.class.getResourceAsStream(path+"/jovem2.gif"));
  public static final Image ADULTO01 = new Image(Gallery.class.getResourceAsStream(path+"/adulto1.gif"));
  public static final Image ADULTO02 = new Image(Gallery.class.getResourceAsStream(path+"/adulto2.gif"));
  public static final Image ADULTO03 = new Image(Gallery.class.getResourceAsStream(path+"/adulto3.gif"));
  public static final Image ADULTO04 = new Image(Gallery.class.getResourceAsStream(path+"/adulto4.png"));
  public static final Image ADULTO05 = new Image(Gallery.class.getResourceAsStream(path+"/adulto35.gif"));
  public static final Image IDOSO01 = new Image(Gallery.class.getResourceAsStream(path+"/vei1.gif"));
  public static final Image IDOSO02 = new Image(Gallery.class.getResourceAsStream(path+"/vei2.gif"));
  public static final Image IDOSO03 = new Image(Gallery.class.getResourceAsStream(path+"/vei3.gif"));
  public static final Image TUMULO = new Image(Gallery.class.getResourceAsStream(path+"/tumulo.png"));
  public static final Image PAI = new Image(Gallery.class.getResourceAsStream(path+"/pai1.gif"));
  public static final Image LUTO = new Image(Gallery.class.getResourceAsStream(path+"/luto.gif"));
}
