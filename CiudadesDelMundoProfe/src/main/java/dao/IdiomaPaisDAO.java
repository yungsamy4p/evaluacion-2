/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conn.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.IdiomaPais;

/**
 *
 * @author Santo Tomas
 */
public class IdiomaPaisDAO implements CrudDAO<IdiomaPais> {

    @Override
    public void insertar(IdiomaPais entidad) {
        String sql = "INSERT INTO Idioma (nombreIdioma, oficial, codigoPais) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, entidad.getNombre());
            ps.setBoolean(2, entidad.isOficial());
            ps.setString(3, entidad.getCodigo());

            int filas = ps.executeUpdate();
            //return filas > 0;
        } catch (SQLException e) {
            System.out.println("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(IdiomaPais entidad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IdiomaPais obtenerPorId(int id) {
        String sql = "SELECT * FROM Idioma WHERE id = ?";
        try (
                Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id); // asignar el id al parámetro

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("ID: " + rs.getInt("idIdioma"));
                System.out.println("nombreIdioma: " + rs.getString("nombreIdioma"));
                System.out.println("oficial: " + rs.getBoolean("oficial"));
                System.out.println("codigoPais: " + rs.getString("codigoPais"));

                IdiomaPais idioma = new IdiomaPais(
                        rs.getInt("idIdioma"),
                        rs.getString("nombreIdioma"),
                        rs.getBoolean("oficial"),
                        rs.getString("codigoPais")
                );

                return idioma;
            } else {
                System.out.println("No se encontró ningún idioma con ID " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar idioma: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<IdiomaPais> listarTodos() {
        List<IdiomaPais> listado = new ArrayList<>();
        String sql = "SELECT * FROM Idioma";

        try (Connection conn = Conexion.getInstancia(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                IdiomaPais idioma = new IdiomaPais(
                        rs.getInt("idIdioma"),
                        rs.getString("nombreIdioma"),
                        rs.getBoolean("oficial"),
                        rs.getString("codigoPais")
                );

//                u.setFecha(rs.getTimestamp("fecha_registro").toLocalDateTime());
                listado.add(idioma);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }

        return listado;
    }

}
