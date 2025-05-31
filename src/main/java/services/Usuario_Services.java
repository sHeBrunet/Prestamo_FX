package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Usuario;

public class Usuario_Services {


    //-----------------------------------------Insetar un nuevo Usuario------------------------------------
    public void insertarUsuario(String nombreUsuario, Integer idAreaInteres) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_usuario(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombreUsuario);
        preparedFunction.setInt(2, idAreaInteres);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Usuario----------------------------------------
    public void eliminarUsuario(Integer idUsuario) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_usuario(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idUsuario);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar una Copia Videojuego---------------------------------------------
    public void actualizarUsuario(Integer idUsuario,String nombreUsuario, Integer idAreaInteres) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_usuario(?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idUsuario);
        preparedFunction.setString(2, nombreUsuario);
        preparedFunction.setInt(3, idAreaInteres);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los Usuarios-----------------------------------------
    public ArrayList<Usuario> load_usuario () throws SQLException, ClassNotFoundException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        String function = "{ ? = call load_usuario()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        try {
            CallableStatement preparedFunction = connection.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.REF_CURSOR);
            preparedFunction.execute();
            ResultSet result = (ResultSet) preparedFunction.getObject(1);

            while (result.next()) {
                usuarios.add(new Usuario(result.getInt(1), result.getString(2), result.getInt(3)));
            }
            result.close();
            preparedFunction.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            connection.close();
        }

        return usuarios;
    }


    //----------------------------------------Buscar 1 Desarrollador por su Identificador-----------------------------------------------
    public Usuario buscarUsuario(Integer idUsuario) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM usuario WHERE usuario.id_usuario=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idUsuario);
        ResultSet result = statement.executeQuery();


        result.next();
        Usuario usuario = new Usuario(result.getInt(1),result.getString(2),result.getInt(3));
        result.close();
        statement.close();
        connection.close();

        return usuario;
    }
}
