package cerradura;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class ProductorCerr extends Thread {
    private Tanke tankeCerradura;
    private Cerradura candado;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;

    // Cola para los datos
    private Queue<Integer> colaDatos;

    public ProductorCerr(Tanke panel6, Cerradura candado) {
        this.tankeCerradura = panel6;
        this.candado = candado;
        this.colaDatos = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (candado.estadoCerradura()) {
                    candado.cerrarCerradura();
                    this.agua = tankeCerradura.getAgua();
                    this.cantidad = 300 - tankeCerradura.getAgua().size() * 20;
                    if (agua.isEmpty()) {
                        agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                        tankeCerradura.setAgua(this.agua);
                        tankeCerradura.Etiqueta("Porcentaje:" + (((300 - cantidad) * 100) / 260) + "%");


                        // Agregar cantidad a la cola de datos

                            if (colaDatos.size() >= 30) { // Limitar el tamaño de la cola a 10 elementos
                                colaDatos.poll();
                            }
                            colaDatos.offer((((300 - cantidad) * 100) / 300)); // Se agrega la cantidad al porcentaje

                            tankeCerradura.repaint();
                        sleep((long) ((Math.random() * 0.1) + 100));
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                candado.abrirCerradura();
            }
        }
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        synchronized (colaDatos) {
            return new LinkedList<>(colaDatos); // Devuelve una copia para evitar problemas de concurrencia
        }
    }
}
