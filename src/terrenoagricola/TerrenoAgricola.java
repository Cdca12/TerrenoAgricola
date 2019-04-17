package terrenoagricola;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import utils.Rutinas;

/**
 *
 * @author Carlos Contreras
 */
public class TerrenoAgricola extends JFrame implements ActionListener {

    private JPanel terreno;
    private Color[] tiposHierba = {Color.RED, Color.ORANGE, Color.GREEN};
    private Color[] hermanos = {Color.BLUE, Color.WHITE, Color.PINK};
    private final int hectareas;
    private JButton btnIniciar, btnReiniciar, btnSalir;

    public TerrenoAgricola() {
        hectareas = 100;
        initComponents();
        addListeners();

    }

    public void initComponents() {
        setTitle("Terreno Agrícola");
        setLayout(null);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        btnIniciar = new JButton("Iniciar simulación");
        btnIniciar.setBounds(430, 150, 140, 30);
        add(btnIniciar);

        btnReiniciar = new JButton("Reiniciar terreno");
        btnReiniciar.setBounds(btnIniciar.getX(), btnIniciar.getY() + btnIniciar.getHeight() + 5, 140, 30);
        add(btnReiniciar);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(btnIniciar.getX(), btnReiniciar.getY() + btnReiniciar.getHeight() + 5, 140, 30);
        add(btnSalir);

        terreno = new JPanel(new GridLayout(10, 10));
        terreno.setBounds(30, 30, 350, 300);
        generarTerreno();

        add(terreno);

        setVisible(true);
    }

    public void addListeners() {
        btnIniciar.addActionListener(this);
        btnReiniciar.addActionListener(this);
        btnSalir.addActionListener(this);
    }

    public void generarTerreno() {
        for (int i = 0; i < hectareas; terreno.add(new Hectarea(tiposHierba[Rutinas.nextInt(0, 2)])), i++);
    }

    public void asignarHectareas() {

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnIniciar) {
            asignarHectareas();
            return;
        }
        if (evt.getSource() == btnReiniciar) {
            terreno.removeAll();
            generarTerreno();
            terreno.updateUI();
            return;
        }
        if (evt.getSource() == btnSalir) {
            System.exit(NORMAL);
            return;
        }
    }
}
