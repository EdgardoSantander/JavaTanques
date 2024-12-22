package barrera;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class ConsumidorBar extends Thread {
    private  Tanke tankeBarrera;
    private ArrayList<Rectangle2D> agua;
    private int cantidad;
    private  CyclicBarrier tranca;
    private  Queue<Integer> colaDatos;

    public ConsumidorBar(Tanke panel5, CyclicBarrier barrera) {
        this.tankeBarrera = panel5;
        this.tranca = barrera;
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
                tranca.await();
                this.agua = tankeBarrera.getAgua();
                this.cantidad = 300 - tankeBarrera.getAgua().size() * 20;
                    int porcentaje = ((300 - cantidad) * 100) / 300;
                if (!agua.isEmpty()) {
                    agua.remove(agua.size() -1);
                    tankeBarrera.setAgua(agua);
                }
                    // Calcular el porcentaje consumido


                    // Actualizar etiqueta
                    tankeBarrera.Etiqueta("Porcentaje:" + porcentaje + "%");



                    // Limitar la cola a 6 elementos
                    if (colaDatos.size() >= 30) {
                        colaDatos.poll();
                    }
                    // Registrar en la cola el porcentaje consumido
                    colaDatos.offer(porcentaje);
                    tankeBarrera.repaint();

                    Thread.sleep((long) ((Math.random() * 0.1) + 150));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
