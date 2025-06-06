/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.llibrerialliure.llibreriaweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 * Servlet que se conecta a la base de datos y pasa los datos al JSP para mostrarlos
 * 
 * Funciona tanto en Tomcat como en WildFly sin tener que cambiar nada del código:
 * si encuentra un DataSource configurado (como en WildFly), lo usa directamente,
 * si no, tira de la clase ConnectDB usando JDBC para Tomcat
 * 
 * @author cristinalopezmontalvo
 * @version 1.0
 */
@WebServlet(name = "Consulta", urlPatterns = {"/consulta"})
public class Consulta extends HttpServlet {

    /**
     * Responde a las peticiones GET mostrando una lista de libros
     * Al final del código envia los libros al JSP "llibreria.jsp" para mostrarlos
     *
     * @param request  la petición HTTP que llega del navegador
     * @param response la respuesta que se va a devolver al navegador
     * @version 1.0
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Llibre> llistaLlibres = new ArrayList<>();
        Connection connexio = null;

        try {
            // Intenta usar un DataSource (WildFly)
            try {
                InitialContext ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx.lookup("java:/LlibreriaDS");
                connexio = dataSource.getConnection();
                System.out.println("Conexión establecida mediante DataSource (WildFly)");
            } catch (NamingException | SQLException e) {
                // Si falla (por ejemplo en Tomcat), usa DriverManager con ConnectDB
                System.out.println("No se encontró el DataSource. Usando DriverManager (Tomcat).");
                connexio = ConnectDB.obtenirConnexio();
            }

            // Consulta SQL para obtener la lista de libros y su editorial
            String sql = "SELECT titol, isbn, e.nom as editorial " +
                         "FROM llibres JOIN editorials e ON e.id = llibres.id_editorial";

            try (Statement stmt = connexio.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                // Recorre cada fila del resultado de la consulta
                while (rs.next()) {
                    String titol = rs.getString("titol");
                    String isbn = rs.getString("isbn");
                    String editorial = rs.getString("editorial");

                    // Creación de objetos de tipo Llibre
                    Llibre llibre = new Llibre(titol, isbn, editorial);
                    llistaLlibres.add(llibre);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServletException("Error accedint a la base de dades", ex);
        } finally {
            // Cerrar la conexión si existe
            if (connexio != null) {
                try { connexio.close(); } catch (SQLException ignore) {}
            }
        }

        // setAttribute -> guarda llistaLlibres dentro del objeto request con el nombre "llibres"
        request.setAttribute("llibres", llistaLlibres); 
        
        // Obtiene una referencia al recurso llibreria.jsp
        RequestDispatcher rd = request.getRequestDispatcher("llibreria.jsp");
        
        // Transfiere la petición y la respuesta actuales para que ese recurso (el JSP) las gestione.
        rd.forward(request, response);
    }
}
