package models;

public class Tipo_Sancion {
    private Integer ID_Tipo_Sancion;
    private String Tipo_Sancion;

    public Tipo_Sancion(Integer iD_Tipo_Sancion, String tipo_Sancion) {
        setID_Tipo_Sancion(iD_Tipo_Sancion);
        setTipo_Sancion(tipo_Sancion);
    }

    public Integer getID_Tipo_Sancion() {return ID_Tipo_Sancion;}
    public String getTipo_Sancion() {return Tipo_Sancion;}


    public void setID_Tipo_Sancion(Integer iD_Tipo_Sancion) {ID_Tipo_Sancion = iD_Tipo_Sancion;}
    public void setTipo_Sancion(String tipo_Sancion) {Tipo_Sancion = tipo_Sancion;}

}
