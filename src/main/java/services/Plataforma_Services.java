package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Plataforma;

public class Plataforma_Services {
    //-----------------------------------------Insetar un nuevo Plataforma------------------------------------
    public void insertarPlataforma(String nombrePlataforma) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_plataforma(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombrePlataforma);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Plataforma----------------------------------------
    public void eliminarPlataforma(Integer idPlataforma) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_plataforma(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPlataforma);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Plataforma---------------------------------------------
    public void actualizarPlataforma(Integer idPlataforma, String nombrePlataforma) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_plataforma(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPlataforma);
        preparedFunction.setString(2, nombrePlataforma);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los Plataformas-----------------------------------------
    public ArrayList<Plataforma> load_plataforma() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Plataforma> plataformas = new ArrayList<Plataforma>();
        String function = "{?= call load_plataforma()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            plataformas.add(new Plataforma(result.getInt(1),result.getString(2)));
        result.close();
        preparedFunction.close();
        connection.close();

        return plataformas;
    }


    //----------------------------------------Buscar 1 Plataforma por su Identificador-----------------------------------------------
    public Plataforma buscarPlataforma(Integer idPlataforma) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM plataforma WHERE plataforma.id_plataforma=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idPlataforma);
        ResultSet result = statement.executeQuery();


        result.next();
        Plataforma plataforma = new Plataforma(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();

        return plataforma;
    }
}
