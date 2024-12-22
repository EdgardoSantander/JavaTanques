package mutex;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ProductorMutex extends Thread {
    private Tanke tanqueMutex;
    private int cantidad;
    private ArrayList<Rectangle2D> agua;
    private ReentrantLock mutex;

    // Cola para almacenar los datos, limitado a 6 elementos
    private Queue<Integer> colaCantidad = new LinkedBlockingQueue<>(30);

    public ProductorMutex(Tanke panel1, ReentrantLock mutex) {
        this.tanqueMutex = panel1;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            if (mutex.tryLock()) {
                try {
                    // Obtener los datos actuales del tanque
                    this.agua = tanqueMutex.getAgua();
                    this.cantidad = 300 - tanqueMutex.getAgua().size() * 20;

                    if (cantidad >= 30) {
                        // Añadir el nuevo dato a la cola y limitarla a 6 datos
                        if (colaCantidad.size() == 30) {
                            colaCantidad.poll();  // Eliminar el dato más antiguo
                        }
                        colaCantidad.add(((300 - cantidad) * 100) / 300); // Convertir cantidad a porcentaje y agregar

                        // Agregar la nueva parte al tanque
                        agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                        tanqueMutex.setAgua(this.agua);

                        // Actualizar la etiqueta con el porcentaje
                        tanqueMutex.Etiqueta("Porcentaje: " + (((300 - cantidad) * 100) / 300) + "%");

                        // Repintar el tanque para reflejar los cambios
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

    // Método para obtener la cola de datos, que se usará para la gráfica
    public Queue<Integer> getColaCantidad() {
        return colaCantidad;
    }
}
