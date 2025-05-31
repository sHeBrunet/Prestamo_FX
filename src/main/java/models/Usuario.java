package models;

public class Usuario {
    private Integer ID_Usuario;
    private String Nombre_Usuario;
    private Integer ID_Area_Interes;

    public Usuario(Integer iD_Usuario, String nombre_Usuario, Integer iD_Area_Interes) {
        setID_Area_Interes(iD_Area_Interes);
        setID_Usuario(iD_Usuario);
        setNombre_Usuario(nombre_Usuario);
    }


    public Integer getID_Usuario() {return ID_Usuario;}
    public String getNombre_Usuario() {return Nombre_Usuario;}
    public Integer getID_Area_Interes() {return ID_Area_Interes;}


    public void setID_Usuario(Integer iD_Usuario) {ID_Usuario = iD_Usuario;}
    public void setNombre_Usuario(String nombre_Usuario) {Nombre_Usuario = nombre_Usuario;}
    public void setID_Area_Interes(Integer iD_Area_Interes) {ID_Area_Interes = iD_Area_Interes;}

}
