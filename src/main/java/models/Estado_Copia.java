package models;

public class Estado_Copia {
    private Integer ID_Estado_Copia;
    private String Tipo_Estado;

    public Estado_Copia(Integer iD_Estado_Copia, String tipo_Estado) {
        setID_Estado_Copia(iD_Estado_Copia);
        setTipo_Estado(tipo_Estado);
    }

    public Integer getID_Estado_Copia() {return ID_Estado_Copia;}
    public String getTipo_Estado() {return Tipo_Estado;}


    public void setID_Estado_Copia(Integer iD_Estado_Copia) {ID_Estado_Copia = iD_Estado_Copia;}
    public void setTipo_Estado(String tipo_Estado) {Tipo_Estado = tipo_Estado;}

}
