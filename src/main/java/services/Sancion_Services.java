package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Sancion;

public class Sancion_Services {
    //-----------------------------------------Insetar Sancion------------------------------------
    public void insertarSancion( java.sql.Date fechaFinalizacion,Integer idTipoSancion,Integer idUsuario) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_sancion(?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setDate(1, fechaFinalizacion);
        preparedFunction.setInt(2, idTipoSancion);
        preparedFunction.setInt(3, idUsuario);


        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar una Sancion----------------------------------------
    public void eliminarSancion(Integer idSancion) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_sancion(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idSancion);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Videojuego---------------------------------------------
    public void actualizarSancion(Integer idSancion,java.sql.Date fechaImposicion, java.sql.Date fechaFinalizacion,
                                  Integer idTipoSancion,Integer idUsuario) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_sancion(?,?,?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idSancion);
        preparedFunction.setDate(2, fechaImposicion);
        preparedFunction.setDate(3, fechaFinalizacion);
        preparedFunction.setInt(4, idTipoSancion);
        preparedFunction.setInt(5, idUsuario);

        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todas las Sanciones-----------------------------------------
    public ArrayList<Sancion> load_sancion() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Sancion> sanciones = new ArrayList<Sancion>();
        String function = "{?= call load_sancion()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            sanciones.add(new Sancion(result.getInt(1),result.getDate(2),result.getDate(3),result.getInt(4),result.getInt(5)));
        result.close();
        preparedFunction.close();
        connection.close();

        return sanciones;
    }

    public ArrayList<Sancion> load_sancion_activa() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Sancion> sanciones = new ArrayList<Sancion>();
        String function = "{?= call load_sanciones_activas()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            sanciones.add(new Sancion(result.getInt(1),result.getDate(2),result.getDate(3),result.getInt(4),result.getInt(5)));
        result.close();
        preparedFunction.close();
        connection.close();

        return sanciones;
    }

    public ArrayList<Sancion> load_sancion_finalizadas() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Sancion> sanciones = new ArrayList<Sancion>();
        String function = "{?= call load_sanciones_finalizadas()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            sanciones.add(new Sancion(result.getInt(1),result.getDate(2),result.getDate(3),result.getInt(4),result.getInt(5)));
        result.close();
        preparedFunction.close();
        connection.close();

        return sanciones;
    }


    //----------------------------------------Buscar 1 Sancion por su Identificador-----------------------------------------------
    public Sancion buscarSancion(Integer idSancion) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM sancion WHERE sancion.id_sancion=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idSancion);
        ResultSet result = statement.executeQuery();


        result.next();
        Sancion sancion = new Sancion(result.getInt(1),result.getDate(2),result.getDate(3),result.getInt(4),result.getInt(5));
        result.close();
        statement.close();
        connection.close();

        return sancion;
    }
}
