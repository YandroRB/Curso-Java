import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Usuario> usuarios= new ArrayList<>(){{
            add(new Usuario("Santiago Byron","56422","Rodriguez Bone"));
            add(new Usuario("Jose Luis","45230","Oswaldo Hugo"));
            add(new Usuario("Pedro Luis","89512","Gomez Martinez"));
            add(new Usuario("Ana Carme","65321","Salazar Perez"));
            add(new Usuario("Laura Gabriela","21453","Lopez Gomez"));
        }};
        usuarios.add(new Usuario("Gabriela Maria","12124","Salazar Jaramillo","gmaria@gmail.com"));

        List<Libro> libros = new ArrayList<>(){{
           add(new Libro("56231","Cien años de soledad","Gabriel García Márquez","5/6/1967"));
           add(new Libro("56531","1984","George Orwell","8/6/1949"));
           add(new Libro("56924","El gran Gatsby","F. Scott Fitzgerald","10/4/1925"));
           add(new Libro("56842","Orgullo y prejuicio","Jane Austen","28/1/1813"));
           add(new Libro("56231","Los juegos del hambre","Suzanne Collins","14/9/2008"));
        }};

        for(Usuario usuario:usuarios){
            System.out.println(usuario);
        }
        for (Libro libro:libros){
            System.out.println(libro);
        }
    }
}