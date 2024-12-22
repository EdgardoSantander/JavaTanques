package cerradura;

public class Cerradura {
    private boolean candado = true;

    public void abrirCerradura(){
        this.candado = true;
    }

    public void cerrarCerradura(){
        this.candado = false;
    }

    public boolean estadoCerradura(){
        return this.candado;
    }
}
