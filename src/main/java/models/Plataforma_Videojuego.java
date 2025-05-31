package models;

public class Plataforma_Videojuego {
    private Integer ID_Plataforma;
    private Integer ID_Videojuego;


    //----------------------Constructor---------------------------------
    public Plataforma_Videojuego(Integer iD_Plataforma, Integer iD_Videojuego) {
        setID_Plataforma(iD_Plataforma);
        setID_Videojuego(iD_Videojuego);
    }

    //-------------------Getters------------------------------------------------------
    public Integer getID_Plataforma() {return ID_Plataforma;}
    public Integer getID_Videojuego() {return ID_Videojuego;}

    //------------------------------Setters-------------------------------------------
    public void setID_Plataforma(Integer iD_Plataforma) {ID_Plataforma = iD_Plataforma;}
    public void setID_Videojuego(Integer iD_Videojuego) {ID_Videojuego = iD_Videojuego;}
}
