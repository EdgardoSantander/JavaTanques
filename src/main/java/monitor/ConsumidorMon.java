package monitor;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConsumidorMon extends Thread {
    private final Tanke tankeMonitor;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private final Queue<Integer> colaDatos;

    public ConsumidorMon(Tanke panel4) {
        this.tankeMonitor = panel4;
        this.colaDatos = new LinkedList<>();
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (tankeMonitor) {
                try {
                    this.agua = tankeMonitor.getAgua();
                    this.cantidad = 300 - tankeMonitor.getAgua().size() * 20;

                    if (this.agua.size() >= 1) {
                        agua.remove(agua.size() - 1);
                        tankeMonitor.setAgua(agua);

                        // Calcular el porcentaje consumido


                        Thread.sleep((long) ((Math.random() * 0.1) + 200));
                    }
                    int porcentaje = ((300 - cantidad) * 100) / 300;

                        // Actualizar la etiqueta
                        tankeMonitor.Etiqueta("Porcentaje:" + porcentaje + "%");

                        // Registrar en la cola el porcentaje consumido


                        // Limitar la cola a 6 elementos
                        if (colaDatos.size() >= 30) {
                            colaDatos.poll();
                        }
                        colaDatos.offer(porcentaje);
                        // Repintar el tanque
                        tankeMonitor.repaint();
                    // Esperar por la señal del productor
                    tankeMonitor.wait(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    // Notificar al productor
                    tankeMonitor.notify();
                }
            }
        }
    }
}
