package com.nathan.util;

import javafx.beans.property.SimpleStringProperty;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 17/05/2021
 * Ultima alteracao: 17/05/2021
 * Nome: Person
 * Funcao: Define o modelo de dado e providencia os metodos  e
 * campos necessarios para funcionar com a tableview da interface
 * ************************************************************** */
public class Person {
  private SimpleStringProperty people;      // Nome ou identificador da pessoa
  private SimpleStringProperty lesson;      // Materia que estah estudando ou ensinando
  private SimpleStringProperty size;        // Tamanho da materia
  private SimpleStringProperty percent;     // Conclusao

  public Person(){
    this.people = new SimpleStringProperty("");
    this.lesson = new SimpleStringProperty("");
    this.size = new SimpleStringProperty("");
    this.percent = new SimpleStringProperty("");
  }

  public String getPeople() {
    return people.get();
  }

  public SimpleStringProperty peopleProperty() {
    return people;
  }

  public void setPeople(String people) {
    this.people.set(people);
  }

  public String getLesson() {
    return lesson.get();
  }

  public SimpleStringProperty lessonProperty() {
    return lesson;
  }

  public void setLesson(String lesson) {
    this.lesson.set(lesson);
  }

  public String getSize() {
    return size.get();
  }

  public SimpleStringProperty sizeProperty() {
    return size;
  }

  public void setSize(String size) {
    this.size.set(size);
  }

  public String getPercent() {
    return percent.get();
  }

  public SimpleStringProperty percentProperty() {
    return percent;
  }

  public void setPercent(String percent) {
    this.percent.set(percent);
  }
}
