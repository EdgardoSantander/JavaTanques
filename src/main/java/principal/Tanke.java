package principal;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Tanke extends JPanel {

    private ArrayList<Rectangle2D> agua;
    private JLabel etiquetaPorcentaje;

    public Tanke(){

        setLayout(new BorderLayout());

        // Inicializar lista y etiqueta
        agua = new ArrayList<>();
        etiquetaPorcentaje = new JLabel("0%", SwingConstants.CENTER); // Etiqueta centrada horizontalmente
        etiquetaPorcentaje.setBorder(BorderFactory.createEmptyBorder(265, 0, 0, 0)); // 20 píxeles de margen inferior

        // Agregar la etiqueta al sur del panel
        add(etiquetaPorcentaje);

    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.draw(new Rectangle2D.Double(80,40,50,280));


        for(int i=0 ;i<agua.size();i++){
            if(!agua.isEmpty()){
                g2.setColor(Color.CYAN);
            g2.fill( (Rectangle2D)agua.get(i));
            g2.setColor(Color.RED);
            g2.draw((Rectangle2D)agua.get(i));
            }

        }



    }

    public void setAgua(ArrayList<Rectangle2D> agua){
        this.agua = agua;
    }

    public ArrayList<Rectangle2D> getAgua(){
        return agua;
    }

    public void Etiqueta(String dato){

        this.etiquetaPorcentaje.setText(dato);
    }


}
