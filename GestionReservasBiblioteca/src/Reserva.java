import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private String identifReserva;
    private Usuario usuario;
    private List<ReservarLibro> libros=new ArrayList<>();
    private Integer MAXLIBROS=5;


    public Reserva(String identifReserva, Usuario usuario) {
        this.identifReserva = identifReserva;
        this.usuario = usuario;
    }

    public String getIdentifReserva() {
        return identifReserva;
    }

    public void agregarLibro(ReservarLibro libro){
        if(this.libros.size()<this.MAXLIBROS){
            this.libros.add(libro);
        }
        else {
            System.out.println("No se puede agregar mas libros");
        }
    }

    public List<ReservarLibro> getLibros() {
        return libros;
    }

    public void setLibros(List<ReservarLibro> libros) {
        this.libros = libros;
    }

    public Integer getMAXLIBROS() {
        return MAXLIBROS;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "identifReserva='" + identifReserva + '\'' +
                ", usuario=" + usuario +
                ", libros=" + libros +
                ", MAXLIBROS=" + MAXLIBROS +
                '}';
    }
}
