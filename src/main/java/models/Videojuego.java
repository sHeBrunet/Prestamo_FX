package models;

import java.time.Year;

public class Videojuego {
    private Integer ID_Videojuego;
    private String Titulo_Juego;
    private Integer Anio_Lanzamiento;
    private Integer Cantidad_Jugadores;
    private String Descripcion;
    private Integer ID_Pais_Origen;
    private Integer Cantidad_Copias_Existencia;

    //----------------------------------- Constructor-------------------------------------------
    public Videojuego(Integer ID_Videojuego, String Titulo_Juego, Integer Anio_Lanzamiento, Integer Cantidad_Jugadores,
                      String Descripcion, Integer ID_Pais_Origen, Integer Cantidad_Copias_Existencia) {

        setID_Videojuego(ID_Videojuego);
        setTitulo_Juego(Titulo_Juego);
        setAnio_Lanzamiento(Anio_Lanzamiento);
        setCantidad_Jugadores(Cantidad_Jugadores);
        setDescripcion(Descripcion);
        setID_Pais_Origen(ID_Pais_Origen);
        setCantidad_Copias_Existencia(Cantidad_Copias_Existencia);
    }

    // -------------------------------------------Getters------------------------------------------
    public Integer getID_Videojuego() {return ID_Videojuego;}
    public String getTitulo_Juego() {return Titulo_Juego;}
    public Integer getAnio_Lanzamiento() {return Anio_Lanzamiento;}
    public Integer getCantidad_Jugadores() {return Cantidad_Jugadores;}
    public String getDescripcion() {return Descripcion;}
    public Integer getID_Pais_Origen() {return ID_Pais_Origen;}
    public Integer getCantidad_Copias_Existencia() {return Cantidad_Copias_Existencia;}

    // --------------------------------------Setters--------------------------------
    public void setID_Videojuego(Integer ID_Videojuego) {this.ID_Videojuego = ID_Videojuego;}
    public void setTitulo_Juego(String Titulo_Juego) {this.Titulo_Juego = Titulo_Juego.trim();}
    public void setAnio_Lanzamiento(Integer Anio_Lanzamiento) {this.Anio_Lanzamiento = Anio_Lanzamiento;}
    public void setCantidad_Jugadores(Integer Cantidad_Jugadores) {this.Cantidad_Jugadores = Cantidad_Jugadores;}
    public void setDescripcion(String Descripcion) {this.Descripcion = Descripcion.trim();}
    public void setID_Pais_Origen(Integer ID_Pais_Origen) {this.ID_Pais_Origen = ID_Pais_Origen;}
    public void setCantidad_Copias_Existencia(Integer Cantidad_Copias_Existencia) {this.Cantidad_Copias_Existencia = Cantidad_Copias_Existencia;}



}
