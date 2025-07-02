package Models;

public class Arbitro {
    private int id;
    private String nombre;
    private int partidosDirigidos;

    public Arbitro(){
    }

    public Arbitro(int id, String nombre, int partidosDirigidos) {
        this.id = id;
        this.nombre = nombre;
        this.partidosDirigidos = partidosDirigidos;
    }

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidosDirigidos() {
        return this.partidosDirigidos;
    }

    public void setPartidosDirigidos(int partidosDirigidos) {
        this.partidosDirigidos = partidosDirigidos;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", partidosDirigidos='" + getPartidosDirigidos() + "'" +
            "}";
    }
}