package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Area_Interes;

public class Area_Interes_Services {


    //----------------------------------------Insetar un nuevo tipo de Area de Interes------------------------------------
    public void insertarAreaInteres(String nombreArea) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_area_interes(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombreArea);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------Eliminar un tipo de Area de Interes-----------------------------------------
    public void eliminarAreaInteres(Integer idArea) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_area_interes(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idArea);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Actualizar un tipo de Area de Interes-----------------------------------------------
    public void actualizarAreaInteres(Integer idArea, String nombreArea) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_area_interes(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idArea);
        preparedFunction.setString(2, nombreArea);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------Devolver todos los tipos de Areas de Interes--------------------------------------------
    public ArrayList<Area_Interes> listado_de_areas() throws SQLException, IOException {
        ArrayList<Area_Interes> areas = new ArrayList<>();
        String function = "{?= call load_area_interes()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            areas.add(new Area_Interes(result.getInt(1),result.getString(2)));
        result.close();
        preparedFunction.close();
        connection.close();

        return areas;
    }

    //-------------------------------------Buscar 1 Area Interes por su Identificador-------------------------------------
    public Area_Interes buscarAreaInteres(Integer id_area) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM Area_Interes WHERE Area_Interes.ID_Area_Interes=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_area);
        ResultSet result = statement.executeQuery();


        result.next();
        Area_Interes area = new Area_Interes(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();


        return area;
    }
}
