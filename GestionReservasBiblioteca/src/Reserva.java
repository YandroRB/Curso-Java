import java.util.ArrayList;

public class Reserva {
    private String identifReserva;
    private Usuario usuario;
    private ReservarLibro[] libros = new ReservarLibro[5];

    public Reserva(String identifReserva, Usuario usuario, ReservarLibro[] libros) {
        this.identifReserva = identifReserva;
        this.usuario = usuario;
        this.libros = libros;
    }

    public String getIdentifReserva() {
        return identifReserva;
    }

    public ReservarLibro[] getLibros() {
        return libros;
    }

    public void setLibros(ReservarLibro[] libros) {
        this.libros = libros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
