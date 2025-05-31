package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Copia_Videojuego;


public class Copia_Videojuego_Services {
    //-----------------------------------------Insetar una nueva Copia Videojuego------------------------------------
    public void insertarCopia(Integer idVideojuego, Integer idEstadoCopia) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_copia_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, idEstadoCopia);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar una Copia Videojuego----------------------------------------
    public void eliminarCopia(Integer idVideojuego, Integer numSerie) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_copia_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, numSerie);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar una Copia Videojuego---------------------------------------------
    public void actualizarCopia(Integer idVideojuego,Integer numSerie, Integer idEstadoCopia) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_copia(?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, numSerie);
        preparedFunction.setInt(3, idEstadoCopia);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos las copias Videojuegos-----------------------------------------
    public ArrayList<Copia_Videojuego> load_copia() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Copia_Videojuego> copias = new ArrayList<Copia_Videojuego>();
        String function = "{?= call load_copia()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            copias.add(new Copia_Videojuego(result.getInt(1),result.getInt(2),result.getInt(3)));
        result.close();
        preparedFunction.close();
        connection.close();

        return copias;
    }

    public ArrayList<Copia_Videojuego> load_copia_prestadas() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Copia_Videojuego> copias = new ArrayList<Copia_Videojuego>();
        String function = "{?= call load_copia_prestada()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            copias.add(new Copia_Videojuego(result.getInt(1),result.getInt(2),result.getInt(3)));
        result.close();
        preparedFunction.close();
        connection.close();

        return copias;
    }

    public ArrayList<Copia_Videojuego> load_copia_disponibles() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Copia_Videojuego> copias = new ArrayList<Copia_Videojuego>();
        String function = "{?= call load_copia_disponible()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            copias.add(new Copia_Videojuego(result.getInt(1),result.getInt(2),result.getInt(3)));
        result.close();
        preparedFunction.close();
        connection.close();

        return copias;
    }


    //---------------------------Buscar Todas las Copias de un Videojuego por su Identificador-----------------------------------------------
    public ArrayList<Copia_Videojuego> buscarCopiasDeVideojuego(Integer idVideojuego) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        ArrayList<Copia_Videojuego> copias = new ArrayList<Copia_Videojuego>();
        String sql = "SELECT * FROM copia_videojuego WHERE copia_videojuego.id_videojuego=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idVideojuego);
        ResultSet result = statement.executeQuery();


        while(result.next())
            copias.add(new Copia_Videojuego(result.getInt(1),result.getInt(2),result.getInt(3)));
        result.close();
        statement.close();
        connection.close();

        return copias;
    }
}
