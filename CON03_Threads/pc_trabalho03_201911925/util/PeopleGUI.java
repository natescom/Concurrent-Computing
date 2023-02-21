package pc_trabalho03_201911925.util;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 28/03/2021
 * Nome: PeopleGUI
 * Funcao: Definir quais alteracoes serao aplicadas na interface.
 * Sua classe mae executa somente o ciclo de vida
 * ***************************************************************/
public class PeopleGUI extends People {

  private static final ArrayList<PeopleGUI> tree = new ArrayList<>(); // Armazena toda a arvore genealogica, usada para colocar a imagem de choro quando alguem morre
  private ArrayList<PeopleGUI> tree2; // Armazena toda a arvore genealogica, usada para colocar a imagem de choro quando alguem morre
  public static Controller controller; // Controlador da interface, usado para mudar o texto do status
  private final ImageView imageView; // Imagem do pessoa
  private final Label label; // Label com a idade da pessoa

  /**
   * Construtor
   *
   * @param nome              Nome da pessoa
   * @param tempoDeVida       Tempo de vida da pessoa e tambem da thread
   * @param idadeAoReproduzir Vetor com as idades em que a pessoa se torna pai
   * @param filhos            Vetor com os filhos da Pessoa
   * @param imageView         ImagemView que mostrarah a foto da pessoa
   * @param label             Label que mostrarah a idade da pessoa
   */
  public PeopleGUI(String nome, int tempoDeVida, int[] idadeAoReproduzir, People[] filhos, ImageView imageView, Label label) {
    super(nome, tempoDeVida, idadeAoReproduzir, filhos);
    this.imageView = imageView;
    this.label = label;
    tree.add(this);
    tree2 = new ArrayList<>();
    tree.forEach(peopleGUI -> tree2.add(peopleGUI));
  }

  /**
   * Atualiza o status da arvore genealogica quando algum evento acontecer
   * @param s Status
   */
  @Override
  public void publish(String s) {
    Platform.runLater(() ->{
      System.out.println(s);
      controller.lbl_status.setText(s);
    });
  }

  /**
   * Atualiza as ImageViews e as Label de cada pessoa de acordo sua idade
   */
  @Override
  public void publish() {
    imageView.setVisible(true);
    label.setVisible(true);
    Platform.runLater(() ->{
      label.setText(age+ " anos");
      if(age == lifetime){ // Se a pessoa morreu
        imageView.setImage(Gallery.TUMULO); // Coloco a imagem de tumulo
        tree.remove(this); // Removo ele da arvore
        tree.forEach(people ->{
          people.imageView.setImage(Gallery.LUTO);
        }); // E para os que ainda estao na arvore coloco a imagem de luto
      }else{ // Se ela ainda estah viva
        if(ageAtBeFather.length == 0 || age < ageAtBeFather[0] || age >(ageAtBeFather[ageAtBeFather.length-1] + 2)){ // Se ela nao tera filhos ou a idade atual nao estiver entre as idades em q ele sera pai
          // A sequencia de if abaixo verifica em qual intervalo de idade a pessoa estah para definir qual sera a sua foto,
          // e tbm verifica se essa foto ja foi aplicada ao imageView para nao ficar aplicando varias vezes
          if(age<lifetime && age>=70 && !imageView.getImage().equals(Gallery.IDOSO03))
            imageView.setImage(Gallery.IDOSO03);
          if(age<70 && age>=60 && !imageView.getImage().equals(Gallery.IDOSO02))
            imageView.setImage(Gallery.IDOSO02);
          if(age<60 && age>=50 && !imageView.getImage().equals(Gallery.ADULTO01))
            imageView.setImage(Gallery.IDOSO01);
          if(age<50 && age>=40 && !imageView.getImage().equals(Gallery.ADULTO05))
            imageView.setImage(Gallery.ADULTO05);
          if(age<40 && age>=35 && !imageView.getImage().equals(Gallery.ADULTO04))
            imageView.setImage(Gallery.ADULTO04);
          if(age<35 && age>=30 && !imageView.getImage().equals(Gallery.ADULTO03))
            imageView.setImage(Gallery.ADULTO03);
          if(age<30 && age>=25 && !imageView.getImage().equals(Gallery.ADULTO02))
            imageView.setImage(Gallery.ADULTO02);
          if(age<25 && age>=20 && !imageView.getImage().equals(Gallery.ADULTO01))
            imageView.setImage(Gallery.ADULTO01);
          if(age<20 && age>=15 && !imageView.getImage().equals(Gallery.JOVEM02))
            imageView.setImage(Gallery.JOVEM02);
          if(age<15 && age>=10 && !imageView.getImage().equals(Gallery.JOVEM01))
            imageView.setImage(Gallery.JOVEM01);
          if(age<10 && age>=3 && !imageView.getImage().equals(Gallery.BEBE02))
            imageView.setImage(Gallery.BEBE02);
          if(age<3 && age>=0 && !imageView.getImage().equals(Gallery.BEBE01))
            imageView.setImage(Gallery.BEBE01);
          for (int i = 0; i < tree2.size(); i++) {
            if(tree2.get(i).age==tree2.get(i).lifetime){
              imageView.setImage(Gallery.LUTO);
              tree2.remove(i);
            }
          }
        }else{ // Se a idade estiver entre as que ele serah pai
          if(childIndex < ageAtBeFather.length){ // Se o meu indice de filho for menor que a quantidade de idades ao ser pai entao
            if (age == ageAtBeFather[childIndex]) { // verifico se ta na hora da pessoa ser pai
              imageView.setImage(Gallery.PAI); // Se for, coloco a imagem da cabeca explodindo
            }
          }
        }
      }
    });
  }

}
