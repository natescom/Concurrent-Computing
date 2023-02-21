# FORK

Trabalho de programação concorrente, manipulação de processos com fork().
Simulação de uma árvore genealógica. 

Fork é uma função de sistemas operacionais baseadas em Unix usada para criar novos processos em execução. Quando chamada, o processo que a chama será o processo pai e o processo criado será o filho e seu corpo será identico ao pai. 

Para destinar algum trecho de código para determinado filho é necessário identificar o processo através de um ID com a função getpid().