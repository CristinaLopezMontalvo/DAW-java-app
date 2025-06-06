/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.llibrerialliure.llibreriaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MariaDB de la MV Debian12 "la_meva_llibreria"
 * Proporciona un método estático para obtener una conexión activa
 * @version 1.0
 * @author cristinalopezmontalvo
 */
public class ConnectDB {

    private static final String URL_BBDD = "jdbc:mariadb://192.168.1.133:3306/la_meva_llibreria";
    private static final String USUARI_BBDD = "admin02";
    private static final String CONTRASENYA_BBDD = "admin002"; 

    /**
     * Abre una conexión con la base de datos MariaDB
     * 
     * Carga el driver necesario y se conecta a la base de datos con los datos definidos
     * Devuelve un objeto que te permite hacer consultas o actualizaciones
     * 
     * @return una conexión válida si todo va bien
     * @throws SQLException si falla al cargar el driver o al conectar
     */
    public static Connection obtenirConnexio() throws SQLException {
        try {
            // línea específica para cargar el driver
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver MariaDB cargado explícitamente."); // Para depuración
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró la clase del driver MariaDB.");
            throw new SQLException("Driver MariaDB no encontrado en el classpath", e);
        }

        // Imprime la URL para verificar que no hay caracteres extraños
        System.out.println("Intentando conectar a: " + URL_BBDD);

        return DriverManager.getConnection(URL_BBDD, USUARI_BBDD, CONTRASENYA_BBDD);
    }
}
