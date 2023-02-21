package com.nathan.protocolo;

/****************************************************************
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 29/04/2021
 * Ultima alteracao: 07/05/2021
 * Nome: Protocol
 * Funcao: Serve de heranca para os protocolos de gerenciamento
 * de zonas criticas
 * ************************************************************** */
public abstract class Protocol {
  /**
   * Entra na zona critica
   * @param processID Identificador do processo
   */
  public abstract void enter_region(int processID);

  /**
   * Sai da zona critica
   * @param processID Identificador do processo
   */
  public abstract void leave_region(int processID);
}
