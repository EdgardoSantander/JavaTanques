package semaforo;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class ProductorSema extends Thread {
    private Semaphore semaforo;
    private Tanke tankeSemaforo;
    private int cantidad;
    private ArrayList<Rectangle2D> agua;
    private Queue<Integer> colaDatos;

    public ProductorSema(Tanke panel3, Semaphore semaforo) {
        this.semaforo = semaforo;
        this.tankeSemaforo = panel3;
        this.colaDatos = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                semaforo.acquire();
                    this.agua = tankeSemaforo.getAgua();
                    this.cantidad = 300 - tankeSemaforo.getAgua().size() * 20;
                    if (cantidad >= 30) {
                        agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                        tankeSemaforo.setAgua(this.agua);
                        tankeSemaforo.Etiqueta("Porcentaje: " + (((300 - cantidad) * 100) / 260) + "%");
                        tankeSemaforo.repaint();



                        // Mantener un máximo de 6 elementos en la cola
                        if (colaDatos.size() == 30) {
                            colaDatos.poll(); // Remover el elemento más antiguo
                        }
                        // Agregar el dato actual a la cola
                        colaDatos.offer(((300 - cantidad) * 100) / 260);
                        sleep((long) ((Math.random() * 0.1) + 500));
                    }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaforo.release();
            }
        }
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }
}
