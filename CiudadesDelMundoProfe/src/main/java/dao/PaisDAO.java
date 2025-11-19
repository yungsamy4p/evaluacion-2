/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author samue
 */

import conn.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Pais;

public class PaisDAO implements CrudDAO<Pais> {

    @Override
    public Pais obtenerPorId(int id) { return null; } // No usado porque la PK es String
    
    // Implementación básica requerida por la interfaz (puedes dejarla vacía si no insertas datos)
    @Override public void insertar(Pais entidad) {}
    @Override public void actualizar(Pais entidad) {}
    @Override public void eliminar(int id) {}
    
    @Override
    public List<Pais> listarTodos() {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pais";
        try (Connection conn = Conexion.getInstancia(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    // REQ 1: Buscar por nombre
    public Pais buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM Pais WHERE nombrePais LIKE ?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // REQ 3: Listar por continente
    public List<Pais> listarPorContinente(String continente) {
        List<Pais> lista = new ArrayList<>();
        String sql = "SELECT * FROM Pais WHERE continentePais = ?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, continente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // Método auxiliar para convertir ResultSet a Objeto
    private Pais mapear(ResultSet rs) throws SQLException {
        return new Pais(
            rs.getString("codigoPais"),
            rs.getString("nombrePais"),
            rs.getString("continentePais"),
            rs.getInt("poblacionPais"),
            rs.getBoolean("tipoGobierno")
        );
    }
}