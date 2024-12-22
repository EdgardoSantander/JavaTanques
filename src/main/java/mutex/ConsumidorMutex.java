package mutex;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumidorMutex extends Thread {
    private final Tanke tanqueMutex;
    private final ReentrantLock mutex;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private final Queue<Integer> colaDatos;

    public ConsumidorMutex(Tanke panel1, ReentrantLock mutex) {
        this.tanqueMutex = panel1;
        this.mutex = mutex;
        this.colaDatos = new LinkedList<>();
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }

    @Override
    public void run() {
        while (true) {
            if (mutex.tryLock()) {
                try {
                    this.agua = tanqueMutex.getAgua();
                    this.cantidad = 300 - tanqueMutex.getAgua().size() * 20;

                    if (!agua.isEmpty()) {
                        agua.remove(agua.size() - 1);
                        tanqueMutex.setAgua(agua);
                        tanqueMutex.Etiqueta("Porcentaje:" + (((300 - cantidad) * 100) / 300) + "%");

                        // Registrar en la cola la cantidad consumida


                        // Limitar la cola a 6 elementos
                        if (colaDatos.size() == 30) {
                            colaDatos.poll();
                        }
                        colaDatos.offer((((300 - cantidad) * 100) / 300));
                        tanqueMutex.repaint();
                        Thread.sleep((long) ((Math.random() * 0.1) + 500));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    mutex.unlock();
                }
            }
        }
    }
}
