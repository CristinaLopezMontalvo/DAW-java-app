/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.llibrerialliure.llibreriaweb;

/**
 * Clase que representa un libro con su título ISBN y editorial
 * 
 * @author cristinalopezmontalvo
 * @version 1.0
 */
public class Llibre {
    
    private String titol;
    private String isbn;
    private String editorial;


    // Constructor
     /**
     * crea un nuevo libro con los datos que se le pasan
     * 
     * @param titol     el título del libro
     * @param isbn      el código ISBN del libro
     * @param editorial el nombre de la editorial
     * @version 1.0
     */
    public Llibre(String titol, String isbn, String editorial) {
        this.titol = titol;
        this.isbn = isbn;
        this.editorial = editorial;
    }

    // Getters
     /**
     * Devuelve el título del libro
     * 
     * @return el título
     * @version 1.0
     */
    public String getTitol() { return titol; }
    
    /**
     * Devuelve el ISBN del libro
     * 
     * @return el ISBN
     * @version 1.0
     */
    public String getIsbn() { return isbn; }
    
     /**
     * devuelve la editorial del libro
     * 
     * @return la editorial
     * @version 1.0
     */
    public String getEditorial() { return editorial; }
    
    
    
}
