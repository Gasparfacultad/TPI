package Models;

public class Arbitro {
    private String nombre;
    private int partidosDirigidos;

    public Arbitro(){
    }


    public Arbitro(String nombre, int partidosDirigidos) {
        this.nombre = nombre;
        this.partidosDirigidos = partidosDirigidos;
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