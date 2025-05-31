package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Tipo_Sancion;

public class Tipo_Sancion_Services {

    //-----------------------------------------Insetar un nuevo tipo de Sancion------------------------------------
    public void insertarTipoSancion(String nombreTipo) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_tipo_sancion(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombreTipo);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un tipo de Sancion----------------------------------------
    public void eliminarTipoSancion(Integer idTipo) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_tipo_sancion(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idTipo);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un tipo de Sancion---------------------------------------------
    public void actualizarTipoSancion(Integer idTipo, String nombreTipo) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_tipo_sancion(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idTipo);
        preparedFunction.setString(2, nombreTipo);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los tipos de Sancion-----------------------------------------
    public ArrayList<Tipo_Sancion> load_tipo_sancion() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Tipo_Sancion> tiposSanciones = new ArrayList<Tipo_Sancion>();
        String function = "{?= load_tipo_sancion()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);
        while (result.next()){
            tiposSanciones.add(new Tipo_Sancion(result.getInt(1),result.getString(2)));
        }
        result.close();
        preparedFunction.close();
        connection.close();

        return tiposSanciones;
    }


    //----------------------------------------Buscar 1 Tipo Sancion por su Identificador-----------------------------------------------
    public Tipo_Sancion buscarTipoSancion(Integer idTipo) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM tipo_sancion WHERE tipo_sancion.id_tipo_sancion=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idTipo);
        ResultSet result = statement.executeQuery();


        result.next();
        Tipo_Sancion tipoSancion = new Tipo_Sancion(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();


        return tipoSancion;
    }
}
