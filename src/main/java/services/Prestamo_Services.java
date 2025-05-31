package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Prestamo;

public class Prestamo_Services {
    //-----------------------------------------Insetar Prestamo------------------------------------
    public void insertarPrestamo(Integer idVideojuego,Integer numSerie,Integer idUsuario,
                                 Integer idEstadoPrestamo, Date fechaDevolucion) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_prestamo(?,?,?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, numSerie);
        preparedFunction.setInt(3, idUsuario);
        preparedFunction.setInt(4, idEstadoPrestamo);
        preparedFunction.setDate(5, fechaDevolucion);


        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un Prestamo----------------------------------------
    public void eliminarPrestamo(Integer idVideojuego, Integer numSerie, Integer idUsuario) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_prestamo(?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, numSerie);
        preparedFunction.setInt(3, idUsuario);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un Prestamo---------------------------------------------
    public void actualizarPrestamo(Integer idVideojuego,Integer numSerie,Integer idUsuario,
                                   Integer idEstadoPrestamo, Date fechaDevolucion) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_prestamo(?,?,?,?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idVideojuego);
        preparedFunction.setInt(2, numSerie);
        preparedFunction.setInt(3, idUsuario);
        preparedFunction.setInt(4, idEstadoPrestamo);
        preparedFunction.setDate(5, fechaDevolucion);

        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todas las Sanciones-----------------------------------------
    public ArrayList<Prestamo> load_prestamo() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        String function = "{?= call load_prestamo()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            prestamos.add(new Prestamo(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getDate(5),result.getDate(6),result.getInt(7)));
        result.close();
        preparedFunction.close();
        connection.close();

        return prestamos;
    }

    public ArrayList<Prestamo> load_prestamo_activos() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        String function = "{?= call load_prestamos_activo()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            prestamos.add(new Prestamo(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getDate(5),result.getDate(6),result.getInt(7)));
        result.close();
        preparedFunction.close();
        connection.close();

        return prestamos;
    }

    public ArrayList<Prestamo> load_prestamo_finalizados() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        String function = "{?= call load_prestamo_finalizados()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);

        CallableStatement preparedFunction = connection.prepareCall(function);
        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);

        while (result.next())
            prestamos.add(new Prestamo(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getDate(5),result.getDate(6),result.getInt(7)));
        result.close();
        preparedFunction.close();
        connection.close();

        return prestamos;
    }

    //----------------------------------------Buscar 1 Sancion por su Identificador-----------------------------------------------
    public Prestamo buscarPrestamo(Integer idPrestamo) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM prestamo WHERE prestamo.id_prestamo=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idPrestamo);
        ResultSet result = statement.executeQuery();


        result.next();
        Prestamo prestamo = new Prestamo(result.getInt(1),result.getInt(2),result.getInt(3),result.getInt(4),result.getDate(5),result.getDate(6),result.getInt(7));
        result.close();
        statement.close();
        connection.close();

        return prestamo;
    }
}
