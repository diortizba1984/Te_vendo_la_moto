# **TE VENDO LA MOTO**
- ## Descripción de la aplicación WEB
La aplicacion gestiona la venta de motos de segunda mano entre usuarios registrados, el usuario puede ver el anuncio de otro usuario y hacer una oferta por una moto, el usuario vendedor puede aceptar la oferta y vender o rechazarla.
Los usuarios sin registro solo pueden registrarse o hacer login para iniciar sesión.
Un Administardor puede borrar anuncios que no cumplan la normativa o dar de baja a usuarios. Además tiene las opciones de los usuarios normales como crear anuncio o ver las ofertas.

- ## Nombre y descripción de las entidades 

- ADMINISTRADOR --> Gestiona las altas, bajas de los usuarios, gestiona las ofertas y las ventas. Da de alta las motos y gestiona los anuncios.

- USUARIO --> Para ver los anuncios debe estar registrado, para realizar una oferta de una moto de segunda mano o comprar una moto nueva debe registrarse e iniciar sesión.

- COMPRA --> Esta entidad registra una compra por parte del usuario

- VENTA --> Registra la venta y da de baja la moto del catálogo.

- MOTO --> Registra todas las motos del catálogo con sus caracteristicas, crea y da de baja las motos del catálogo. 



## INTEGRANTES DEL EQUIPO

- Damián Ortiz Barahona --> d.ortizb@alumnos.urjc.es 
-->Cuenta GIT : diortizba1984
- Antonio Vizcaino Moraga --> a.vizcainom@alumnos.urjc.es --> Cuenta Git : viz7mor

##Tablero TRELLO
- https://trello.com/b/BlmwYZA0/te-vendo-la-moto

##DIAGRAMAS: 
###- CAPTURAS DE PANTALLA:

Mediante una presentación mostramos las diferentes pantallas que hemos creado con una secuencia lógica, el enlace URL es:

https://prezi.com/view/czPqNnc1nodJ6b7VZiIk/

###- DIAGRAMA DE NAVEGACION

![diagrama_navegación](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/diagrama_navegacion.JPG)

###- MODELO DE DATOS:

####- Diagrama de clases UML

https://app.genmymodel.com/api/projects/_THav0G2HEemzHvNogvRQlA/diagrams/_THav022HEemzHvNogvRQlA/svg

####- Diagrama Entidad-Relacion


![entidad_relacion](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/ERD.TVM.jpg)

##INSTRUCCIONES PARA DESPLEGAR LA APLICACION

- Los Requisitos para ejecutar la aplicacion son:

    - Java 11
    - Open JDK JAVA 11
    - Mysql 5.7.29

- Verificamos que nuestro proyecto sea MAVEN en el archivo POM

![pom_maven](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/maven.png)

- Verificamos que el packaging sea JAR en el archivo POM

![pom_jar](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/jar.png)

- Creamos el ejecutable JAR  
    1.- En el menu RUN seleccionamos RUN AS  
    2.- En el desplegable seleccionamos MAVEN BUILD  
    3.- Dentro de la ventana escribimos package

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/package.png)

- Cuando se genere el archivo JAR verificamos la ubicacion 

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/ruta.png)

- Buscamos el archivo jar que se ha creado

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/Archivo_JAR.png)
  
Ejecutamos el archivo y la aplicacion entrara en fucionamiento.

Aclizar la base de dat problema con el  servidor







