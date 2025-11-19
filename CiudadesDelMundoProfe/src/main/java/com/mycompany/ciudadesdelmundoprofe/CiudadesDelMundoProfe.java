/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciudadesdelmundoprofe;

import dao.IdiomaPaisDAO;
import modelo.IdiomaPais;

/**
 *
 * @author Santo Tomas
 */
public class CiudadesDelMundoProfe {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        for (IdiomaPais idioma : (new IdiomaPaisDAO()).listarTodos()) {
            System.out.println("idioma " + idioma.toString());
        }
        for (IdiomaPais idioma : (new IdiomaPaisDAO()).listarTodos()) {
            System.out.println("idioma " + idioma.toString());
        }

    }
}
