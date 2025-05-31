package services;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Estado_Copia;

public class Estado_Copia_Services {

    //-----------------------------------------Insetar un nuevo tipo de Estado Copia------------------------------------
    public void insertarEstadoCopia(String nombreEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call insert_estado_copia(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setString(1, nombreEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }

    //----------------------------------------------Eliminar un tipo de Estado Copia----------------------------------------
    public void eliminarEstadoCopia(Integer idEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call delete_estado_copia(?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //------------------------------------------Actualizar un tipo de Estado de Copia---------------------------------------------
    public void actualizarEstadoCopia(Integer idEstado, String nombreEstado) throws SQLException, ClassNotFoundException, IOException {

        String function = "{ call update_estado_copia(?,?)}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.setInt(1, idEstado);
        preparedFunction.setString(2, nombreEstado);
        preparedFunction.execute();
        preparedFunction.close();
        connection.close();
    }


    //--------------------------------------Devolver todos los tipos de Estados de Copias-----------------------------------------
    public ArrayList<Estado_Copia> allEstadosCopias() throws SQLException, ClassNotFoundException, IOException{

        ArrayList<Estado_Copia> estados=new ArrayList<Estado_Copia>();
        String function = "{?= call load_estado_copia()}";
        java.sql.Connection connection = ServicesLocator.getConnection();
        connection.setAutoCommit(false);
        CallableStatement preparedFunction = connection.prepareCall(function);

        preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
        preparedFunction.execute();
        ResultSet result = (ResultSet) preparedFunction.getObject(1);
        while (result.next()){
            estados.add(new Estado_Copia(result.getInt(1),result.getString(2)));
        }
        result.close();
        preparedFunction.close();
        connection.close();

        return estados;
    }


    //----------------------------------------Buscar 1 Estado Copia por su Identificador-----------------------------------------------
    public Estado_Copia buscarEstadoCopia(Integer idEstado) throws SQLException, IllegalAccessException, IOException{

        java.sql.Connection connection = ServicesLocator.getConnection();
        String sql = "SELECT * FROM estado_copia WHERE estado_copia.id_estado_copia=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEstado);
        ResultSet result = statement.executeQuery();


        result.next();
        Estado_Copia estado = new Estado_Copia(result.getInt(1),result.getString(2));
        result.close();
        statement.close();
        connection.close();

        return estado;
    }
}
