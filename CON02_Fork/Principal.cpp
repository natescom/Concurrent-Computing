#include <iostream>
#include <unistd.h>

/********
 * Autor: Nathan Ferraz da Silva
 * Matricula: 201911925
 * Inicio: 19/06/2021
 * Ultima alteracao: 19/06/2021
 * Nome: Principal
 * Funcao: Simular uma arvore genealogica
 * *******/
using namespace std;

static pid_t pai, primeiroFilho, segundoFilho, terceiroFilho, primeiroNeto, segundoNeto, primeiroBisneto;
int nascFilho1, nascFilho2, nascFilho3, nascNeto1, nascNeto2, nascBisneto;
int ano = 0;

int main()
{
  pai = getpid();
  while (true)
  {
    if (getpid() == pai)
    {
      cout << "Ano: " << ano << endl;
    }
    // Switch abaixo seleciona o evento de acordo o ano corrente
    switch (ano)
    {
    case 0:
      if (getpid() == pai)
      {
        cout << "Nasce pai " << endl;
      }
      break;
    case 22:
      if (getpid() == pai)
      {
        cout << "Pai tem o primeiro filho aos " << ano << " anos" << endl;
        primeiroFilho = fork();
        nascFilho1 = ano;
        if (primeiroFilho == 0)
        {
          primeiroFilho = getpid();
        }
      }
      break;
    case 25:
      if (getpid() == pai)
      {
        cout << "Pai tem o segundo filho aos " << ano << " anos" << endl;
        segundoFilho = fork(); //nasce o segundo filho
        nascFilho2 = ano;
        if (segundoFilho == 0)
        {
          segundoFilho = getpid();
        }
      }
      break;
    case 32:
      if (getpid() == pai)
      {
        cout << "Pai tem o terceiro filho aos " << ano << " anos" << endl;
        terceiroFilho = fork();
        nascFilho3 = ano;
        if (terceiroFilho == 0)
        {
          terceiroFilho = getpid();
        }
      }
      break;
    case 38:
      if (getpid() == primeiroFilho)
      {
        cout << "Pai eh avo (primeiro filho) aos " << ano << " anos" << endl;
        primeiroNeto = fork();
        nascNeto1 = ano;
        if (primeiroNeto == 0)
        {
          primeiroNeto = getpid();
        }
      }
      break;
    case 45:
      if (getpid() == segundoFilho)
      {
        cout << "Pai eh avo (segundo filho) aos " << ano << " anos" << endl;
        segundoNeto = fork();
        nascNeto2 = ano;
        if (segundoNeto == 0)
        {
          segundoNeto = getpid();
        }
      }
      break;
    case 68:
      if (getpid() == primeiroBisneto)
      {
        cout << "Pai eh bisavo aos " << ano << " anos" << endl;
        primeiroBisneto = fork();
        nascBisneto = ano;
        if (primeiroBisneto == 0)
        {
          primeiroBisneto = getpid();
        }
      }
      break;
    case 73:
      if (getpid() == primeiroNeto)
      {
        cout << "Primeiro Neto morre aos " << (ano - nascNeto1) << " anos" << endl;
        _exit(0);
      }
      break;
    case 78:
      if (getpid() == segundoNeto)
      {
        cout << "Segundo Neto morre aos " << (ano - nascNeto2) << " anos" << endl;
        _exit(0);
      }
      break;
    case 80:
      if (getpid() == segundoFilho)
      {
        cout << "Segundo Filho morre aos " << (ano - nascFilho2) << " anos" << endl;
        _exit(0);
      }
      if (getpid() == primeiroBisneto)
      {
        cout << "Bisneto morre aos " << (ano - nascBisneto) << " anos" << endl;
        _exit(0);
      }
      break;
    case 84:
      if (getpid() == primeiroFilho)
      {
        cout << "Primeiro filho morre aos " << (ano - nascFilho1) << " anos" << endl;
        _exit(0);
      }
      break;
    case 87:
      if (getpid() == terceiroFilho)
      {
        cout << "Terceiro filho morre aos " << (ano - nascFilho3) << " anos" << endl;
      }
      break;
    case 90:
      if (getpid() == pai)
      {
        cout << "Pai morre aos " << ano << " anos" << endl;
        _exit(0);
      }
      break;
    }
    sleep(1);
    ano++;
  }
  return 0;
}