package async;

import principal.Tanke;
import java.awt.geom.Rectangle2D;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ArrayList;

public class ProductorAsy extends Thread {
    private final Tanke tanqueasync;
    private final ArrayDeque<Integer> colaDatos; // Usamos ArrayDeque para mayor rendimiento
    private ArrayList<Rectangle2D> agua;
    private int cantidad;

    private static final int MAX_COLA_SIZE = 30; // Limitar la cola a 6 elementos

    public ProductorAsy(Tanke panel2) {
        this.tanqueasync = panel2;

        this.colaDatos = new ArrayDeque<>(MAX_COLA_SIZE); // Crear la cola con capacidad máxima
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.agua = tanqueasync.getAgua();
                this.cantidad = 300 - agua.size() * 20;

                // Calcular el porcentaje
                int porcentaje = ((300 - cantidad) * 100) / 300;

                // Solo agregar si la cola tiene menos de 6 elementos
                if (colaDatos.size() == MAX_COLA_SIZE) {
                    colaDatos.poll(); // Eliminar el elemento más antiguo si la cola está llena
                }
                colaDatos.offer(porcentaje); // Agregar el nuevo porcentaje a la cola

                if (cantidad >= 30) {
                    agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                    tanqueasync.setAgua(this.agua);
                    tanqueasync.Etiqueta("Porcentaje:" + porcentaje + "%");
                    tanqueasync.repaint(); // Repintar solo cuando realmente haya un cambio
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            // El hilo va a dormir un poco para evitar sobrecargar el procesador
            try {
                Thread.sleep(100); // Ajusta el tiempo de espera según sea necesario
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }
}
