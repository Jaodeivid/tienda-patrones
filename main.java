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

    // cada tipo de juego calcula su precio final a su manera
    public abstract double precioTotal();
}

// al juego fisico se le suman 25 Bs de envio
class JuegoFisico extends Videojuego{
    public JuegoFisico(String nombre, double precio, String plataforma){
        super(nombre, precio, plataforma);
    }

    @Override
    public double precioTotal(){
        return precio + 25;
    }
}

// al juego digital se le hace 10% de descuento
class JuegoDigital extends Videojuego{
    public JuegoDigital(String nombre, double precio, String plataforma){
        super(nombre, precio, plataforma);
    }

    @Override
    public double precioTotal(){
        return precio * 0.90;
    }
}

// PATRON CREACIONAL: BUILDER
// lo uso para crear los videojuegos paso a paso, asi no hago un constructor largo con todos los datos
// cada metodo guarda un dato y devuelve this, por eso se pueden encadenar
// build() decide si crea un juego fisico o digital
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

// PATRON DE COMPORTAMIENTO: STRATEGY
// MetodoPago es la estrategia, Efectivo y Tarjeta son las formas de pagar, y Tienda es la que las usa
// asi la tienda cobra de distintas formas sin usar if ni else
// si quiero otro metodo de pago solo creo otra clase y no toco la tienda
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

// PATRON ESTRUCTURAL: DAO
// esta interfaz maneja todo el acceso a los datos del inventario, guardar y listar videojuegos
// la tienda solo conoce la interfaz, no sabe donde se guardan los juegos
// si despues quiero guardarlos en un archivo o base de datos, solo hago otra clase que implemente Inventario
interface Inventario{
    void guardar(Videojuego videojuego);
    List<Videojuego> listar();
}

// aqui los juegos se guardan en una lista en memoria
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

// la tienda solo se encarga de mostrar el catalogo y vender
class Tienda{
    private Inventario inventario;

    public Tienda(Inventario inventario){
        this.inventario = inventario;
    }

    public void mostrarCatalogo(){
        for(Videojuego v : inventario.listar()){
            System.out.println("- " + v.descripcion() + ": " + v.precioTotal() + " Bs");
        }
    }

    public void vender(Videojuego videojuego, MetodoPago metodoPago){
        System.out.println("Venta de " + videojuego.descripcion());
        metodoPago.pagar(videojuego.precioTotal());
    }
}

public class Main{
    public static void main(String[] args){
        // BUILDER y DAO: creo los juegos con el builder y los guardo en el inventario
        Inventario inventario = new InventarioMemoria();
        inventario.guardar(new VideojuegoBuilder().nombre("Warcraft").precio(120).plataforma("PC").build());
        inventario.guardar(new VideojuegoBuilder().nombre("StarCraft").precio(150).plataforma("PC").digital().build());

        // la tienda usa el inventario sin saber donde estan guardados los juegos
        Tienda tienda = new Tienda(inventario);
        tienda.mostrarCatalogo();
        System.out.println("");

        // STRATEGY: cada venta se paga con un metodo distinto
        tienda.vender(inventario.listar().get(0), new Tarjeta());
        System.out.println("");
        tienda.vender(inventario.listar().get(1), new Efectivo());
    }
}