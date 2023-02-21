package pc_trabalho03_201911925.util;

import java.util.Timer;
import java.util.TimerTask;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 23/03/2021
 * Ultima alteracao: 28/03/2021
 * Nome: People
 * Funcao: Simular o ciclo de vida de uma pessoa: Nascer, Crescer
 * Reproduzir e Morrer e somente isso. As alteracoes na interface
 * devem ser definidas por uma classe filha
 * ***************************************************************/
public abstract class People extends Thread{
  public final String name;  // Nome da pessoa
  private final People[] sons; // Vetor com os filhos que essa pessoa terah, caso tenha

  protected int age; // Idade corrente da pessoa, usada acionar os eventos do ciclo da vida (nascer, reproduzir e morrer)
  protected int childIndex; // Indice usado para identificar qual filho deverah nascer quando esta pessoa atingir uma idade em que se reproduz (nao eh usada quando a pessoa nao tem filhos)
  protected final int lifetime; // Tempo de vida da Pessoa
  protected final int[] ageAtBeFather; // Vetor com as idades em que a pessoa terah um filho

  /**
   * Construtor
   * @param name          Nome da pessoa
   * @param lifetime      Tempo de vida da pessoa, e tambem da thread
   * @param ageAtBeFather Vetor com as idades em que a pessoa se torna pai
   * @param sons          Vetor com os filhos da Pessoa
   */
  public People(String name, int lifetime, int[] ageAtBeFather, People[] sons) {
    this.name = name;
    this.lifetime = lifetime;
    this.ageAtBeFather = ageAtBeFather;
    this.sons = sons;
  }

  @Override
  public void run() {
    Timer timer = new Timer(); // Temporizador para contar o tempo de vida da pessoa e da thread
    timer.scheduleAtFixedRate(new TimerTask() { // Esse metodo executarah em loop com intervalo de um segundo a sequencia de codigo abaixo
      @Override
      public void run() {
        /*
         * O ciclo de vida de uma pessoa eh basicamente: nascer, crescer, reproduzir e morrer
         * logo o que as linhas de codigo a seguir fazem eh reproduzir esse ciclo
         */
        publish(); // A cada completar de anos eu publico uma alteracao da interface
        if (age == 0) { // Se a idade da pessoa eh zero, entao ela acabou de nascer//
          publish(name + " nasceu"); // Publica uma alteracao na interface
        }
        if(childIndex < ageAtBeFather.length) { // Se o meu indice de filho for menor que a quantidade de idades ao ser pai entao
          if (age == ageAtBeFather[childIndex]) { // verifico se a idade atual eh igual a minha idade ao ter o filho de indice 'childIndex'
            People filho = sons[childIndex]; // Se for, o filho do 'childIndex' do vetor de filhos vai nascer e iniciar seu ciclo de vida
            filho.start();
            childIndex++; // o indice do filho a nascer aumentarah para apontar para o proximo filho que deve nascer
          }
        }
        if (age == lifetime) { // Se a minha idade for igual ao tempo de vida, significa que o tempo de vida da pessoa acabou
          publish(name +" morreu aos "+ age +" anos"); // Publica uma alteracao na interface
          timer.cancel(); // Esse comando irah quebrar o loop
        }else{
          age++; // Depois de um segundo, um ano de vida eh incremendado na vida da pessoa
        }
      }
    }, 0, 1000);
  }

  /**
   * Responsavel por publicar alteracoes na interface
   */
  public abstract void publish();
  /**
   * Responsavel por publicar uma mensagem na interface
   */
  public abstract void publish(String s);

}

