package models;

public class Desarrollador {
    private Integer ID_Desarrollador;
    private String Nombre_Desarrollador;

    public Desarrollador(Integer iD_Desarrollador, String nombre_Desarrollador) {
        setID_Desarrollador(iD_Desarrollador);
        setNombre_Desarrollador(nombre_Desarrollador);
    }

    public Integer getID_Desarrollador() {return ID_Desarrollador;}
    public String getNombre_Desarrollador() {return Nombre_Desarrollador;}


    public void setID_Desarrollador(Integer iD_Desarrollador) {ID_Desarrollador = iD_Desarrollador;}
    public void setNombre_Desarrollador(String nombre_Desarrollador) {Nombre_Desarrollador = nombre_Desarrollador;}
}
