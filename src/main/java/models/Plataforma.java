package models;

public class Plataforma {
    private Integer ID_Plataforma;
    private String Nombre_Plataforma;


    public Plataforma(Integer iD_Plataforma, String nombre_Plataforma) {
        setID_Plataforma(iD_Plataforma);
        setNombre_Plataforma(nombre_Plataforma);
    }

    public Integer getID_Plataforma() {return ID_Plataforma;}
    public String getNombre_Plataforma() {return Nombre_Plataforma;}

    public void setID_Plataforma(Integer iD_Plataforma) {ID_Plataforma = iD_Plataforma;}
    public void setNombre_Plataforma(String nombre_Plataforma) {Nombre_Plataforma = nombre_Plataforma;}
}
