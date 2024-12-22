package barrera;

import principal.Tanke;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class ProductorBar extends Thread {

    private Tanke tankeBarrera;
    private int cantidad;
    private ArrayList<Rectangle2D> agua;
    private CyclicBarrier tranca;
    private Queue<Integer> colaDatos;

    public ProductorBar(Tanke panel5, CyclicBarrier barrera) {
        this.tankeBarrera = panel5;
        this.tranca = barrera;
        this.colaDatos = new LinkedList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                tranca.await(); // Sincronizar con la barrera

                this.agua = tankeBarrera.getAgua();
                this.cantidad = 300 - tankeBarrera.getAgua().size() * 20;
                if (agua.isEmpty()) {
                    agua.add(new Rectangle2D.Double(80, cantidad, 50, 20));
                    tankeBarrera.setAgua(this.agua);
                    }
                    tankeBarrera.Etiqueta("Porcentaje: " + (((300 - cantidad) * 100) / 300) + "%");


                    // Agregar el dato actual a la cola


                    // Mantener un máximo de 6 elementos en la cola
                    if (colaDatos.size() >= 30) {
                        colaDatos.poll(); // Remover el elemento más antiguo
                    }
                    colaDatos.offer(((300 - cantidad) * 100) / 260);

                    tankeBarrera.repaint();
                    Thread.sleep((long) ((Math.random() * 0.1) + 80));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener la cola de datos
    public Queue<Integer> getColaDatos() {
        return colaDatos;
    }
}
