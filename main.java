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
        return nombre + " (" + plataforma + ")";
    }

    public abstract double precioTotal();
}

class JuegoFisico extends Videojuego{
    public JuegoFisico(String nombre, double precio, String plataforma){
        super(nombre, precio, plataforma);
    }

    @Override
    public double precioTotal(){
        return precio + 25;
    }
}

class JuegoDigital extends Videojuego{
    public JuegoDigital(String nombre, double precio, String plataforma){
        super(nombre, precio, plataforma);
    }

    @Override
    public double precioTotal(){
        return precio * 0.90;
    }
}

public class Main{
    public static void main(String[] args){
        Videojuego juego = new JuegoFisico("Warcraft", 120, "PC");
        System.out.println(juego.descripcion() + ": " + juego.precioTotal() + " Bs");
    }
}