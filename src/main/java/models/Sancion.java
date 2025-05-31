package models;

public class Sancion {
    private Integer ID_Sancion;
    private java.sql.Date Fecha_Imposicion;
    private java.sql.Date Fecha_Finalizacion;
    private Integer ID_Tipo_Sancion;
    private Integer ID_Usuario;


    public Sancion(Integer iD_Sancion, java.sql.Date fecha_Imposicion, java.sql.Date fecha_Finalizacion, Integer iD_Tipo_Sancion,
                   Integer iD_Usuario) {
        setFecha_Finalizacion(fecha_Finalizacion);
        setFecha_Imposicion(fecha_Imposicion);
        setID_Sancion(iD_Sancion);
        setID_Tipo_Sancion(iD_Tipo_Sancion);
        setID_Usuario(iD_Usuario);
    }

    public Integer getID_Sancion() {return ID_Sancion;}
    public java.sql.Date getFecha_Imposicion() {return Fecha_Imposicion;}
    public java.sql.Date getFecha_Finalizacion() {return Fecha_Finalizacion;}
    public Integer getID_Tipo_Sancion() {return ID_Tipo_Sancion;}
    public Integer getID_Usuario() {return ID_Usuario;}


    public void setID_Sancion(Integer iD_Sancion) {ID_Sancion = iD_Sancion;}
    public void setFecha_Imposicion(java.sql.Date fecha_Imposicion) {Fecha_Imposicion = fecha_Imposicion;}
    public void setFecha_Finalizacion(java.sql.Date fecha_Finalizacion) {Fecha_Finalizacion = fecha_Finalizacion;}
    public void setID_Tipo_Sancion(Integer iD_Tipo_Sancion) {ID_Tipo_Sancion = iD_Tipo_Sancion;}
    public void setID_Usuario(Integer iD_Usuario) {ID_Usuario = iD_Usuario;}

}
