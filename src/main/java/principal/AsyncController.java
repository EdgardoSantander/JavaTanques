package principal;

import async.ConsumidorAsy;
import async.ProductorAsy;

import java.util.Queue;

public class AsyncController {
    private ProductorAsy pAsync;
    private ConsumidorAsy cAsync;
    private Tanke panel;
    public AsyncController(){
        this.panel = new Tanke();
        pAsync = new ProductorAsy(this.panel);
        cAsync = new ConsumidorAsy(this.panel);

    }

    public ProductorAsy getpAsync() {
        return pAsync;
    }

    public void iniciarProceso(){
        pAsync.start();
        cAsync.start();
    }

    public ConsumidorAsy getcAsync() {
        return cAsync;
    }


    public Queue<Integer> getColaP(){
        return pAsync.getColaDatos();
    }

    public Queue<Integer> getColaC(){
        return cAsync.getColaDatos();
    }

    public Tanke getPanel(){
        return this.panel;
    }


}
