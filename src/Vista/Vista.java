package Vista;

import Controlador.Lista;

import Modelo.Empleado;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    private JList<String> jl_lista;
    JScrollPane scrollPane;
    private DefaultListModel<String> listModel;
    Lista listaempleados;

    public Vista() {


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
            listaempleados.add(new Empleado(1, "Pepito", 1000, 2000));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!listaempleados.isLast()) {
            listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
            listaempleados.goNext();
        }
        listModel.addElement( listaempleados.getAct().getNumEmp() + " " + listaempleados.getAct().getMain().getTipo());
        jl_lista = new JList<>(listModel);
        scrollPane = new JScrollPane(jl_lista);
    }
}


