package View;

import Controller.Lista;
import Model.Analista;
import Model.ComparaFechas;
import Model.Empleado;
import Model.Programador;
import Exceptions.SueldoSuperiorAMaximo;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Vista {
    private JPanel panel1;
    private Lista listaempleados;
    private Lista.Node nodo_actual;
    private JList<String> jl_lista;
    private JScrollPane scrollPane;
    private ComparaFechas comparaFechas;
    private DefaultListModel<String> listModel;
    private JButton btnCargar;
    private JButton btnGuardar;
    private JButton btnCrear;
    private JButton btnOrdenar;
    private JButton btnPrimero;
    private JButton btnUltimo;
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnCalcular;
    private JButton btnCreaMass;
    private JLabel lblNombre;
    private JLabel lblNumero;
    private JLabel lblSueldo;
    private JLabel lblFecha;
    private JLabel lblSueldo_max;
    private JLabel lblTipo;
    private JLabel lblOpcion1;
    private JLabel lblOpcion2;
    private JTextField txtfSueldo;
    private JTextField txtfNombre;
    private JTextField txtfNumero;
    private JTextField txtfFecha;
    private JTextField txtfCargo;
    private JTextField txtfMaxSueldo;
    private JTextField txtfOpcion1;
    private JTextField txtfOpcion2;

    public Vista() {

        btnCalcular.setEnabled(false);
        btnOrdenar.setEnabled(false);
        txtfNumero.setEditable(false);
        txtfNombre.setEditable(false);
        txtfSueldo.setEditable(false);
        txtfMaxSueldo.setEditable(false);
        txtfFecha.setEditable(false);
        txtfCargo.setEditable(false);
        txtfOpcion1.setEditable(false);
        txtfOpcion2.setEditable(false);
        btnSiguiente.setEnabled(false);
        btnAnterior.setEnabled(false);
        btnPrimero.setEnabled(false);
        btnUltimo.setEnabled(false);

        jl_lista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    btnSiguiente.doClick();
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    btnAnterior.doClick();
                }
            }
        });

        btnCalcular.addActionListener(e -> {
            try {
                nodo_actual.getMain().calcularSueldo();
                JOptionPane.showMessageDialog(null,
                        "Salario modificado correctamente.\nNuevo sueldo: " + nodo_actual.getMain().getSueldo(),
                        "Salario modificado", JOptionPane.INFORMATION_MESSAGE);
                cargarLista();
            } catch (SueldoSuperiorAMaximo ex) {
                JOptionPane.showMessageDialog(null,
                        "El salario quedaría por encima del maximo.\nNo se puede modificar.",
                        "Salario superior al maximo.",
                        JOptionPane.ERROR_MESSAGE);
            }
            rellenarCampos();
            jl_lista.setSelectedIndex(0);
        });

        btnCreaMass.addActionListener(e -> {
            try {
                listaempleados.crearAleatoriosCienMil();
                cargarLista();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            jl_lista.setSelectedIndex(0);
        });

        btnSiguiente.addActionListener(e -> {
            if (!nodo_actual.isLast()) {
                setNodo_actual(nodo_actual.getNextNode());
                jl_lista.setSelectedValue(nodo_actual.getNumEmp() + " " + nodo_actual.getMain().getTipo(), true);
                rellenarCampos();
            }
        });

        btnPrimero.addActionListener(e -> {
            setNodo_actual(listaempleados.getFirstNode());
            jl_lista.setSelectedIndex(nodo_actual.getIndice());
            rellenarCampos();
        });

        btnUltimo.addActionListener(e -> {
            setNodo_actual(listaempleados.getLastNode());
            jl_lista.setSelectedIndex(nodo_actual.getIndice());
            rellenarCampos();
        });

        btnOrdenar.addActionListener(e -> {
            List<Empleado> lista = new ArrayList<>();
            listaempleados.goFirst();
            while (!listaempleados.isLast()) {
                lista.add(listaempleados.getAct().getMain());
                listaempleados.goNext();
            }
            lista.add(listaempleados.getAct().getMain());
            long startTime = System.nanoTime();
            lista.sort(Comparator.comparing(Empleado::getNum_empleado));
            long endTime = System.nanoTime();
            int tiempo = (int) ((endTime - startTime) / 1000000);
            JOptionPane.showMessageDialog(null,
                    "Tiempo de ordenamiento burbuja: " + listaempleados.sort() + " ms\n" +
                            "Tiempo de ordenamiento coleccion: " + tiempo + " ms",
                    "Tiempo de ordenamiento", JOptionPane.INFORMATION_MESSAGE);
            cargarLista();
            jl_lista.setSelectedIndex(0);

        });

        btnAnterior.addActionListener(e -> {
            if (!nodo_actual.isFirst()) {
                setNodo_actual(nodo_actual.getPrevNode());
                jl_lista.setSelectedIndex(nodo_actual.getIndice());
                rellenarCampos();
            }
        });

        btnCargar.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setDialogTitle("Selecciona el archivo a cargar");
            if (chooser.showOpenDialog(panel1) == JFileChooser.APPROVE_OPTION) {
                try {
                    listaempleados.leerArchivo(chooser.getSelectedFile().getAbsolutePath());
                    listModel.clear();
                    while (!listaempleados.isLast()) {
                        listModel.addElement(listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
                        listaempleados.goNext();
                    }
                    listModel.addElement(listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
                    jl_lista.setModel(listModel);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                cargarLista();
            }
            jl_lista.setSelectedIndex(0);
        });

        jl_lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && !jl_lista.isSelectionEmpty()) {
                JList list = (JList) e.getSource();
                setNodo_actual(listaempleados.getEmpleadoAt(list.getSelectedIndex()));
                rellenarCampos();
            }
        });

        btnCrear.addActionListener(e -> {
            CreaEmpleado dialog = new CreaEmpleado(listaempleados);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
            cargarLista();
            jl_lista.setSelectedIndex(listaempleados.getCounter() - 1);
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


    private void createUIComponents() {
        comparaFechas = new ComparaFechas();
        listaempleados = new Lista();
        listModel = new DefaultListModel<>();
        cargarLista();
    }

    public void cargarLista() {
        listModel.clear();
        if (jl_lista != null)
            jl_lista.clearSelection();
        jl_lista = new JList<>(listModel);
        listaempleados.goFirst();
        while (!listaempleados.isLast() && !listaempleados.isEmpty()) {
            listModel.addElement(listaempleados.getAct().getMain().getNum_empleado() + " " + listaempleados.getAct().getMain().getTipo());
            listaempleados.goNext();
        }
        if (!listaempleados.isEmpty())
            listModel.addElement(listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());

        jl_lista = new JList<>(listModel);
        scrollPane = new JScrollPane(jl_lista);
        if (!listaempleados.isEmpty()) {
            btnOrdenar.setEnabled(true);
            btnPrimero.setEnabled(true);
            btnUltimo.setEnabled(true);
        }
    }

    public void setNodo_actual(Lista.Node nodo) {
        this.nodo_actual = nodo;
    }

    public void rellenarCampos() {
        if (nodo_actual != null) {
            btnCreaMass.setEnabled(true);
            btnCalcular.setEnabled(true);
            btnOrdenar.setEnabled(true);

            System.out.println(nodo_actual);
            txtfNumero.setText(String.valueOf(this.nodo_actual.getMain().getNum_empleado()));
            txtfNombre.setText(this.nodo_actual.getMain().getNombre());
            txtfSueldo.setText(String.valueOf(this.nodo_actual.getMain().getSueldo()));
            txtfMaxSueldo.setText(String.valueOf(this.nodo_actual.getMain().getSueldo_max()));
            txtfFecha.setText(this.nodo_actual.getMain().getsdfFecha(this.nodo_actual.getMain().getFecha_alta()));
            if (this.nodo_actual.getMain() instanceof Programador) {
                txtfCargo.setText("Programador");
                lblOpcion1.setText("Sueldo Extra Mensual");
                txtfOpcion1.setText(String.valueOf(((Programador) this.nodo_actual.getMain()).getSueldo_extra_mensual()));
                lblOpcion2.setText("Lenguaje Principal");
                txtfOpcion2.setText(((Programador) this.nodo_actual.getMain()).getLenguaje_principal());
                if (!nodo_actual.isFirst())
                    btnCalcular.setEnabled(comparaFechas.cumpleMes(this.nodo_actual.getMain().getFecha_alta()));
            } else {
                txtfCargo.setText("Analista");
                lblOpcion1.setText("Plus Anual");
                txtfOpcion1.setText(String.valueOf(((Analista) this.nodo_actual.getMain()).getPlus_anual()));
                lblOpcion2.setText("Años de experiencia");
                txtfOpcion2.setText(String.valueOf(((Analista) this.nodo_actual.getMain()).getAnios_experiencia()));
                if (!nodo_actual.isFirst())
                    btnCalcular.setEnabled(comparaFechas.cumpleAnios(this.nodo_actual.getMain().getFecha_alta()));
            }

            btnSiguiente.setEnabled(!nodo_actual.isLast());
            btnAnterior.setEnabled(!nodo_actual.isFirst());
            if (nodo_actual.getMain().getTipo().equals("Programador")) {
                lblOpcion1.setText("Sueldo extra mensual");
                lblOpcion2.setText("Lenguaje principal");
            } else {
                lblOpcion1.setText("Plus anual");
                lblOpcion2.setText("Años de experiencia");
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("View");
        frame.setContentPane(new Vista().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
