package Vista;

import Controlador.Lista;

import javax.swing.*;

public class Vista {
    private JPanel panel1;
    private JList jl_lista;

    private Lista listaempleados;

    public Vista() {
        listaempleados = new Lista();
        jl_lista = new JList();
        ListModel listModel = new DefaultListModel();
        listaempleados.goFirst();
        while (listaempleados.)
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Vista");
        frame.setContentPane(new Vista().panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}


