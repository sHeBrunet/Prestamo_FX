package services;

import java.io.IOException;
import java.sql.SQLException;

import utils.Connection;

public class ServicesLocator {

    private static Area_Interes_Services Area_Interes = null;
    private static Copia_Videojuego_Services Copia_Videojuego = null;
    private static Desarrollador_Videojuego_Services Desarrollador_Videojuego = null;
    private static Desarrollador_Services Desarrollador = null;
    private static Estado_Copia_Services Estado_Copia = null;
    private static Estado_Prestamo_Services Estado_Prestamo = null;
    private static Pais_Origen_Services Pais_Origen = null;
    private static Plataforma_Services Plataforma = null;
    private static Plataforma_Videojuego_Services Plataforma_Videojuego = null;
    private static Prestamo_Services Prestamo = null;
    private static Sancion_Services Sancion = null;
    private static Tipo_Sancion_Services Tipo_Sancion = null;
    private static Usuario_Services Usuario = null;
    private static Videojuego_Services Videojuego = null;




    public static Area_Interes_Services getArea_Interes() {
        if(Area_Interes == null)
            Area_Interes = new Area_Interes_Services();
        return Area_Interes;
    }

    public static Copia_Videojuego_Services getCopia_Videojuego() {
        if(Copia_Videojuego == null)
            Copia_Videojuego = new Copia_Videojuego_Services();
        return Copia_Videojuego;
    }

    public static Desarrollador_Videojuego_Services getDesarrollador_Plataforma() {
        if(Desarrollador_Videojuego == null)
            Desarrollador_Videojuego = new Desarrollador_Videojuego_Services();
        return Desarrollador_Videojuego;
    }

    public static Desarrollador_Services getDesarrollador() {
        if(Desarrollador == null)
            Desarrollador = new Desarrollador_Services();
        return Desarrollador;
    }

    public static Estado_Copia_Services getEstado_Copia() {
        if(Estado_Copia == null)
            Estado_Copia = new Estado_Copia_Services();
        return Estado_Copia;
    }

    public static Estado_Prestamo_Services getEstado_Prestamo() {
        if(Estado_Prestamo == null)
            Estado_Prestamo = new Estado_Prestamo_Services();
        return Estado_Prestamo;
    }

    public static Pais_Origen_Services getPais_Origen() {
        if(Pais_Origen == null)
            Pais_Origen = new Pais_Origen_Services();
        return Pais_Origen;
    }

    public static Plataforma_Services getPlataforma() {
        if(Plataforma == null)
            Plataforma = new Plataforma_Services();
        return Plataforma;
    }

    public static Plataforma_Videojuego_Services getPlataforma_Videojuego() {
        if(Plataforma_Videojuego == null)
            Plataforma_Videojuego = new Plataforma_Videojuego_Services();
        return Plataforma_Videojuego;
    }

    public static Prestamo_Services getPrestamo() {
        if(Prestamo == null)
            Prestamo = new Prestamo_Services();
        return Prestamo;
    }

    public static Sancion_Services getSancion() {
        if(Sancion == null)
            Sancion = new Sancion_Services();
        return Sancion;
    }

    public static Tipo_Sancion_Services getTipo_Sancion() {
        if(Tipo_Sancion == null)
            Tipo_Sancion = new Tipo_Sancion_Services();
        return Tipo_Sancion;
    }

    public static Usuario_Services getUsuario() {
        if(Usuario == null)
            Usuario  = new Usuario_Services();
        return Usuario;
    }

    public static Videojuego_Services getVideojuego() {
        if(Videojuego == null)
            Videojuego = new Videojuego_Services();
        return Videojuego;
    }


    public static java.sql.Connection getConnection() throws IOException{

        Connection connection = null;
        String URL = "jdbc:postgresql://localhost:5433/PrestamoVideotecaDEFINITIVO";
        String USER ="postgres";
        String PASS = "Sheila130305";

        try { connection = new Connection(URL,USER,PASS); }

        catch (ClassNotFoundException e) {e.printStackTrace();}
        catch (SQLException e) {e.printStackTrace();}
        return connection.getConnection();
    }


}
