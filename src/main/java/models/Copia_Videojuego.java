package models;

public class Copia_Videojuego {
    private Integer ID_Videojuego;
    private Integer Num_Serie;
    private Integer ID_Estado_Copia;


    //------------------Constructor------------------------------------------
    public Copia_Videojuego(Integer iD_Videojuego, Integer num_Serie, Integer iD_Estado_Copia) {
        setID_Estado_Copia(iD_Estado_Copia);
        setID_Videojuego(iD_Videojuego);
        setNum_Serie(num_Serie);
    }

    //--------------------Getters----------------------------------------
    public Integer getID_Videojuego() {return ID_Videojuego;}
    public Integer getNum_Serie() {return Num_Serie;}
    public Integer getID_Estado_Copia() {return ID_Estado_Copia;}

    //---------------------Setters--------------------------------------
    public void setID_Videojuego(Integer iD_Videojuego) {ID_Videojuego = iD_Videojuego;}
    public void setNum_Serie(Integer num_Serie) {Num_Serie = num_Serie;}
    public void setID_Estado_Copia(Integer iD_Estado_Copia) {ID_Estado_Copia = iD_Estado_Copia;}
}
