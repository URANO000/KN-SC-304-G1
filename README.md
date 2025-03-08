# KN-SC-304-G1
Proyecto de Estructura de Datos 

==================Integrantes==========================================
Adriana Isabel Sevilla Useda
Maria Fernanda Mata Halleslebens
Adbeel Espinoza Vanegas
Daniel Quiros

==================Requisitos implementados==========================================

1. MOD 1.0: Adbeel-
Para la implementación de este modulo necesite realizar cambios constantes en distintas clases ya que tuve algunos problemas para que toda la información necesaria quedara registrada
en el archivo JSON, sin embargo gracias a mi compañera Adriana logre darme cuenta de lo que necesitaba cambiar para que todo quedara en orden. Ademas cabe decir que realice varias
versiones de este modulo ya que a parte de algunos problemas a solucionar también se presentaron otros cambios como el cambio de idioma y la organización de las clases.

3. MOD 1.1: Adriana-
Para la implementación del segundo módulo no se me ocurrió una buena manera de hacer las cosas más que con un HashMap para los keys, pero apreciaría muchísimo feedback
para pensar en una mejor manera más adecuada de realizarlo, por lo que puedo decir que no está del todo finalizado en cuanto a la división correcta de cajas
El requisito de cargar el archivo de JSON incluso si la app se cierra, no está listo todavía. 



==================Detalles de la configuración==========================================
Tanto mi módulo (Adriana) como el de Adbeel se ejecutan en el MAIN. Los dos los separé en paquetes para organizarlos. 
Al principio estaban divididos en dos mains, pero decidí, con feedback de Maria Fernanda, 
el convertir los dos en métodos para llamarlos en el main principal, sólo utilizando un Main. 
De esta manera se vé más decluttered.

==================Documentación Breve de clases==========================================
MOD 1.0:
Clases:
ConfigJson -> Esta clase se encarga de guardar los datos en el archivo JSON en un inicio y luego los carga
ConfigSucursal -> De esta clase se obtienen los datos de la sucursal como el nombre, numero de cajas, tipos entre otros
ListaEnlazada -> Esta clase agrega el nodo para obtener cada dato y verificar datos espeificos de la lista
Nodo -> Una clase Nodo para dar paso a los siguientes datos
Menu -> La clase menu llama a las demas clases y se utiliza JOptionPane para la interacción con el usuario
Main -> Desde la clase main se llaman a las clases menus de los distintos modulos para su respectivo uso

MOD 1.1: 
Clases:
Ticket -> Es el dato dentro del nodo, contiene los atributos de un tiquete
Nodo 
Cola -> Contiene las funciones de cola modificadas para funcionar con el HashMap
Serializacionticket -> La clase que contiene los métodos de serialización de las colas de tiquetes
CreacionTicket -> Contiene todos los métodos para la creación de los tiquetes utilizando
JOptionPane.

Main -> En el paquete por default, es el main de la app.
