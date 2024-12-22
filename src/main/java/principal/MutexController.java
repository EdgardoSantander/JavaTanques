package principal;

import mutex.ConsumidorMutex;
import mutex.ProductorMutex;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class MutexController {
     private ProductorMutex pMutex;
     private ConsumidorMutex cMutex;
     private ReentrantLock candadoMutex;
     private Tanke panel;

    public MutexController(){
        this.panel = new Tanke();
        candadoMutex = new ReentrantLock();
        pMutex = new ProductorMutex(this.panel,candadoMutex);
        cMutex = new ConsumidorMutex(this.panel,candadoMutex);

    }

    public void iniciarProceso(){
        pMutex.start();
        cMutex.start();
    }
    public ProductorMutex GetProductor(){
        return pMutex;
    }

    public ConsumidorMutex GetConsumidor(){
        return cMutex;
    }

    public Queue<Integer> EnviarColaP(){
        return pMutex.getColaCantidad();
    }

    public Queue<Integer> EnviarColaC(){
        return cMutex.getColaDatos();
    }
    public Tanke getPanel(){
        return this.panel;
    }
    public void actualizarPanel() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Actualiza el panel de manera segura en el hilo de la interfaz
                panel.repaint(); // Puedes hacer otras actualizaciones de la interfaz aquí
            }
        });
    }
}
