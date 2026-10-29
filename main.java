import java.util.ArrayList;
import java.util.List;
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
interface Inventario{
    void guardar(Videojuego videojuego);
    List<Videojuego> listar();
}

class InventarioMemoria implements Inventario{
    private List<Videojuego> videojuegos = new ArrayList<>();

    @Override
    public void guardar(Videojuego videojuego){
        videojuegos.add(videojuego);
    }

    @Override
    public List<Videojuego> listar(){
        return videojuegos;
    }
}
public class Main{
    public static void main(String[] args){
        Inventario inventario = new InventarioMemoria();
        inventario.guardar(new VideojuegoBuilder().nombre("Warcraft").precio(120).plataforma("PC").build());
        inventario.guardar(new VideojuegoBuilder().nombre("StarCraft").precio(150).plataforma("PC").digital().build());

        for(Videojuego v : inventario.listar()){
            System.out.println(v.descripcion() + ": " + v.precioTotal() + " Bs");
        }
    }
}