import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Libro {
    private String identifLibro;
    private String titulo;
    private String autor;
    private String genero="Desconocido";
    private Date fechaPublicacion ;
    private String resumen ="Desconocido";
    private int numeroCopias=1;


    public Libro (String identifLibro, String titulo, String autor, String fechaPublicacion){
        this.identifLibro=identifLibro;
        this.titulo=titulo;
        this.autor=autor;
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            this.fechaPublicacion = format.parse(fechaPublicacion);
        }
        catch (ParseException e){
            System.err.println(e.getMessage());
        }

    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }

    public Libro(String identifLibro, String titulo, String genero, String autor, String resumen, String fechaPublicacion) {
        this.identifLibro = identifLibro;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.resumen = resumen;
        try{
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            this.fechaPublicacion = format.parse(fechaPublicacion);
        }
        catch (ParseException e){
            System.err.println(e.getMessage());
        }
    }

    public String getIdentifLibro(){
        return this.identifLibro;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public  String getAutor(){
        return this.autor;
    }
    public String getGenero() {
        return genero;
    }
    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }
    public String getResumen(){
        return resumen;
    }
    public void  setTitulo(String titulo){
        this.titulo=titulo;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }



    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", Locale.of("es","ES"));
        return "{\n" +
                "identifLibro='" + identifLibro + ",\n" +
                "titulo='" + titulo + ",\n" +
                "autor='" + autor + ",\n" +
                "fechaPublicacion=" + format.format(fechaPublicacion) + ",\n"+
                "resumen='" + resumen + ",\n" +
                '}';
    }
}
