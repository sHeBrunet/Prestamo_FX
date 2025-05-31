package models;

public class Area_Interes {
        private Integer ID_Area_Interes;
        private String Tipo_Area;

        public Area_Interes(Integer iD_Area_Interes, String tipo_Area) {
            setID_Area_Interes(iD_Area_Interes);
            setTipo_Area(tipo_Area);
        }

        // ---------------------- Getters ----------------------
        public Integer getID_Area_Interes() {return ID_Area_Interes;}
        public String getTipo_Area() {return Tipo_Area;}

        // ---------------------- Setters  ----------------------
        public void setID_Area_Interes(Integer ID_Area_Interes) {
            if (ID_Area_Interes == null || ID_Area_Interes < 1)
                throw new IllegalArgumentException("El ID del �rea de inter�s debe ser un n�mero positivo.");
            this.ID_Area_Interes = ID_Area_Interes;
        }

        public void setTipo_Area(String Tipo_Area) {
            if (Tipo_Area == null || Tipo_Area.trim().isEmpty())
                throw new IllegalArgumentException("El tipo de �rea no puede estar vac�o o ser nulo.");

            if (Tipo_Area.trim().length() > 50)
                throw new IllegalArgumentException("El tipo de �rea no puede exceder los 50 caracteres.");

            this.Tipo_Area = Tipo_Area.trim();
        }
}
