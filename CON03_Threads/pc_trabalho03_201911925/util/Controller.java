package pc_trabalho03_201911925.util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.Timer;
import java.util.TimerTask;
/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 27/03/2021
 * Nome: Controller
 * Funcao: Controla as alteracoes da Janela
 * ***************************************************************/
public class Controller {

  @FXML
  public ImageView imagePai;
  public ImageView imageFilho1;
  public ImageView imageFilho2;
  public ImageView imageFilho3;
  public ImageView imageNeto1;
  public ImageView imageNeto2;
  public ImageView imageBisneto;
  public Button btn_comecar;
  public CheckBox cbx_audio;
  public Label lbl_status;
  public Label lbl_anos;
  public Label lbl_idadePai;
  public Label lbl_idadeFilho1;
  public Label lbl_idadeFilho2;
  public Label lbl_idadeFilho3;
  public Label lbl_idadeNeto1;
  public Label lbl_idadeNeto2;
  public Label lbl_idadeBisneto;

  /**
   * Eh acionado quando um elemento da tela eh clicado
   * @param event
   */
  @FXML
  public void btnOnClick(ActionEvent event){
      if(event.getSource().equals(btn_comecar)){ // Se o botao comecar for pressionado
        // ALTERACOES DE INTERFACE //
        btn_comecar.setDisable(true); // Desativo o botao comecar
        btn_comecar.setVisible(false); // Deixo ele invisivel
        cbx_audio.setVisible(true); // Deixo o checkbox do audio visivel
        cbx_audio.setSelected(true); // E comeco com ele selecionado pois a musica estarah tocando
        // MUSICA //
        Jukebox.play("soundtrack.wav"); // Coloco a musica pra tocar

        // INSTANCIAS //
        System.out.println("--- Arvore Genealogica ---");
        PeopleGUI pai,filho1,filho2,filho3,neto1,neto2,bisneto;
        PeopleGUI.controller = this;
        // Instacio as pessoas de acordo o mais novo para o mais velho para que eu possa passar o mais novo como filho do mais velho
        bisneto = new PeopleGUI("Bisneto",12, packAges(), packSons(),imageBisneto,lbl_idadeBisneto);
        neto1 = new PeopleGUI("Primeiro Neto",35, packAges(30), packSons(bisneto),imageNeto1,lbl_idadeNeto1);
        neto2 = new PeopleGUI("Segundo Neto",32, packAges(), packSons(),imageNeto2,lbl_idadeNeto2);
        filho1 = new PeopleGUI("Primeiro filho",61, packAges(15), packSons(neto1),imageFilho1,lbl_idadeFilho1);
        filho2 = new PeopleGUI("Segundo filho",55, packAges(19), packSons(neto2),imageFilho2,lbl_idadeFilho2);
        filho3 = new PeopleGUI("Terceiro filho",55, packAges(), packSons(),imageFilho3,lbl_idadeFilho3);
        pai = new PeopleGUI("Pai",90, packAges(22,25,32), packSons(filho1,filho2,filho3), imagePai,lbl_idadePai);

        pai.start(); // Inicio a vida do pai e a partir dele as outras pessoas nascerao

        // TEMPORIZADOR PARA CONTAR OS ANOS //
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
          int i;
          @Override
          public void run() {
            Platform.runLater(()->{
              lbl_anos.setText("Ano: "+ (2000 + i));
            });
            i++;
          }
        },0,1000);
      }

      if(event.getSource().equals(cbx_audio)){ // Se a check box for pressionada
        Jukebox.pause();
      }
  }


  /**
   * Util pra nao precisar instanciar um novo vetor sempre que for passar as idades para as Pessoas
   * @param i idades
   * @return Vetor de idades
   */
  public int[] packAges(int ... i){
    return i;
  }

  /**
   * Util pra nao precisar instanciar uma nova pessoa sempre que for passar os filhos para os Pais
   * @param sons filhos
   * @return Vetor de filhos
   */
  public People[] packSons(People ... sons){
    return sons;
  }
}
