package terrenoagricola;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import utils.Rutinas;
import utils.Semaforo;

/**
 *
 * @author Carlos Contreras
 */
public class Hermano extends Thread {

    public Hectarea[] hectareasTerreno;
    public Color colorHermano;
    public int hectareasAsignadas;

    public Hermano(Hectarea[] hectareasTerreno, Color colorHermano) {
        this.hectareasTerreno = hectareasTerreno;
        this.colorHermano = colorHermano;
        this.hectareasAsignadas = 0;
    }

    @Override
    public void run() {
        Hectarea hectareaAsignada;
        while (hectareasDisponibles()) {
            hectareaAsignada = hectareasTerreno[Rutinas.nextInt(0, 99)];
            // Sección crítica
            hectareaAsignada.semaforo.Espera();
            if (hectareaAsignada.estaAsignada) { // Si está ocupada se vuelve a buscar otra hectárea
                hectareaAsignada.semaforo.Libera();
                continue;
            }
            hectareaAsignada.estaAsignada = true; // Lo marca como assignada
            hectareaAsignada.semaforo.Libera(); // Libera primero antes de hacer sus operaciones
            // Operaciones
            hectareaAsignada.setBackground(colorHermano);
//            dormir(10);
//            hectareaAsignada.updateUI();
            hectareasAsignadas++;
        }
    }

    public boolean hectareasDisponibles() {
        for (Hectarea hectarea : hectareasTerreno) {
            if (!hectarea.estaAsignada) {
                return true;
            }
        }
        return false;
    }

    public void dormir(int milisegundos) {
        try {
            sleep(milisegundos);
        } catch (InterruptedException ex) {
        }
    }
}
