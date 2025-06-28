package Models;

public class Jugador {
    private String nombre;
    private String nroCamiseta;
    private int goles;

    public Jugador() {
    }

    public Jugador(String nombre, String nroCamiseta, int goles) {
        this.nombre = nombre;
        this.nroCamiseta = nroCamiseta;
        this.goles = goles;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNroCamiseta() {
        return this.nroCamiseta;
    }

    public void setNroCamiseta(String nroCamiseta) {
        this.nroCamiseta = nroCamiseta;
    }

    public int getGoles() {
        return this.goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    // Método para añadir goles
    public void addGoles(int cantidad) {
        this.goles += cantidad;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", nroCamiseta='" + getNroCamiseta() + "'" +
            ", goles='" + getGoles() + "'" +
            "}";
    }
}