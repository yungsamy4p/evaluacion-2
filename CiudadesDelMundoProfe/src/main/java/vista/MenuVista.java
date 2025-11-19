/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import dao.*;
import modelo.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author samue
 */

public class MenuVista {
    private Scanner leer = new Scanner(System.in);
    private PaisDAO paisDAO = new PaisDAO();
    private CiudadDAO ciudadDAO = new CiudadDAO();
    private IdiomaPaisDAO idiomaDAO = new IdiomaPaisDAO();

    public void mostrarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n=== SISTEMA CIUDADES (AGENTE 117) - DB ORIGINAL ===");
            System.out.println("1. Buscar país (Nombre, Cont., Pob.)");
            System.out.println("2. Buscar ciudad");
            System.out.println("3. Listar países por continente");
            System.out.println("4. Ver ciudades de un país");
            System.out.println("5. Ver idiomas de un país");
            System.out.println("6. Comparar población entre países");
            System.out.println("7. Listar todos los países (Año Indep. no disponible)");
            System.out.println("8. Frecuencia de idiomas (Global)");
            System.out.println("9. Listar ciudades principales (Capitales no marcadas en DB)");
            System.out.println("10. Top ciudades más pobladas");
            System.out.println("0. Salir");
            System.out.print("Seleccione misión: ");
            
            try {
                String input = leer.nextLine();
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) { 
                opcion = -1; 
            }

            switch (opcion) {
                case 1: vistaBuscarPais(); break;
                case 2: vistaBuscarCiudad(); break;
                case 3: vistaPaisesPorContinente(); break;
                case 4: vistaCiudadesPorPais(); break;
                case 5: vistaIdiomasPais(); break;
                case 6: vistaCompararPaises(); break;
                case 7: vistaListarTodos(); break;
                case 8: vistaIdiomasFrecuencia(); break;
                case 9: vistaCiudadesPrincipales(); break;
                case 10: ciudadDAO.listarMasPobladas(5); break;
                case 0: System.out.println("Cortana fuera."); break;
                default: System.out.println("Comando no reconocido, Jefe.");
            }
        } while (opcion != 0);
    }

    private void vistaBuscarPais() {
        System.out.print("Nombre del país: ");
        String nombre = leer.nextLine();
        Pais p = paisDAO.buscarPorNombre(nombre);
        if (p != null) {
            System.out.println(">> " + p.getNombre() + " (" + p.getCodigo() + ")");
            System.out.println("   Continente: " + p.getContinente());
            System.out.println("   Población: " + p.getPoblacion());
            // Interpretación: 1 = República/Presidencial, 0 = Monarquía/Parlamentaria (ejemplo)
            System.out.println("   Gobierno: " + (p.isTipoGobierno() ? "República (1)" : "Monarquía/Otro (0)")); 
        } else {
            System.out.println("Objetivo no encontrado.");
        }
    }

    private void vistaBuscarCiudad() {
        System.out.print("Nombre de la ciudad: ");
        String nombre = leer.nextLine();
        Ciudad c = ciudadDAO.buscarPorNombre(nombre);
        if (c != null) {
            System.out.println(">> " + c.getNombre() + " | País: " + c.getCodigoPais() + " | Pob: " + c.getPoblacion());
        } else {
            System.out.println("Ciudad no encontrada.");
        }
    }

    private void vistaPaisesPorContinente() {
        System.out.print("Continente (Asia, Europe, America...): ");
        String cont = leer.nextLine();
        List<Pais> lista = paisDAO.listarPorContinente(cont);
        if (lista.isEmpty()) {
            System.out.println("Sin registros o continente mal escrito.");
        }
        for (Pais p : lista) {
            System.out.println("- " + p.getNombre());
        }
    }

    private void vistaCiudadesPorPais() {
        System.out.print("Código País (ej. ARG, CHL): ");
        String cod = leer.nextLine().toUpperCase();
        List<Ciudad> lista = ciudadDAO.listarPorPais(cod);
        if (lista.isEmpty()) {
            System.out.println("No se encontraron ciudades para este código.");
        }
        for (Ciudad c : lista) {
            System.out.println("- " + c.getNombre() + " (" + c.getPoblacion() + ")");
        }
    }

    private void vistaIdiomasPais() {
        System.out.print("Código País (ej. CHL): ");
        String cod = leer.nextLine().toUpperCase();
        List<IdiomaPais> lista = idiomaDAO.listarPorPais(cod);
        if (lista.isEmpty()) {
            System.out.println("No se encontraron idiomas.");
        }
        for (IdiomaPais i : lista) {
            System.out.println("- " + i.getNombre() + (i.isOficial() ? " [OFICIAL]" : ""));
        }
    }

    private void vistaCompararPaises() {
        System.out.print("País 1: ");
        Pais p1 = paisDAO.buscarPorNombre(leer.nextLine());
        System.out.print("País 2: ");
        Pais p2 = paisDAO.buscarPorNombre(leer.nextLine());
        
        if (p1 != null && p2 != null) {
            System.out.println("=== COMPARATIVA DE POBLACIÓN ===");
            System.out.println(p1.getNombre() + ": " + p1.getPoblacion());
            System.out.println(p2.getNombre() + ": " + p2.getPoblacion());
            if(p1.getPoblacion() > p2.getPoblacion()) 
                System.out.println(">> " + p1.getNombre() + " tiene mayor población.");
            else 
                System.out.println(">> " + p2.getNombre() + " tiene mayor población.");
        } else {
            System.out.println("Uno de los países no fue encontrado.");
        }
    }

    // Adaptación REQ 7
    private void vistaListarTodos() {
        System.out.println("(Nota: Campo 'Año Independencia' no existe en DB. Listando todos los países...)");
        List<Pais> lista = paisDAO.listarTodos();
        for (Pais p : lista) {
            System.out.println(p.getCodigo() + " - " + p.getNombre());
        }
    }

    // Adaptación REQ 8
    private void vistaIdiomasFrecuencia() {
        System.out.println("(Nota: Campo 'Porcentaje' no existe. Funcionalidad limitada.)");
        System.out.println("Consulta tu tabla 'Idioma' en Workbench para ver estadísticas.");
    }

    // Adaptación REQ 9
    private void vistaCiudadesPrincipales() {
        System.out.println("(Nota: La DB no marca cuál es la Capital. Listando ciudades registradas...)");
        vistaCiudadesPorPais();
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

