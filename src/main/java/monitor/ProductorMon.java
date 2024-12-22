package monitor;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProductorMon extends Thread {
    private Tanke tankeMonitor;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private Queue<Integer> colaDatos;

    private static final int MAX_COLA_SIZE = 30; // Limitar la cola a 6 elementos

    public ProductorMon(Tanke panel4) {
        this.tankeMonitor = panel4;
        this.colaDatos = new LinkedBlockingQueue<>(MAX_COLA_SIZE);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (tankeMonitor) {
                try {
                    this.agua = tankeMonitor.getAgua();
                    this.cantidad = 300 - tankeMonitor.getAgua().size() * 20;

                    if (cantidad >= 0) {
                        // Añadir una nueva representación de agua
                        agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                        tankeMonitor.setAgua(this.agua);

                        // Calcular el porcentaje

                        sleep((long) ((Math.random() * 100) + 200));
                    }
                    int porcentaje = ((300 - cantidad) * 100) / 260;

                        // Actualizar la etiqueta con el porcentaje
                        tankeMonitor.Etiqueta("Porcentaje: " + porcentaje + "%");


                        // Agregar el porcentaje a la cola de datos
                        if (colaDatos.size() >= 30) { // Si la cola ya tiene el limite de datos
                            colaDatos.poll(); // Eliminar el dato más antiguo

                        }
                            colaDatos.offer(porcentaje); // Agregar el nuevo dato
                        // Simular un retardo en la producción
                         tankeMonitor.repaint();
                    // Esperar brevemente antes de la siguiente iteración
                    tankeMonitor.wait(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    tankeMonitor.notify();
                }
            }
        }
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        // Devolver una copia para evitar problemas de concurrencia
        synchronized (colaDatos) {
            return new LinkedBlockingQueue<>(colaDatos);
        }
    }
}
