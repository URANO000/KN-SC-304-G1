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

3. MOD 1.1: Adriana-  Maria Fernanda
Para la implementación de el módulo, utilicé una lista de colas, todos los requerimientos se cumplieron menos el requerimiento de cargar las colas si el archivo existe. Ese no se ha implementado todavía.
Arreglé los bugs del código de MOD 1.0

Maria Fernanda -> Serialización del ticket.


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
Ticket
NodoCaja
NodoLista
ListaCajas
Caja
Serialización ticket


Main -> En el paquete por default, es el main de la app.


------------VIDEO----------------------------------
https://youtu.be/KOyo9-M0awc
