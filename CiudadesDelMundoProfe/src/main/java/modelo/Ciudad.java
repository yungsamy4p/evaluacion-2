/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author samue
 */

public class Ciudad {
    private int id;
    private String nombre;
    private int poblacion;
    private String codigoPais;

    public Ciudad(int id, String nombre, int poblacion, String codigoPais) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.codigoPais = codigoPais;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }
 
    
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getPoblacion() { return poblacion; }
    public String getCodigoPais() { return codigoPais; }
    
    @Override
    public String toString() {
        return nombre;
    }
}