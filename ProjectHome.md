# JDaVelha #

O projeto consiste em um jogo da velha em rede construído em Java. Ele utiliza a API de I/O e de rede (java.net) do Java SE 6 para a comunicação entre o Server e os Clients. Sua interface com o usuário é feita com o pacote gráfico Swing.

Sua arquitetura é baseada em Cliente/Servidor, com sua comunicação sendo realizada através do Streaming entre Sockets, contendo um protocolo próprio baseado em texto, que define quais ações podem ser realizadas pelos Clients.


---


**Obs.: Se o host que estiver executando a parte do Server possuir um roteador, o mesmo deverá ser configurado para receber conexões externas. (Não sei muito sobre isso, mas uma olhada no manual de instruções pode resolver esse problema)**