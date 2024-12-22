package principal;

import barrera.ConsumidorBar;
import barrera.ProductorBar;

import java.util.Queue;
import java.util.concurrent.CyclicBarrier;

public class BarreraController {
    private ProductorBar pBarrera;
    private ConsumidorBar cBarrera;
    private CyclicBarrier barrera;
    private Tanke panel;

    public BarreraController(){
        this.panel = new Tanke();
        barrera = new CyclicBarrier(1);
        pBarrera = new ProductorBar(this.panel,barrera);
        cBarrera = new ConsumidorBar(this.panel, barrera);

    }
    public void iniciarProceso(){
        pBarrera.start();
        cBarrera.start();
    }

    public ProductorBar getpBarrera(){
        return pBarrera;
    }

    public ConsumidorBar getcBarrera(){
        return cBarrera;
    }

    public Queue<Integer> ColaP(){
        return pBarrera.getColaDatos();
    }

    public Queue<Integer> ColaC(){
        return cBarrera.getColaDatos();
    }

    public Tanke getPanel(){
        return this.panel;
    }


}
