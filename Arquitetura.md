# Descrição Técnica #

O JDaVelha é constituído por uma arquitetura do tipo cliente / servidor, usando conexões Sockets do pacote java.net. A comunicação entre ambos é realizada através de um protocolo bem simples, baseado em Strings.

## Protocolo ##

Composição: "acao|valor1,valor2,valor3"

A "acao" representa uma ação a ser executada no outro lado da rede, fazendo com que o nó (servidor ou cliente) tome uma decisão de acordo com a ação enviada na primeira parte da String.

## Eventos ##

A lógica e a interface gráfica tanto do servidor quanto do cliente foram divididas para facilitar o desenvolvimento do jogo. Portanto, a comunicação entre o cliente e o servidor é realizada apenas na camada de lógica e rede, fazendo da interface gráfica apenas uma visualização do estado atual do jogo. Para antingir esse objetivo, foi implementado o Design Pattern Observer, que cria uma infra-estrutura permitindo que os ouvintes (as telas) sejam notificadas a qualquer interação entre os nós de rede (cliente / servidor). A cada interação entre eles, um evento é disparado na classe de destino, fazendo com que a tela consiga se atualizar automaticamente.

## Modos De Inicialização ##

O sistema inicia e logo são exibidos os modos de inicialização: Cliente ou Servidor. Caso o servidor seja escolhido, será aberto um cliente, conectado no próprio localhost. Após a abertura do cliente, o servidor ficará escutando alguma conexão para que o jogo seja iniciado.

Caso seja iniciado em modo Cliente, será necessário informar o IP da máquina que realizará o papel de servidor, para assim iniciarmos o jogo.


---


Obs.: Em sua implementação original, as ações do protocolo ainda estão hardcoded, mas em uma futura melhoria, elas poderiam estar em algum mecanismo mais eficiente e reutilizável (quem tiver interesse em desenvolver algo desse tipo, por favor entre em contato)