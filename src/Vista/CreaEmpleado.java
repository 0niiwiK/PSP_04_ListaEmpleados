package Vista;

import Controlador.Lista;
import Modelo.Analista;
import Modelo.Empleado;
import Modelo.Programador;
import usarExcepciones.SueldoSuperiorAMaximo;

import javax.swing.*;
import java.awt.event.*;

public class CreaEmpleado extends JDialog {
    private JPanel contentPane;
    private JButton btnOk;
    private JButton btnCancelar;
    private JLabel lblNewNumero;
    private JLabel lblNewNombre;
    private JLabel lblNewSueldo;
    private JLabel lblNewFecha;
    private JLabel lblNewSueldo_max;
    private JLabel lblNewTipo;
    private JLabel lblNewOpcion1;
    private JLabel lblNewOpcion2;
    private JTextField txtfNewNumero;
    private JTextField txtfNewNombre;
    private JTextField txtfNewSueldo;
    private JTextField txtfNewFecha;
    private JTextField txtfNewMaxSueldo;
    private JTextField txtfNewOpcion1;
    private JTextField txtfNewOpcion2;
    private JComboBox cbCargo;
    private JLabel lblTit1;

    int num_empleado;
    String nombre;
    float sueldo;
    float sueldo_max;
    String fecha_alta;
    String tipo;
    double op1;
    int opA2;
    String opP2;
    Empleado empleado;

    public CreaEmpleado(Lista l) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnOk);

        cbCargo.addItem("Analista");
        cbCargo.addItem("Programador");

        compruebaComboBox();

        cbCargo.addActionListener(e -> compruebaComboBox());

        btnOk.addActionListener(e -> {
            num_empleado = Integer.parseInt(txtfNewNumero.getText());
            nombre = txtfNewNombre.getText();
            sueldo = Float.parseFloat(txtfNewSueldo.getText());
            sueldo_max = Float.parseFloat(txtfNewMaxSueldo.getText());
            fecha_alta = txtfNewFecha.getText();
            tipo = (String) cbCargo.getSelectedItem();
            op1 = Double.parseDouble(txtfNewOpcion1.getText());
            if (tipo.equals("Analista")) {
                try {
                    opA2 = (int) Double.parseDouble(txtfNewOpcion2.getText());
                    if (txtfNewFecha.getText().isEmpty()) {
                        empleado = new Analista(num_empleado, nombre, sueldo, sueldo_max, op1, opA2);
                    }else {
                        empleado = new Analista(num_empleado, nombre, sueldo,fecha_alta, sueldo_max, op1, opA2);
                    }
                } catch (SueldoSuperiorAMaximo ex) {
                    throw new RuntimeException(ex);
                }
            } else if (tipo.equals("Programador")) {
                try {
                    opP2 = txtfNewOpcion2.getText();
                    if (txtfNewFecha.getText().isEmpty()) {
                        empleado = new Programador(num_empleado, nombre, sueldo, sueldo_max, op1, opP2);
                    }else {
                        empleado = new Programador(num_empleado, nombre, sueldo,fecha_alta, sueldo_max, op1, opP2);
                    }
                } catch (SueldoSuperiorAMaximo ex) {
                    throw new RuntimeException(ex);
                }
            }
            l.add(empleado);
            onOK();
        });

        btnCancelar.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void compruebaComboBox() {
        tipo = (String) cbCargo.getSelectedItem();

        if (tipo.equals("Analista")) {
            lblNewOpcion1.setText("Plus Anual");
            lblNewOpcion2.setText("Años de experiencia");
        }else if (tipo.equals("Programador")) {
            lblNewOpcion1.setText("Sueldo Extra Mensual");
            lblNewOpcion2.setText("Lenguaje Principal");
        }
    }

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
