# KN-SC-304-G1
Proyecto de Estructura de Datos 

==================Integrantes==========================================
Adriana Isabel Sevilla Useda
Maria Fernanda Mata Halleslebens
Adbeel Espinoza Vanegas
Daniel Quiros  --> No participa

==================Requisitos implementados==========================================
Mod 1.2: María Fernanda Mata
El aporte fue en el módulo de Atención de tiquetes, donde puse las clases de ManagerAtencion y TicketAtendido. Donde en ManagerAtencion se implementaron los siguientes requerimientos
como la validación de que la caja no esté vacía, obtención del primer tiquete en cola, el registro de la hora de atención con formato, la serialización del tiquete atendido a "atendidos.json"
y la actualización del archivo `tiquetes.json` al eliminar el tiquete. Cabe aclarar que el punto 6 del módulo 1.2 quedó pendiente para la proxima entrega.

Mod 1.3: Adriana Sevilla Useda
Se resume en agregar tiquetes a las diferentes cajas normales, tomando en cuenta que tan llena esté cada caja normal. El módulo se implementó con éxito. Todo el funcionamiento del módulo se implementó en la misma clase de ManagerCajas, excepto por SerializacionCola. Además, realicé la deserialización del módulo 1.1.


==================Detalles de la configuración==========================================
El código se ejecuta en el MAIN. Se debe de correr desde el root, el archivo con nombre Esferisoft.
==================Documentación Breve de clases==========================================

MOD 1.2
Clases:
ManagerAtencion -> tiene todas los métodos para desarrollar las funcionalidades del módulo
TicketAtendido -> concentra los atributos, constructores, y los getters para acceder a esos atributos.

Main -> María Fernanda
Se encuentra como primero la función de validar los usuarios (el método se llama validarUsuario y se encuentra en la clase Menu, modulo 1.0), 
y luego de validar entra a un menú donde podrá acceder a las funcionalidades implementadas por el moemento.

MOD 1.3
Clases:
ManagerCajas: Respectivamente del módulo 1.1, pero la funcionalidad es del 1.3.
SerializacionColas: Contiene la serialización y deserialización.

MOD 1.4
Clases
GrafoServicios: En este modulo se implemento la busqueda por grafos a traves de JSON


------------VIDEO----------------------------------
https://youtu.be/RbfU4Vh7FTs

