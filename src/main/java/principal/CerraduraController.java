package principal;

import cerradura.Cerradura;
import cerradura.ConsumidorCerr;
import cerradura.ProductorCerr;

import javax.swing.*;
import java.util.Queue;

public class CerraduraController {
        private  Cerradura candadoCerradura;
        private ProductorCerr pCerradura;
        private ConsumidorCerr cCerradura;
        private Tanke panel;

    public CerraduraController(){
        this.panel = new Tanke();
        candadoCerradura = new Cerradura();
        pCerradura = new ProductorCerr(this.panel,candadoCerradura);
        cCerradura = new ConsumidorCerr(this.panel,candadoCerradura);

    }

    public void iniciarProceso(){
        pCerradura.start();
        cCerradura.start();
    }

    public ProductorCerr GetProductor(){
        return pCerradura;
    }

    public ConsumidorCerr GetConsumidor(){
        return cCerradura;
    }

    public Queue<Integer> EnviarColaP(){
        return pCerradura.getColaDatos();
    }

    public Queue<Integer> EnviarColaC(){
        return cCerradura.getColaDatos();
    }

    public Tanke getPanel(){
        return this.panel;
    }
}
