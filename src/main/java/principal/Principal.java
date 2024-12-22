package principal;

import mpi.MPI;
import javax.swing.*;

public class Principal {
    static Marco marco;  // La ventana se crea solo una vez


    public static void main(String[] args) {


        // Inicializamos MPI
        MPI.Init(args);

        int numeroP = MPI.COMM_WORLD.Rank();
        marco = new Marco(numeroP);
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Finalizar el entorno de MPI
        MPI.Finalize();
    }
}
