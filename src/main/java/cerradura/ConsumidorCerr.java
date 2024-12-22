package cerradura;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ConsumidorCerr extends Thread {
    private final Tanke tankeCerradura;
    private final Cerradura candado;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private final Queue<Integer> colaDatos;

    public ConsumidorCerr(Tanke panel6, Cerradura candado) {
        this.tankeCerradura = panel6;
        this.candado = candado;
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
                if (candado.estadoCerradura()) {
                    candado.cerrarCerradura();
                    this.agua = tankeCerradura.getAgua();
                    this.cantidad = 300 - tankeCerradura.getAgua().size() * 20;

                    if (this.agua.size() >= 1) {
                        agua.remove(agua.size() - 1);
                        tankeCerradura.setAgua(agua);

                        // Calcular el porcentaje consumido
                        int porcentaje = ((300 - cantidad) * 100) / 300;

                        // Actualizar etiqueta
                        tankeCerradura.Etiqueta("Porcentaje:" + porcentaje + "%");

                        // Registrar en la cola el porcentaje consumido


                        // Limitar la cola a 6 elementos
                        if (colaDatos.size() >= 30) {
                            colaDatos.poll();
                        }
                        colaDatos.offer(porcentaje);
                        tankeCerradura.repaint();
                        Thread.sleep((long) ((Math.random() * 0.1) + 100));
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                candado.abrirCerradura();
            }
        }
    }
}
