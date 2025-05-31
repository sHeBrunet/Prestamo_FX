package models;

public class Prestamo {
    private Integer ID_Videojuego;
    private Integer Num_Serie;
    private Integer ID_Usuario;
    private Integer ID_Estado_Prestamo;
    private java.sql.Date Fecha_Devolucion;
    private java.sql.Date Fecha_Inicio;
    private Integer ID_Prestamo;


    public Prestamo(Integer ID_Videojuego, Integer num_Serie, Integer iD_Usuario, Integer iD_Estado_Prestamo,
                    java.sql.Date fecha_Devolucion,java.sql.Date FechaInicio, Integer IdPrestamo) {
        setFecha_Devolucion(fecha_Devolucion);
        setID_Estado_Prestamo(iD_Estado_Prestamo);
        setID_Usuario(iD_Usuario);
        setID_Videojuego(ID_Videojuego);
        setNum_Serie(num_Serie);
        setFecha_Inicio(FechaInicio);
        setID_Prestamo(IdPrestamo);
    }


    public Integer getID_Videojuego() {return ID_Videojuego;}
    public Integer getNum_Serie() {return Num_Serie;}
    public Integer getID_Usuario() {return ID_Usuario;}
    public Integer getID_Estado_Prestamo() {return ID_Estado_Prestamo;}
    public java.sql.Date getFecha_Devolucion() {return Fecha_Devolucion;}
    public java.sql.Date getFecha_Inicio() {return Fecha_Inicio;}
    public Integer getID_Prestamo() {return ID_Prestamo;}

    public void setID_Videojuego(Integer iD_Videojuego) {ID_Videojuego = iD_Videojuego;}
    public void setNum_Serie(Integer num_Serie) {Num_Serie = num_Serie;}
    public void setID_Usuario(Integer iD_Usuario) {ID_Usuario = iD_Usuario;}
    public void setID_Estado_Prestamo(Integer iD_Estado_Prestamo) {ID_Estado_Prestamo = iD_Estado_Prestamo;}
    public void setFecha_Devolucion(java.sql.Date fecha_Devolucion) {Fecha_Devolucion = fecha_Devolucion;}
    public void setFecha_Inicio(java.sql.Date fecha_Inicio) {Fecha_Inicio = fecha_Inicio;}
    public void setID_Prestamo(Integer iD_Prestamo) {ID_Prestamo = iD_Prestamo;}
}
