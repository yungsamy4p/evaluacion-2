/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author samue
 */

public class Pais {
    private String codigo;
    private String nombre;
    private String continente;
    private int poblacion;
    private boolean tipoGobierno;

    public Pais() {}

    public Pais(String codigo, String nombre, String continente, int poblacion, boolean tipoGobierno) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.continente = continente;
        this.poblacion = poblacion;
        this.tipoGobierno = tipoGobierno;
    }

    // Getters y Setters
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getContinente() { return continente; }
    public void setContinente(String continente) { this.continente = continente; }
    public int getPoblacion() { return poblacion; }
    public void setPoblacion(int poblacion) { this.poblacion = poblacion; }
    public boolean isTipoGobierno() { return tipoGobierno; }
    public void setTipoGobierno(boolean tipoGobierno) { this.tipoGobierno = tipoGobierno; }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }

    public String getCodigoPais() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}