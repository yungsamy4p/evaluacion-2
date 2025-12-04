/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conn.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Pais;

/**
 *
 * @author samue
 */

public class PaisDAO implements CrudDAO<Pais> {

    // Método no usado porque la PK es String, usaremos buscarPorNombre o buscarPorCodigo si lo implementas
    @Override
    public Pais obtenerPorId(int id) { return null; } 

    // INSERTAR
    @Override
    public void insertar(Pais entidad) {
        String sql = "INSERT INTO Pais (codigoPais, nombrePais, continentePais, poblacionPais, tipoGobierno) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entidad.getCodigo());
            ps.setString(2, entidad.getNombre());
            ps.setString(3, entidad.getContinente());
            ps.setInt(4, entidad.getPoblacion());
            ps.setBoolean(5, entidad.isTipoGobierno());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar país: " + e.getMessage());
        }
    }

    // ACTUALIZAR (Basado en el código del país)
    @Override
    public void actualizar(Pais entidad) {
        String sql = "UPDATE Pais SET nombrePais=?, continentePais=?, poblacionPais=?, tipoGobierno=? WHERE codigoPais=?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, entidad.getNombre());
            ps.setString(2, entidad.getContinente());
            ps.setInt(3, entidad.getPoblacion());
            ps.setBoolean(4, entidad.isTipoGobierno());
            ps.setString(5, entidad.getCodigo()); // WHERE clause
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar país: " + e.getMessage());
        }
    }

    // ELIMINAR (Sobrecargamos o cambiamos la firma en tu interfaz CrudDAO si es posible, 
    // pero aquí añado el método específico para String)
    public void eliminar(String codigo) {
        String sql = "DELETE FROM Pais WHERE codigoPais=?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar país: " + e.getMessage());
        }
    }
    
    // Implementación de la interfaz (recibe int), la dejamos vacía o lanzamos error
    @Override 
    public void eliminar(int id) { 
        System.out.println("Usar eliminar(String codigo) para Paises."); 
    }

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

    public Pais buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM Pais WHERE nombrePais = ?"; // Cambiado LIKE por = para búsqueda exacta en combos
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapear(rs);
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

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