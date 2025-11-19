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
import modelo.Ciudad;

public class CiudadDAO implements CrudDAO<Ciudad> {
    
    // REQ 2: Buscar ciudad
    public Ciudad buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM Ciudad WHERE nombreCiudad LIKE ?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return new Ciudad(
                    rs.getInt("idCiudad"), rs.getString("nombreCiudad"), 
                    rs.getInt("poblacionCiudad"), rs.getString("codigoPais"));
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // REQ 4: Ciudades por país
    public List<Ciudad> listarPorPais(String codigoPais) {
        List<Ciudad> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ciudad WHERE codigoPais = ?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, codigoPais);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) lista.add(new Ciudad(
                    rs.getInt("idCiudad"), rs.getString("nombreCiudad"), 
                    rs.getInt("poblacionCiudad"), rs.getString("codigoPais")));
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    // REQ 10: Ciudades más pobladas
    public void listarMasPobladas(int limite) {
        String sql = "SELECT c.nombreCiudad, c.poblacionCiudad, p.nombrePais " +
                     "FROM Ciudad c JOIN Pais p ON c.codigoPais = p.codigoPais " +
                     "ORDER BY c.poblacionCiudad DESC LIMIT ?";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, limite);
            ResultSet rs = ps.executeQuery();
            System.out.printf("%-25s %-15s %-20s%n", "CIUDAD", "POBLACION", "PAIS");
            while (rs.next()) {
                System.out.printf("%-25s %-15s %-20s%n", 
                    rs.getString("nombreCiudad"), rs.getInt("poblacionCiudad"), rs.getString("nombrePais"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override public void insertar(Ciudad entidad) {}
    @Override public void actualizar(Ciudad entidad) {}
    @Override public void eliminar(int id) {}
    @Override public Ciudad obtenerPorId(int id) { return null; }
    @Override public List<Ciudad> listarTodos() { return null; }
}
