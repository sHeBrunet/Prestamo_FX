package models;

public class Estado_Prestamo {
    private Integer ID_Estado_Prestamo;
    private String Estado_Prestamo;

    public Estado_Prestamo(Integer iD_Estado_Prestamo, String estado_Prestamo) {
        setEstado_Prestamo(estado_Prestamo);
        setID_Estado_Prestamo(iD_Estado_Prestamo);
    }


    public Integer getID_Estado_Prestamo() {return ID_Estado_Prestamo;}
    public String getEstado_Prestamo() {return Estado_Prestamo;}


    public void setID_Estado_Prestamo(Integer iD_Estado_Prestamo) {ID_Estado_Prestamo = iD_Estado_Prestamo;}
    public void setEstado_Prestamo(String estado_Prestamo) {Estado_Prestamo = estado_Prestamo;}

}
