<h3 align="center">Projeto RabbitMQ</h3>

<br />

---
<br />

# :clipboard: Indice

- [Demonstração](#Demonstracao) 
- [Sobre](#Sobre)
- [Tecnologias Utilizadas](#Tecnologias-utilizadas)
- [Autor](#Autor)

## :camera: <a name="Demonstracao">Demonstração</a>

Conversa em privado
<br />
![privado](https://user-images.githubusercontent.com/49540283/117526346-e82cb300-af9a-11eb-9d89-0d58943d84f8.png)

<br />

Conversa em grupo
<br />
![grupo](https://user-images.githubusercontent.com/49540283/117526353-f084ee00-af9a-11eb-9882-ff8476b38462.png)

<br />

## :pushpin: <a name="Sobre">Sobre</a>  

Projeto desenvolvido com a biblioteca javaFX para criação das interfaces, e RabbitMQ para criação dos canais e envios ou recebimentos de mensagens enviadas pelos usuários através do programa. O projeto contém duas interfaces, a tela de login para acessar alguma conta, e a tela de chat na qual vai ter um chat privado com cada usuário e um chat em grupo no qual todos os usuários podem se comunicar entre si. Sempre que algum contato envia alguma mensagem para o usuário, ele é notificado que a uma nova mensagem no respectivo chat.

O projeto possui várias Threads, na interface para que ela seja atualizada constantemente devido as conversas realizadas dinamicamente, e nas classes que contém as lógicas de execução ligadas ao uso do RabbitMQ. Relacionado aos canais de cada usuário, existe três threads, uma thread que inicia a conexão com a fila privada do respectivo usuário, outra thread que inicia a conexão com a fila relacionada ao grupo, tendo seu nome de fila igual aos outros usuários do grupo para que assim todos recebam as mensagens enviadas ao respectivo canal, e por fim, a thread que realiza a emissão de publicações para algum canal quando o usuário envia mensagem. 

## :rocket: <a name="Tecnologias-utilizadas">Tecnologias utilizadas</a>  

- [Java](https://www.java.com/pt-BR/) > 10
- [Maven](https://maven.apache.org/) > 4
- [JavaFX](https://openjfx.io/) > 11
- [RabbitMQ](https://www.rabbitmq.com/)
- [SLF4J](http://www.slf4j.org/)

## :smile: <a name="Autor">Autor</a>  

![eu](https://user-images.githubusercontent.com/49540283/117379724-7840fe80-aeae-11eb-87fb-54a79b44233d.jpg)
   
Feito com 💙 por Felipe Konig :wave: Entre em contato!

[![Facebook Badge](https://img.shields.io/badge/Facebook-Felipe%20Konig-blue)](https://www.facebook.com/felipe.konig.3/)
[![Twitter Badge](https://img.shields.io/badge/Twitter-Felipe%20Konig-blue)](https://twitter.com/FelipeKonig4) 
[![Linkedin Badge](https://img.shields.io/badge/LinkedIn-Felipe%20Konig-blue)](https://www.linkedin.com/in/felipe-konig-10bb8a190/) 
[![Gmail Badge](https://img.shields.io/badge/Gmail-lipekonig%40gmail.com-orange)](mailto:lipekonig@gmail.com)
