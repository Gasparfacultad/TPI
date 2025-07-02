package Models;
import java.util.ArrayList;

public class Campeonato {
    private String nombre;
    private ArrayList<Equipo> equipos; 
    private ArrayList<Partido> partidosJugados; 

    public Campeonato(){
        this.equipos = new ArrayList<>();
        this.partidosJugados = new ArrayList<>();
    }

    public Campeonato(String nombre) {
        this.nombre = nombre;
        this.equipos = new ArrayList<>();
        this.partidosJugados = new ArrayList<>();
    }

    public Campeonato(String nombre, ArrayList<Equipo> equipos, ArrayList<Partido> partidosJugados) {
        this.nombre = nombre;
        this.equipos = equipos;
        this.partidosJugados = partidosJugados;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Equipo> getEquipos() {
        return this.equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidosJugados() {
        return this.partidosJugados;
    }

    public void setPartidosJugados(ArrayList<Partido> partidosJugados) {
        this.partidosJugados = partidosJugados;
    }

    public void addEquipo(Equipo equipo) {
        if (equipo != null && !this.equipos.contains(equipo)) {
            this.equipos.add(equipo);
        }
    }

    public void addPartido(Partido partido) {
        if (partido != null && !this.partidosJugados.contains(partido)) {
            this.partidosJugados.add(partido);
        }
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", equipos=" + getEquipos().size() + " equipos" +
            ", partidosJugados=" + getPartidosJugados().size() + " partidos" +
            "}";
    }
}