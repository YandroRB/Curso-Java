import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Usuario> usuarios= new ArrayList<>(){{
            add(new Usuario("Santiago Byron","56422","Rodriguez Bone"));
            add(new Usuario("Jose Luis","45230","Oswaldo Hugo"));
            add(new Usuario("Pedro Luis","89512","Gomez Martinez"));
            add(new Usuario("Ana Carme","65321","Salazar Perez"));
            add(new Usuario("Laura Gabriela","21453","Lopez Gomez"));
        }};
        usuarios.add(new Usuario("Gabriela Maria","12124","Salazar Jaramillo"));
        for(Usuario usuario:usuarios){
            System.out.println(usuario);
        }
    }
}