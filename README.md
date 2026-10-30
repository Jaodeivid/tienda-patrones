# Tienda de Videojuegos
Proyecto de la Actividad 10 (Principios y Patrones del Desarrollo de Software). Es un módulo de ventas de una tienda de videojuegos que funciona por terminal, hecho en Java.
La tienda vende juegos físicos, que llevan 25 Bs de envío, y juegos digitales, que tienen 10% de descuento. Los juegos se guardan en un inventario, se muestra el catálogo con sus precios y después se hace la venta pagando en efectivo o con tarjeta.

## Patrones de diseño usados
**Builder (creacional):** en `VideojuegoBuilder`. Sirve para crear los videojuegos paso a paso, sin un constructor largo.
**DAO (estructural):** en `Inventario` e `InventarioMemoria`. Separa el guardado de los juegos del resto del programa, así la tienda no sabe dónde se guardan.
**Strategy (comportamiento):** en `MetodoPago`, `Efectivo` y `Tarjeta`. Permite que la tienda cobre de distintas formas sin usar if ni else.

## Cómo ejecutarlo
Se necesita tener instalado el JDK 17 o superior. Dentro de la carpeta del proyecto:
```
javac Main.java
java Main
```
## Qué debería salir
```
- Warcraft (PC): 145.0 Bs
- StarCraft (PC): 135.0 Bs

Venta de Warcraft (PC)
Pago con tarjeta: 145.0 Bs

Venta de StarCraft (PC)
Pago en efectivo: 135.0 Bs
```