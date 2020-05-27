- ## Descripción de la aplicación WEB
La aplicación gestiona la venta de motos de segunda mano entre usuarios registrados, el usuario puede ver el anuncio de otro usuario y hacer una oferta por una moto, el usuario vendedor puede aceptar la oferta y vender o rechazarla.
Los usuarios sin registro solo pueden registrarse o hacer login para iniciar sesión.
Un Administrador puede borrar anuncios que no cumplan la normativa o dar de baja a usuarios. Además tiene las opciones de los usuarios normales como crear anuncio o ver las ofertas.

- ## Nombre y descripción de las entidades 

- ADMINISTRADOR --> Gestiona las altas, bajas de los usuarios, gestiona las ofertas y las ventas. Da de alta las motos y gestiona los anuncios.

- USUARIO --> Para ver los anuncios debe estar registrado, para realizar una oferta de una moto de segunda mano o comprar una moto nueva debe registrarse e iniciar sesión.

- COMPRA --> Esta entidad registra una compra por parte del usuario

- VENTA --> Registra la venta y da de baja la moto del catálogo.

- MOTO --> Registra todas las motos del catálogo con sus características, crea y da de baja las motos del catálogo. 



## INTEGRANTES DEL EQUIPO

- Damián Ortiz Barahona --> d.ortizb@alumnos.urjc.es 
-->Cuenta GIT : diortizba1984
- Antonio Vizcaino Moraga --> a.vizcainom@alumnos.urjc.es --> Cuenta Git : viz7mor

##Tablero TRELLO
- https://trello.com/b/BlmwYZA0/te-vendo-la-moto

#FASE 2
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

- Los Requisitos para ejecutar la aplicación son:

    - Java 11
    - Open JDK JAVA 11
    - Mysql 5.7.29
    
- Los datos para iniciar la aplicación son: 
	-user: admin@admin.com   
	-password: adminpass

- Configuracion Mysql
	-user: admin@localhost-->
	-pass: administrador-->
	-base de datos: tevendolamoto

- Verificamos que nuestro proyecto sea MAVEN en el archivo POM

![pom_maven](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/maven.png)

- Verificamos que el packaging sea JAR en el archivo POM

![pom_jar](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/jar.png)

- Creamos el ejecutable JAR  
    1.- En el menu RUN seleccionamos RUN AS  
    2.- En el desplegable seleccionamos MAVEN BUILD  
    3.- Dentro de la ventana escribimos package

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/package.png)

- Cuando se genere el archivo JAR verificamos la ubicación 

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/ruta.png)

- Buscamos el archivo jar que se ha creado

![package](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/Archivo_JAR.png)
  
Ejecutamos el archivo y la aplicación entrara en funcionamiento.

#FASE 3

Para implementar las técnicas de tolerancia a fallos utilizamos Azure para desplegar la aplicación. Vamos a utilizar las siguientes técnicas:
- Balanceador de carga HAPROXY
- Separación de Servidor y BBDD
- Servidor con caché

-Diagrama Azure
![Diagrama Azure](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/azure.png)

- Generamos certificado PEM para Azure
![Certificado Pem](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/pem.png)


- Una vez que tenemos Azure activo creamos 4 servidores:

	+Balanceador 
	+BBDD MySQL server
	+Web01--->Aplicación
	+Web02 -->Aplicación
![Servidores](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/servidores.png)



- Configuramos el servidor Mysql server 
-->Creamos la base de datos para nuestra aplicación

![BBDD](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/bbdd.png)

-->Creamos 2 usuarios para conectar con los servidores de la aplicacion, les concedemos todos los privilegios para la BBDD.

mysql> CREATE USER 'admin'@'10.0.1.7' IDENTIFIED BY 'administrador';
mysql> CREATE USER 'admin'@'10.0.1.8' IDENTIFIED BY 'administrador';

-->Configuramos el puerto para conceder el acceso desde los servidores de la aplicación.

Cambie la línea bind-address = 127.0.0.1, a bind-address = <ip del servidor mysql>

- Copiamos la aplicación JAR desde nuestro sistema local a los dos servidores web que creamos

![Subir aplicación a MV](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/subirvm.png)

- Configuramos el balanceador

Modificamos el archivo haproxy.cfg y lo dejamos como la captura de pantalla siguiente:

![HAPROXY](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/haproxy.png)

Comprobamos si esta activo

![HAPROXY ACTIVO](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/activo.png)

Ejecutamos las aplicaciones en los servidores, en la instrucciones incluimos los parametros como se va a conectar.
Para utilizar la BBDD separada debemos incluir la sentencia:
spring.jpa.hibernate.ddl-auto = none

![APLICACION](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/aplicacion.png)

Miramos el status del HAPROXY y vemos que los dos servidores de la aplicacion esta funcionando, en la captura siguiente he parado un servidor y la aplicacion seguia funcionando.

![STATUS HAPROXY](https://github.com/diortizba1984/Te_vendo_la_moto/blob/master/Diagramas/status.png)






