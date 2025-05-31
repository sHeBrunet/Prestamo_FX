package models;

public class Pais_Origen {
    private Integer ID_Pais;
    private String Nombre_Pais;


    public Pais_Origen(Integer iD_Pais, String nombre_Pais) {
        setID_Pais(iD_Pais);
        setNombre_Pais(nombre_Pais);
    }

    public Integer getID_Pais() {return ID_Pais;}
    public String getNombre_Pais() {return Nombre_Pais;}


    public void setID_Pais(Integer iD_Pais) {ID_Pais = iD_Pais;}
    public void setNombre_Pais(String nombre_Pais) {Nombre_Pais = nombre_Pais;}
}
