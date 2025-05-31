package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Desarrollador;


public class Desarrollador_Services {

    //-----------------------------------------Insetar un nuevo Desarrollador------------------------------------
    public void insertarDesarrollador(String nombreDesarrollador) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_desarrollador(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);
        ;
        preparedFunction.setString(1, nombreDesarrollador);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Desarrollador----------------------------------------
    public void eliminarDesarrollador(Integer idDesarrollador) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_desarrollador(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idDesarrollador);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Desarrollador---------------------------------------------
    public void actualizarDesarrollador(Integer idDesarrollador, String nombreDesarrollador) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_desarrollador(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idDesarrollador);
        preparedFunction.setString(2, nombreDesarrollador);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los Desarrolladores-----------------------------------------
    public ArrayList<Desarrollador> load_desarrollador() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Desarrollador> desarrolladores = new ArrayList<Desarrollador>();
        String function = "{?= call load_desarrollador()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            desarrolladores.add(new Desarrollador(result.getInt(1),result.getString(2)));
        result.close();
        preparedFunction.close();
        connection.close();

        return desarrolladores;
    }


    //----------------------------------------Buscar 1 Desarrollador por su Identificador-----------------------------------------------
    public Desarrollador buscarDesarrollador(Integer idDesarrollador) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM desarrollador WHERE desarrollador.id_desarrollador=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDesarrollador);
        ResultSet result = statement.executeQuery();


        result.next();
        Desarrollador desarrollador = new Desarrollador(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();

        return desarrollador;
    }
}
