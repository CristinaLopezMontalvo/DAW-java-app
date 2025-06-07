# 📚 LlibreriaWeb – Práctica 3 DAW

Aplicación web desarrollada para la Práctica 3 de la asignatura *Despliegue de Aplicaciones Web*. Utiliza Java con Servlets y JSP, conectada a una base de datos MariaDB. Se sigue el modelo MVC y se despliega tanto en Tomcat como en WildFly.

## 🛠 Tecnologías

- Java EE / Jakarta EE 10
- Servlets y JSP
- Maven
- Apache Tomcat 11
- WildFly (con Docker)
- MariaDB (Debian 12)
- NetBeans
- Bootstrap

## 📂 Estructura básica

- **Modelo**: clases Java
- **DAO**: acceso a la base de datos
- **Servlets**: controladores
- **JSP**: vista con Bootstrap

## ▶️ Cómo ejecutar

### En local (Tomcat desde NetBeans)
1. Ejecutar el proyecto desde NetBeans.
2. Acceder a `http://localhost:8080/LlibreriaWeb/llibreria.jsp`.

### En servidor (WildFly con Docker)
1. Generar el `.war` con `mvn clean package`.
2. Copiar el `.war` al contenedor WildFly:
   ```bash
   docker cp target/llibreria.war <id_contenedor>:/opt/bitnami/wildfly/standalone/deployments
