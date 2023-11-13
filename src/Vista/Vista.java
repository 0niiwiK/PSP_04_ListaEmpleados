package Vista;

import Controlador.Lista;

import Modelo.Analista;
import Modelo.Empleado;
import Modelo.Programador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.FileChooserUI;
import java.io.File;

public class Vista {
    private JPanel panel1;
    private JList<String> jl_lista;
    JScrollPane scrollPane;
    private JButton btnCargar;
    private JButton btnGuardar;
    private JButton btnCrear;
    private JLabel lblNombre;
    private JLabel lblNumero;
    private JLabel lblSueldo;
    private JLabel lblFecha;
    private JLabel lblSueldo_max;
    private JTextField txtfSueldo;
    private JTextField txtfNombre;
    private JTextField txtfNumero;
    private JTextField txtfFecha;
    private JTextField txtfCargo;
    private JTextField txtfMaxSueldo;
    private JLabel lblTipo;
    private JTextField txtfOpcion1;
    private JTextField txtfOpcion2;
    private JLabel lblOpcion1;
    private JLabel lblOpcion2;
    private JButton btnCreaMass;
    private JButton btnModificar;
    private JButton btnOrdenar;
    private JButton btnPrimero;
    private JButton btnUltimo;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnCalcular;
    private JLabel txtCalculo;
    private DefaultListModel<String> listModel;
    Lista listaempleados;

    public Vista() {
        btnCargar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Selecciona el archivo a cargar");
            if (chooser.showOpenDialog(panel1) == JFileChooser.APPROVE_OPTION) {
                try {
                    listaempleados.leerArchivo(chooser.getSelectedFile().getAbsolutePath());
                    listModel.clear();
                    while (!listaempleados.isLast()) {
                        listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
                        listaempleados.goNext();
                    }
                    listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
                    jl_lista.setModel(listModel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jl_lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JList list = (JList) e.getSource();
                txtfNombre.setText(listaempleados.getEmpleadoAt(list.getSelectedIndex()).getMain().getNombre());
            }
        });

        btnCrear.addActionListener(e -> {
            Empleados dialog = new Empleados(listaempleados);
            dialog.pack();
            dialog.setVisible(true);
        });

        btnGuardar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Selecciona el archivo a guardar");
            if (chooser.showSaveDialog(panel1) == JFileChooser.APPROVE_OPTION) {
                try {
                    listaempleados.escribirArchivo(chooser.getSelectedFile().getAbsolutePath());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(new Vista().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        listaempleados = new Lista();
        listModel = new DefaultListModel<>();
        try {
            listaempleados.add(new Programador(1, "Pedro", 2000, 2500, 0, "Java"));
            listaempleados.add(new Programador(2, "Juan", 2200, 2600, 0, "Python"));
            listaempleados.add(new Analista(3, "Carla", 3000, 3200, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        listaempleados.goFirst();
        while (!listaempleados.isLast() && !listaempleados.isEmpty()) {
            listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
            listaempleados.goNext();
        }
        if (!listaempleados.isEmpty())
            listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());

        jl_lista = new JList<>(listModel);
        scrollPane = new JScrollPane(jl_lista);
    }
}


