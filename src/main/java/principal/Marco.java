package principal;

import barrera.ConsumidorBar;
import barrera.ProductorBar;
import cerradura.*;
import monitor.ConsumidorMon;
import monitor.ProductorMon;

import mpi.Datatype;
import mpi.MPI;
import mutex.*;
import async.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;
import semaforo.*;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Queue;       // Interfaz de la cola
import java.util.LinkedList;  // Implementación de la cola


public class Marco extends JFrame{
    private Tanke panel1,panel2,panel3,panel4,panel5,panel6;

    private MonitorController monitor;
    private DefaultCategoryDataset data;
    private Timer timer;

    private CerraduraController cerradura;
    private MutexController mutex;
    private AsyncController async;
    private BarreraController barrera;
    private SemaforoController semaforo;

    private JLabel etiquetaMutex,etiquetaAsync,etiquetaSemaforo,etiquetaMonitor,etiquetaBarrera,etiquetaCerradura;
    public Marco(int rank){
        setTitle("Proyecto programacion concurrente y paralela 2024");
        setSize(1450,850);
        setLayout(new BorderLayout());
        async = new AsyncController();
        mutex = new MutexController();
        semaforo = new SemaforoController();
        monitor = new MonitorController();
        barrera = new BarreraController();
        cerradura = new CerraduraController();
        if(rank == 0 ){
            iniciarMutex();
        }else if(rank == 1){
            iniciarAsync();
        }else if (rank == 2){
            iniciarSemaforo();
        }else if(rank == 3){
            iniciarMonitor();
        }else if(rank == 4){
            iniciarBarrera();
        }else if(rank == 5){
            iniciarCerradura();
            iniciarComponentes();
        } else if (rank == 6) {

        }


    }

    public void iniciarMutex(){

        mutex.iniciarProceso();
    }

    public void iniciarAsync(){

        async.iniciarProceso();
    }

    public void iniciarSemaforo(){

        semaforo.iniciarProceso();
    }

    public void iniciarMonitor(){

        monitor.iniciarProceso();
    }

    public void iniciarBarrera(){

        barrera.iniciarProceso();
    }

    public void iniciarCerradura(){

        cerradura.iniciarProceso();
    }





    private void iniciarComponentes(){

        JPanel contenedor = new JPanel(new GridLayout(2,3));
        contenedor.setPreferredSize(new Dimension(600, 400));
        JPanel contenedor2 = new JPanel(new GridLayout(6,1));
        contenedor2.setPreferredSize(new Dimension(650,400));





        // Inicializar Etiquetas
        etiquetaMutex = new JLabel("Mutex",SwingConstants.CENTER);
        etiquetaAsync = new JLabel("Async",SwingConstants.CENTER);
        etiquetaSemaforo = new JLabel("Semaforo",SwingConstants.CENTER);
        etiquetaMonitor = new JLabel("Monitor",SwingConstants.CENTER);
        etiquetaBarrera = new JLabel("Barrera",SwingConstants.CENTER);
        etiquetaCerradura = new JLabel("VC",SwingConstants.CENTER);

         JLabel etiquetaMutex2 = new JLabel("Mutex",SwingConstants.CENTER);
         etiquetaMutex2.setFont(new Font("Verdana",Font.BOLD,28));

         JLabel etiquetaAsync2 = new JLabel("Async",SwingConstants.CENTER);
         etiquetaAsync2.setFont(new Font("Verdana",Font.BOLD,28));

         JLabel etiquetaSema2 = new JLabel("Semaforo",SwingConstants.CENTER);
         etiquetaSema2.setFont(new Font("Verdana",Font.BOLD,28));

         JLabel etiquetaMonitor2 = new JLabel("Monitor",SwingConstants.CENTER);
         etiquetaMonitor2.setFont(new Font("Verdana",Font.BOLD,28));

         JLabel etiquetaBarrera2 = new JLabel("Barrera",SwingConstants.CENTER);
         etiquetaBarrera2.setFont(new Font("Verdana",Font.BOLD,28));

         JLabel etiquetaCondicion2 = new JLabel("Variable C",SwingConstants.CENTER);
         etiquetaCondicion2.setFont(new Font("Verdana",Font.BOLD,28));


        JPanel contenedor3 = new JPanel(new GridLayout(6,1));
        contenedor3.add(etiquetaMutex2);
        contenedor3.add(etiquetaAsync2);
        contenedor3.add(etiquetaSema2);
        contenedor3.add(etiquetaMonitor2);
        contenedor3.add(etiquetaBarrera2);
        contenedor3.add(etiquetaCondicion2);





        // agregar etiquetas
        mutex.getPanel().add(etiquetaMutex,BorderLayout.PAGE_START);
        async.getPanel().add(etiquetaAsync,BorderLayout.NORTH);
        semaforo.getPanel().add(etiquetaSemaforo,BorderLayout.NORTH);
        monitor.getPanel().add(etiquetaMonitor,BorderLayout.NORTH);
        barrera.getPanel().add(etiquetaBarrera,BorderLayout.NORTH);
        cerradura.getPanel().add(etiquetaCerradura,BorderLayout.NORTH);





        // agregar panel al frame
        contenedor.add(mutex.getPanel());
        contenedor.add(async.getPanel());
        contenedor.add(semaforo.getPanel());
        contenedor.add(monitor.getPanel());
        contenedor.add(barrera.getPanel());
        contenedor.add(cerradura.getPanel());

        super.add(contenedor,BorderLayout.LINE_START);
        super.add(contenedor2,BorderLayout.LINE_END);
        super.add(contenedor3,BorderLayout.CENTER);

        // MUTEX




        // ASYNC


        // SEMAFORO


        //MONITORES


        //BARRERA


        //CERRADURA
        /* candadoCerradura = new Cerradura();
        pCerradura = new ProductorCerr(panel6,candadoCerradura);
        cCerradura = new ConsumidorCerr(panel6,candadoCerradura);
        pCerradura.start();
        cCerradura.start();
        */




        //creacion de graficas




//////////////////////////////////////////////////////////////////////////

   // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart2 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset2                     // Datos vacíos, que se llenarán luego
);
NumberAxis numberY5 = (NumberAxis) chart2.getCategoryPlot().getRangeAxis();
        numberY5.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica2 = new ChartPanel(chart2);

// Añadir la gráfica al contenedor
contenedor2.add(panelGrafica2);

/////////////////////////////////////////////////////////////////////////////////



         // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart1 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset1                     // Datos vacíos, que se llenarán luego
);
NumberAxis numberY6 = (NumberAxis) chart1.getCategoryPlot().getRangeAxis();
        numberY6.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica1 = new ChartPanel(chart1);

// Añadir la gráfica al contenedor
contenedor2.add(panelGrafica1);
////////////////////////////////////////////////////////////////////////////////

        // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart3 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset3                     // Datos vacíos, que se llenarán luego
);
NumberAxis numberY4 = (NumberAxis) chart3.getCategoryPlot().getRangeAxis();
        numberY4.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica3 = new ChartPanel(chart3);
contenedor2.add(panelGrafica3);

//////////////////////////////////////////////////////////////////////////////


                // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset4 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart4 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset4                     // Datos vacíos, que se llenarán luego
);
NumberAxis numberY3 = (NumberAxis) chart4.getCategoryPlot().getRangeAxis();
        numberY3.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica4 = new ChartPanel(chart4);
contenedor2.add(panelGrafica4);

////////////////////////////////////////////////////////////////////


                // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset5 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart5 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset5                     // Datos vacíos, que se llenarán luego
);
NumberAxis numberY2 = (NumberAxis) chart5.getCategoryPlot().getRangeAxis();
        numberY2.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica5 = new ChartPanel(chart5);
contenedor2.add(panelGrafica5);


//////////////////////////////////////////////////////////////////////

                // Crear la gráfica y añadirla al contenedor
DefaultCategoryDataset dataset6 = new DefaultCategoryDataset();

// Crear la gráfica
JFreeChart chart6 = ChartFactory.createLineChart(
        "",    // Título del gráfico
        "Tiempo",               // Etiqueta del eje X
        "Cantidad",                  // Etiqueta del eje Y
        dataset6                     // Datos vacíos, que se llenarán luego
);
        NumberAxis numberY1 = (NumberAxis) chart6.getCategoryPlot().getRangeAxis();
        numberY1.setRange(0.0,100);

// Crear el panel con la gráfica
ChartPanel panelGrafica6 = new ChartPanel(chart6);
contenedor2.add(panelGrafica6);

contenedor2.revalidate();  // Asegurarse de que el contenedor se actualice
contenedor2.repaint();     // Repintar el contenedor para mostrar la gráfica





        timer = new Timer(2000,e -> {
    SwingUtilities.invokeLater(() -> {
        // Obtener las colas de datos desde los hilos productores
        Queue<Integer> colaDatosAsyncP = async.getColaP();  // Para la cola de Async
        Queue<Integer> colaDatosAsyncC = async.getColaC();
        Queue<Integer> colaDatosMutexP = mutex.EnviarColaP(); // Para la cola de Mutex
        Queue<Integer> colaDatosMutexC = mutex.EnviarColaC();
        Queue<Integer> colaDatosSemaP = semaforo.colaP();    // Para la cola de Semáforo
        Queue<Integer> colaDatosSemaC = semaforo.colaC();
        Queue<Integer> colaDatosBarP = barrera.ColaP();  // Para la cola de Barrera
        Queue<Integer> colaDatosBarC = barrera.ColaC();
        Queue<Integer> colaDatosMonP = monitor.getColaP(); // Para la cola de Monitores
        Queue<Integer> colaDatosMonC = monitor.getColaC();
        Queue<Integer> colaDatosCerrP = cerradura.EnviarColaP();    // Para la cola de VariableCondicion
        Queue<Integer> colaDatosCerrC = cerradura.EnviarColaC();

        // Actualizar cada gráfica usando los datos respectivos
        actualizarDataset(colaDatosAsyncP,colaDatosAsyncC, dataset1, chart1, "Productor Async","Consumidor Async");
        actualizarDataset(colaDatosMutexP,colaDatosMutexC, dataset2, chart2, "Productor Mutex","Consumidor Mutex");
        actualizarDataset(colaDatosSemaP,colaDatosSemaC, dataset3, chart3, "Productor Semaforo","Consumidor Semaforo");
        actualizarDataset(colaDatosBarP,colaDatosBarC, dataset5, chart5, "Productor Barrera","Consumidor Barrera");
        actualizarDataset(colaDatosMonP,colaDatosMonC, dataset4, chart4, "Productor Monitores", "Consumidor Monitores");
        actualizarDataset(colaDatosCerrP,colaDatosCerrC, dataset6, chart6, "Productor Cerradura", "Consumidor Cerradura");

        // Repintar el contenedor para reflejar los cambios
        contenedor2.repaint();
    });
});




    timer.start();




    }
    private void actualizarDataset(Queue<Integer> colaDatosP, Queue<Integer> colaDatosC,
                               DefaultCategoryDataset dataset, JFreeChart chart,
                               String serieNombreP, String serieNombreC) {
    dataset.clear(); // Limpiar datos previos del dataset

    // Agregar los datos del productor
    int index = 1;
    for (Integer cantidad : colaDatosP) {
        dataset.addValue(cantidad, "P", "" + index);
        index++;
    }

    // Reiniciar el índice y agregar los datos del consumidor
    int ndex = 1;
    for (Integer cantidad : colaDatosC) {
        dataset.addValue(cantidad, "C", "" + ndex);
        ndex++;
    }

    // Actualizar el dataset en la gráfica
    chart.getCategoryPlot().setDataset(dataset);
}


}
