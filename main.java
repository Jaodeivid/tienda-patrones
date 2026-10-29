abstract class Videojuego{
    protected String nombre;
    protected double precio;
    protected String plataforma;

    public Videojuego(String nombre, double precio, String plataforma){
        this.nombre = nombre;
        this.precio = precio;
        this.plataforma = plataforma;
    }

    public String descripcion(){
        return nombre + " [" + plataforma + "]";
    }

    public abstract double precioTotal();
}

public class Main{
    public static void main(String[] args){
        System.out.println("Tienda de videojuegos");
    }
}