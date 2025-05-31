package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Videojuego;

public class Videojuego_Services {
    //-----------------------------------------Insetar un nuevo Videojuego------------------------------------
    public void insertarVideojuego(String titulo, int anioLanzamiento,
                                   int cantJugadores,String descripcion,Integer id_pais, int cantCopiasExistencia) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_videojuego(?,?,?,?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, titulo);
        preparedFunction.setInt(2, anioLanzamiento);
        preparedFunction.setInt(3, cantJugadores);
        preparedFunction.setString(4, descripcion);
        preparedFunction.setInt(5, id_pais);
        preparedFunction.setInt(6, cantCopiasExistencia);

        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Videojuego----------------------------------------
    public void eliminarVideojuego(Integer idVideojuego) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_videojuego(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Videojuego---------------------------------------------
    public void actualizarVideojuego(Integer id_videojuego,String titulo, int anioLanzamiento,
                                     int cantJugadores,String descripcion,Integer id_pais, int cantCopiasExistencia) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_videojuego(?,?,?,?,?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, id_videojuego);
        preparedFunction.setString(2, titulo);
        preparedFunction.setInt(3, anioLanzamiento);
        preparedFunction.setInt(4, cantJugadores);
        preparedFunction.setString(5, descripcion);
        preparedFunction.setInt(6, id_pais);
        preparedFunction.setInt(7, cantCopiasExistencia);

        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los Videojuegos-----------------------------------------
    public ArrayList<Videojuego> load_videojuego() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
        String function = "{?= call load_videojuego()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            videojuegos.add(new Videojuego(result.getInt(1),result.getString(2),result.getInt(3),result.getInt(4),result.getString(5),result.getInt(6),result.getInt(7)));
        result.close();
        preparedFunction.close();
        connection.close();

        return videojuegos;
    }


    //----------------------------------------Buscar 1 Videojuego por su Identificador-----------------------------------------------
    public Videojuego buscarVideojuego(Integer idVideojuego) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM videojuego WHERE videojuego.id_videojuego=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idVideojuego);
        ResultSet result = statement.executeQuery();


        result.next();
        Videojuego videojuego = new Videojuego(result.getInt(1),result.getString(2),result.getInt(3),result.getInt(4),result.getString(5),result.getInt(6),result.getInt(7));
        result.close();
        statement.close();
        connection.close();

        return videojuego;
    }
}
