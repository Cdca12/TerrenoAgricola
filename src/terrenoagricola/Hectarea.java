package terrenoagricola;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author Carlos Contreras
 */
public class Hectarea extends JPanel {

    public boolean asignado;

    public Hectarea(Color tipo) {
        setOpaque(true);
        asignado = false;
        setBackground(tipo);
        setBorder(BorderFactory.createRaisedBevelBorder());
    }
}
