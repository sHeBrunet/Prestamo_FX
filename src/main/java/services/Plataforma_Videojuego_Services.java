package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Plataforma;
import models.Plataforma_Videojuego;
import models.Videojuego;

public class Plataforma_Videojuego_Services {
    //-----------------------------------------Insetar una nueva relacion Videojuego-Plataforma ------------------------------------
    public void insertarVideojuegoPlataforma(Integer idPlataforma, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_plataforma_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPlataforma);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar una  relacion Videojuego-Plataforma----------------------------------------
    public void eliminarVideojuegoPlataforma(Integer idPlataforma, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_plataforma_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPlataforma);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar una  relacion Videojuego-Plataforma---------------------------------------------
    public void actualizarVideojuegoPlataforma(Integer idPlataforma, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_plataforma_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idPlataforma);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todas las relaciones Videojuego-Plataforma-----------------------------------------
    public ArrayList<Plataforma_Videojuego> loadVideojuegoPlataforma() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Plataforma_Videojuego> relaciones = new ArrayList<Plataforma_Videojuego>();
        String function = "{?= call load_plataforma_videojuego()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            relaciones.add(new Plataforma_Videojuego(result.getInt(1), result.getInt(2)));
        result.close();
        preparedFunction.close();
        connection.close();

        return relaciones;
    }


    //------------------Buscar Todos los Videojuegos asociados a una Plataforma-----------------------------------------------
    public ArrayList<Videojuego> buscarVideojuegosDePlataformas(Integer idPlataforma) throws SQLException, IllegalAccessException, IOException{

        ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
        Videojuego_Services v = ServicesLocator.getVideojuego();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM plataforma_videojuego WHERE plataforma_videojuego.id_plataforma=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idPlataforma);
        ResultSet result = statement.executeQuery();


        while(result.next())
            videojuegos.add(v.buscarVideojuego(result.getInt(2)));
        result.close();
        statement.close();
        connection.close();

        return videojuegos;
    }

    //------------------Buscar Todos los Plataformas asociados a un Videouego-----------------------------------------------
    public ArrayList<Plataforma> buscarPlataformasDeVideojuegos(Integer idVideojuego) throws SQLException, IllegalAccessException, IOException{

        ArrayList<Plataforma> plataformas = new ArrayList<Plataforma>();
        Plataforma_Services p = ServicesLocator.getPlataforma();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM plataforma_videojuego WHERE plataforma_videojuego.id_videojuego=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idVideojuego);
        ResultSet result = statement.executeQuery();


        while (result.next())
            plataformas.add(p.buscarPlataforma(result.getInt(1)));
        result.close();
        statement.close();
        connection.close();

        return plataformas;
    }
}
