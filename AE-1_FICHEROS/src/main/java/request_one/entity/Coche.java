package request_one.entity;

import java.io.Serializable;

public class Coche implements Serializable {

    // Variables

    private static final long serialVersionUID = 884331241851833410L;
    private static int CONTADOR_ID = 0;

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;

    // Constructores
    public Coche() {
        super();
    }

    public Coche(String matricula, String marca, String modelo, String color){
        super();
        id = ++CONTADOR_ID;
        matricula = this.getMatricula();
        marca = this.getMarca();
        modelo = this.getModelo();
        color = this.getColor();
    }

    public Coche(int id, String matricula, String marca, String modelo, String color){
        super();
        id = this.getId();
        matricula = this.getMatricula();
        marca = this.getMarca();
        modelo = this.getModelo();
        color = this.getColor();
    }

    //toString
    
    @Override
    public String toString() {
        return "Coche[" +
                "id=" + id +
                "--- matricula='" + matricula + '\'' +
                "--- marca='" + marca + '\'' +
                "--- modelo='" + modelo + '\'' +
                "--- color='" + color + '\'' +
                "]\n";
    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContadorId() {
        return CONTADOR_ID;
    }

    public static void setContadorId(int contadorId) {
        CONTADOR_ID = contadorId;
    }
}
