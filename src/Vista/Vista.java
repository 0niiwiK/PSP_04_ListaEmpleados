package Vista;

import Controlador.Lista;

import Modelo.Empleado;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.io.File;

public class Vista {
    private JPanel panel1;
    private JList<String> jl_lista;
    JScrollPane scrollPane;
    private JButton btnCargar;
    private JButton btnGuardar;
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

        } catch (Exception e) {
            e.printStackTrace();
        }
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


