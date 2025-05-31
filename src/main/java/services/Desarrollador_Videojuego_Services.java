package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Desarrollador;
import models.Desarrollador_Videojuego;
import models.Videojuego;

public class Desarrollador_Videojuego_Services {

    //-----------------------------------------Insetar una nueva relacion Videojuego-Desarrollador ------------------------------------
    public void insertarVideojuegoDesarrollador(Integer idDesarrollador, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_desarrollador_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idDesarrollador);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar una  relacion Videojuego-Desarrollador----------------------------------------
    public void eliminarVideojuegoDesarrollador(Integer idDesarrollador, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_desarrollador_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idDesarrollador);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar una  relacion Videojuego-Desarrollador---------------------------------------------
    public void actualizarVideojuegoDesarrollador(Integer idDesarrollador, Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_desarrollador_videojuego(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idDesarrollador);
        preparedFunction.setInt(2, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todas las relaciones Videojuego-Desarrollador-----------------------------------------
    public ArrayList<Desarrollador_Videojuego> allVideojuegoDesarrollador() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Desarrollador_Videojuego> relaciones = new ArrayList<Desarrollador_Videojuego>();
        String function = "{?= call load_desarrollador_videojuego()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            relaciones.add(new Desarrollador_Videojuego(result.getInt(1),result.getInt(2)));
        result.close();
        preparedFunction.close();
        connection.close();

        return relaciones;
    }


    //------------------Buscar Todos los Videojuegos asociados a un Desarrollador Incompleto-----------------------------------------------
    public ArrayList<Videojuego> buscarVideojuegosDeDesarrollador(Integer idDesarrollador) throws SQLException, IllegalAccessException, IOException{

        ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
        Videojuego_Services v = ServicesLocator.getVideojuego();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM desarrollador_videojuego WHERE desarrollador_videojuego.id_desarrollador=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idDesarrollador);
        ResultSet result = statement.executeQuery();


        while(result.next())
            videojuegos.add(v.buscarVideojuego(result.getInt(2)));
        result.close();
        statement.close();
        connection.close();

        return videojuegos;
    }

    //------------------Buscar Todos los Desarrolladores asociados a un Videouego-----------------------------------------------
    public ArrayList<Desarrollador> buscarDesarrolladoresDeVideojuego(Integer idVideojuego) throws SQLException, IllegalAccessException, IOException{

        ArrayList<Desarrollador> desarrolladores = new ArrayList<Desarrollador>();
        Desarrollador_Services d = ServicesLocator.getDesarrollador();
        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM desarrollador_videojuego WHERE desarrollador_videojuego.id_videojuego=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idVideojuego);
        ResultSet result = statement.executeQuery();


        while (result.next())
            desarrolladores.add(d.buscarDesarrollador(result.getInt(1)));
        result.close();
        statement.close();
        connection.close();

        return desarrolladores;
    }
}
