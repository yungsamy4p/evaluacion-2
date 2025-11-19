/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Santo Tomas
 */

//o	Idioma
//o	Indicador que señala si el idioma es oficial o no
//o	Porcentaje de la población que habla el idioma

//  `idIdioma` INT NOT NULL AUTO_INCREMENT,
//  `nombreIdioma` VARCHAR(50) NOT NULL,
//  `oficial` BIT(1) NOT NULL,
//  `codigoPais` CHAR(3) NOT NULL,

public class IdiomaPais {
    
    private int id;
    private String nombre;
    private boolean oficial;
    private String codigo;

    public IdiomaPais(int id, String nombre, boolean oficial, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.oficial = oficial;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOficial() {
        return oficial;
    }

    public void setOficial(boolean oficial) {
        this.oficial = oficial;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "IdiomaPais{" + "id=" + id + ", nombre=" + nombre + ", oficial=" + oficial + ", codigo=" + codigo + '}';
    }
}
