package principal;

import monitor.ConsumidorMon;
import monitor.ProductorMon;

import java.util.Queue;

public class MonitorController {
    private ProductorMon pMonitor;
    private ConsumidorMon cMonitor;
    private Tanke panel;

    public MonitorController(){
        this.panel = new Tanke();
        pMonitor = new ProductorMon(this.panel);
        cMonitor = new ConsumidorMon(this.panel);

    }

    public void iniciarProceso(){
        pMonitor.start();
        cMonitor.start();
    }
    public ProductorMon getpMonitor(){
        return pMonitor;
    }

    public ConsumidorMon getcMonitor(){
        return cMonitor;
    }

    public Queue<Integer> getColaP(){
        return pMonitor.getColaDatos();
    }

    public Queue<Integer> getColaC(){
        return cMonitor.getColaDatos();
    }
    public Tanke getPanel(){
        return this.panel;
    }
}
