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
    private Color[] colorHermanos = {Color.BLUE, Color.WHITE, Color.PINK};
    private Hermano hermanoA, hermanoB, hermanoC;
    private Hectarea[] hectareasTerreno;
    private final int numeroHectareas;
    private JButton btnIniciar, btnReiniciar, btnSalir;
    private JDialog modalResultados;

    public TerrenoAgricola() {
        numeroHectareas = 100;
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
        hectareasTerreno = new Hectarea[numeroHectareas];
        for (int i = 0; i < numeroHectareas; i++) {
            hectareasTerreno[i] = new Hectarea(tiposHierba[Rutinas.nextInt(0, 2)]);
            terreno.add(hectareasTerreno[i]);
        }
    }

    public void generarHermanos() {
        hermanoA = new Hermano(hectareasTerreno, colorHermanos[0]);
        hermanoB = new Hermano(hectareasTerreno, colorHermanos[1]);
        hermanoC = new Hermano(hectareasTerreno, colorHermanos[2]);
    }

    public void asignarHectareas() {
        hermanoA.start();
        hermanoB.start();
        hermanoC.start();

        while (hermanoA.isAlive() || hermanoB.isAlive() || hermanoC.isAlive());
    }
    
    public void mostrarResultados() {
        modalResultados = new JDialog();
        modalResultados.setTitle("Resultados");
        modalResultados.setLayout(null);
        modalResultados.setSize(250, 250);
        modalResultados.setLocationRelativeTo(null);
        modalResultados.setModal(true);
        JLabel lbHermanoA = new JLabel("Hermano A");
        lbHermanoA.setBounds(20, 20, 100, 20);
        modalResultados.add(lbHermanoA);
        JLabel lbHermanoB = new JLabel("Hermano B");
        lbHermanoB.setBounds(lbHermanoA.getX(), lbHermanoA.getY() + 30, 100, 20);
        modalResultados.add(lbHermanoB);
        JLabel lbHermanoC = new JLabel("Hermano C");
        lbHermanoC.setBounds(lbHermanoB.getX(), lbHermanoB.getY() + 30, 100, 20);
        modalResultados.add(lbHermanoC);
        
        JLabel lbHectareasHermanoA = new JLabel(hermanoA.hectareasAsignadas + " hectáreas");
        lbHectareasHermanoA.setBounds(lbHermanoA.getX() + lbHermanoA.getWidth() + 5 , lbHermanoA.getY(), 100, 20);
        modalResultados.add(lbHectareasHermanoA);
        JLabel lbHectareasHermanoB = new JLabel(hermanoB.hectareasAsignadas + " hectáreas");
        lbHectareasHermanoB.setBounds(lbHermanoB.getX() + lbHermanoB.getWidth() + 5, lbHermanoB.getY(), 100, 20);
        modalResultados.add(lbHectareasHermanoB);
        JLabel lbHectareasHermanoC = new JLabel(hermanoC.hectareasAsignadas + " hectáreas");
        lbHectareasHermanoC.setBounds(lbHermanoC.getX() + lbHermanoC.getWidth() + 5, lbHermanoC.getY(), 100, 20);
        modalResultados.add(lbHectareasHermanoC);
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(18, 160, 100, 30);
        btnAceptar.addActionListener((evt) -> {
            modalResultados.dispose();
        });
        modalResultados.add(btnAceptar);
        JButton btnSalir= new JButton("Salir");
        btnSalir.setBounds(btnAceptar.getX() + btnAceptar.getWidth() + 5, btnAceptar.getY(), 100, 30);
        btnSalir.addActionListener((evt) -> {
            System.exit(NORMAL);
        });
        modalResultados.add(btnSalir);
        
        modalResultados.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnIniciar) {
            generarHermanos();
            asignarHectareas();
            mostrarResultados();
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
