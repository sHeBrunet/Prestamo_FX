package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Pais_Origen;

public class Pais_Origen_Services {
    //-----------------------------------------Insetar un nuevo Pais------------------------------------
    public void insertarPaisOrigen(String nombrePais) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_pais(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombrePais);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Pais----------------------------------------
    public void eliminarPaisOrigen(Integer idPais) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_pais_origen(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPais);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Pais---------------------------------------------
    public void actualizarPaisOrigen(Integer idPais, String nombrePais) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_pais(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPais);
        preparedFunction.setString(2, nombrePais);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los Paises-----------------------------------------
    public ArrayList<Pais_Origen> load_pais() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Pais_Origen> paises = new ArrayList<Pais_Origen>();
        String function = "{?= call load_pais()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);
        while (result.next()){
            paises.add(new Pais_Origen(result.getInt(1),result.getString(2)));
        }
        result.close();
        preparedFunction.close();
        connection.close();

        return paises;
    }


    //----------------------------------------Buscar 1 Pais por su Identificador-----------------------------------------------
    public Pais_Origen buscarPais(Integer idPais) throws SQLException, IllegalAccessException, IOException{
        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM pais_origen WHERE pais_origen.id_pais=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idPais);
        ResultSet result = statement.executeQuery();


        result.next();
        Pais_Origen pais = new Pais_Origen(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();

        return pais;
    }
}
