package async;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConsumidorAsy extends Thread {
    private final Tanke tanqueasync;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private final Queue<Integer> colaDatos;

    public ConsumidorAsy(Tanke panel2) {
        this.tanqueasync = panel2;

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
                this.agua = tanqueasync.getAgua();
                this.cantidad = 300 - tanqueasync.getAgua().size() * 20;

                if (!agua.isEmpty()) {
                    agua.remove(agua.size() -1);
                    tanqueasync.setAgua(agua);
                    tanqueasync.Etiqueta("Porcentaje:" + (((300 - cantidad) * 100) / 300) + "%");
                     // Guardar la cantidad consumida en la cola

                    // Mantener la cola con un tamaño limitado (por ejemplo, 50 elementos)
                    if (colaDatos.size() == 30) {
                        colaDatos.poll();
                    }
                    colaDatos.offer((((300 - cantidad) * 100) / 300));
                    tanqueasync.repaint();
                    Thread.sleep((long) ((Math.random() * 0.1) + 250));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
