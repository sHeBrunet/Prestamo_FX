package models;

public class Desarrollador_Videojuego {
    private Integer ID_Desarrollador;
    private Integer ID_Videojuego;

    public Desarrollador_Videojuego(Integer iD_Desarrollador, Integer iD_Videojuego) {
        setID_Desarrollador(iD_Desarrollador);
        setID_Videojuego(iD_Videojuego);
    }

    public Integer getID_Desarrollador() {return ID_Desarrollador;}
    public Integer getID_Videojuego() {return ID_Videojuego;}

    public void setID_Desarrollador(Integer iD_Desarrollador) {ID_Desarrollador = iD_Desarrollador;}
    public void setID_Videojuego(Integer iD_Videojuego) {ID_Videojuego = iD_Videojuego;}
}
