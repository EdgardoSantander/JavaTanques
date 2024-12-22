package principal;

import semaforo.ConsumidorSema;
import semaforo.ProductorSema;

import javax.swing.*;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class SemaforoController {
    private ProductorSema pSema;
    private ConsumidorSema cSema;
    private Semaphore semaforo;
    private Tanke panel;
    public SemaforoController(){
        panel = new Tanke();
        semaforo = new Semaphore(1);
        pSema = new ProductorSema(panel,this.semaforo);
        cSema = new ConsumidorSema(panel,this.semaforo);

    }

    public void iniciarProceso(){
        pSema.start();
        cSema.start();
    }

    public ProductorSema getpSema(){
        return pSema;
    }

    public ConsumidorSema getcSema(){
        return cSema;
    }

    public Queue<Integer> colaP(){
        return pSema.getColaDatos();
    }

    public Queue<Integer> colaC(){
        return cSema.getColaDatos();
    }

    public Tanke getPanel(){
        return panel;
    }
}
