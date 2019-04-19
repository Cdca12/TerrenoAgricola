package terrenoagricola;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import utils.Semaforo;

/**
 *
 * @author Carlos Contreras
 */
public class Hectarea extends JPanel {
    
    public Semaforo semaforo;
    public boolean estaAsignada;

    public Hectarea(Color tipoHectarea) {
        this.semaforo = new Semaforo(1); // Binario
        setOpaque(true);
        estaAsignada = false;
        setBackground(tipoHectarea);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
