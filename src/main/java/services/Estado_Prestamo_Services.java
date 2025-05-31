package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Estado_Prestamo;

public class Estado_Prestamo_Services {
    //-----------------------------------------Insetar un nuevo tipo de Estado Prestamo------------------------------------
    public void insertarEstadoPrestamo(String nombreEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_estado_prestamo(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombreEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un tipo de Estado Prestamo----------------------------------------
    public void eliminarEstadoPrestamo(Integer idEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_estado_prestamo(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un tipo de Estado de Prestamo---------------------------------------------
    public void actualizarEstadoPrestamo(Integer idEstado, String nombreEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_estado_prestamo(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idEstado);
        preparedFunction.setString(2, nombreEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los tipos de Estados de PRestamo-----------------------------------------
    public ArrayList<Estado_Prestamo> load_estado_prestamo() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Estado_Prestamo> estados = new ArrayList<Estado_Prestamo>();
        String function = "{?= call load_estado_prestamo()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);
        while (result.next()){
            estados.add(new Estado_Prestamo(result.getInt(1),result.getString(2)));
        }
        result.close();
        preparedFunction.close();
        connection.close();

        return estados;
    }


    //----------------------------------------Buscar 1 Estado Prestamo por su Identificador-----------------------------------------------
    public Estado_Prestamo buscarEstadoPrestamo(Integer idEstado) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM estado_prestamo WHERE estado_prestamo.id_estado_prestamo=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEstado);
        ResultSet result = statement.executeQuery();


        result.next();
        Estado_Prestamo estado = new Estado_Prestamo(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();


        return estado;
    }
}
