package com.nathan.utils;

import com.nathan.protocolo.Protocol;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 07/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: Tunnel
 * Funcao: Linha de trem especial em que soh pode ser percorrida
 * por um trem por vez
 * ************************************************************** */
public class Tunnel extends TrainLine {
  private Protocol protocol;

  /**
   * Construtor
   * @param name Nome
   * @param length Comprimento
   * @param protocol Protocolo de gerenciamento de zona critica
   */
  public Tunnel(String name, int length, Protocol protocol) {
    super(name, length);
    this.protocol = protocol;
  }

  public Protocol getProtocolo() {
    return protocol;
  }
}
