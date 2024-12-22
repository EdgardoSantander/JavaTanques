package semaforo;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ConsumidorSema extends Thread {

    private final Semaphore semaforo;
    private final Tanke tankeSemaforo;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private final Queue<Integer> colaDatos;

    public ConsumidorSema(Tanke panel3, Semaphore semaforo) {
        this.semaforo = semaforo;
        this.tankeSemaforo = panel3;
        this.colaDatos = new LinkedList<>();
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaforo.acquire();
                this.agua = tankeSemaforo.getAgua();
                this.cantidad = 300 - tankeSemaforo.getAgua().size() * 20;

                if (!agua.isEmpty()) {
                    agua.remove(agua.size() - 1);
                    tankeSemaforo.setAgua(agua);

                    // Calcular el porcentaje consumido
                    int porcentaje = ((300 - cantidad) * 100) / 300;

                    // Actualizar etiqueta
                    tankeSemaforo.Etiqueta("Porcentaje:" + porcentaje + "%");



                    // Limitar la cola a 6 elementos
                    if (colaDatos.size() == 30) {
                        colaDatos.poll();
                    }
                    // Registrar en la cola el porcentaje consumido
                    colaDatos.offer(porcentaje);

                    tankeSemaforo.repaint();
                    Thread.sleep((long) ((Math.random() * 0.1) + 500));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }
}
