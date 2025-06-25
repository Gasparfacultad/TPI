package Models;

public class Campeonato {
    private String nombre;
    private Equipo equipo;
    private Partido partido;

    public Campeonato(){
    }

    public Campeonato(String nombre, Equipo equipo, Partido partido){
        this.nombre = nombre;
        this.equipo = equipo;
        this.partido = partido;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public Partido getPartido() {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", equipo='" + getEquipo() + "'" +
            ", partido='" + getPartido() + "'" +
            "}";
    }
  
}