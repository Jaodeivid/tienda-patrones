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

class VideojuegoBuilder{
    private String nombre = "Sin nombre";
    private double precio = 0;
    private String plataforma = "PC";
    private boolean digital = false;

    public VideojuegoBuilder nombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public VideojuegoBuilder precio(double precio){
        this.precio = precio;
        return this;
    }

    public VideojuegoBuilder plataforma(String plataforma){
        this.plataforma = plataforma;
        return this;
    }

    public VideojuegoBuilder digital(){
        this.digital = true;
        return this;
    }

    public Videojuego build(){
        if(digital){
            return new JuegoDigital(nombre, precio, plataforma);
        }
        return new JuegoFisico(nombre, precio, plataforma);
    }
}
interface MetodoPago{
    void pagar(double monto);
}

class Efectivo implements MetodoPago{
    @Override
    public void pagar(double monto){
        System.out.println("Pago en efectivo: " + monto + " Bs");
    }
}

class Tarjeta implements MetodoPago{
    @Override
    public void pagar(double monto){
        System.out.println("Pago con tarjeta: " + monto + " Bs");
    }
}

public class Main{
    public static void main(String[] args){
        Videojuego warcraft = new VideojuegoBuilder().nombre("Warcraft").precio(120).plataforma("PC").build();
        MetodoPago metodo = new Tarjeta();
        System.out.println(warcraft.descripcion());
        metodo.pagar(warcraft.precioTotal());
    }
}