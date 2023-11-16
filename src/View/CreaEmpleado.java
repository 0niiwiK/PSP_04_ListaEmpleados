package View;

import Controler.Lista;
import Model.Analista;
import Model.Empleado;
import Model.Programador;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import Exceptions.SueldoSuperiorAMaximo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;


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
    private JLabel lblTit1;
    private JLabel lblNewOpcion1;
    private JLabel lblNewOpcion2;
    private JTextField txtfNewNumero;
    private JTextField txtfNewNombre;
    private JTextField txtfNewSueldo;
    private JTextField txtfNewMaxSueldo;
    private JTextField txtfNewOpcion1;
    private JTextField txtfNewOpcion2;
    private JComboBox<String> cbCargo;
    private JPanel jpDate;
    private final JDateChooser dateChooser = new JDateChooser();


    int num_empleado;
    String nombre;
    float sueldo;
    float sueldo_max;
    String fecha_alta;
    String tipo;
    double op1;
    int opA2;
    String opP2;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Empleado empleado;


    public CreaEmpleado(Lista l) {

        jpDate.setLayout(new BorderLayout());
        dateChooser.setDateFormatString("dd/MM/yyyy");
        JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        editor.setEditable(false);
        jpDate.add(dateChooser, BorderLayout.CENTER);
        
        cbCargo.addItem("Analista");
        cbCargo.addItem("Programador");

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnOk);

        compruebaComboBox();
        
        cbCargo.setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compruebaComboBox();
            }
        });
        
        btnOk.addActionListener(e -> {
            try {
                num_empleado = Integer.parseInt(txtfNewNumero.getText());
                nombre = txtfNewNombre.getText();
                sueldo = Float.parseFloat(txtfNewSueldo.getText());
                sueldo_max = Float.parseFloat(txtfNewMaxSueldo.getText());

                if (dateChooser.getDate() != null) {
                    fecha_alta = sdf.format(dateChooser.getDate());
                } else {
                    fecha_alta = null;
                }

                tipo = (String) cbCargo.getSelectedItem();
                op1 = Double.parseDouble(txtfNewOpcion1.getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Algún campo es erróneo", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (tipo.equals("Analista")) {
                try {
                    opA2 = (int) Double.parseDouble(txtfNewOpcion2.getText());
                    if (fecha_alta == null) {
                        empleado = new Analista(num_empleado, nombre, sueldo, sueldo_max, op1, opA2);
                    } else {
                        empleado = new Analista(num_empleado, nombre, sueldo, fecha_alta, sueldo_max, op1, opA2);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Algún campo es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SueldoSuperiorAMaximo | RuntimeException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

            } else if (tipo.equals("Programador")) {
                try {
                    opP2 = txtfNewOpcion2.getText();

                    if (fecha_alta == null) {
                        empleado = new Programador(num_empleado, nombre, sueldo, sueldo_max, op1, opP2);
                    } else {
                        empleado = new Programador(num_empleado, nombre, sueldo, fecha_alta, sueldo_max, op1, opP2);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Algún campo es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SueldoSuperiorAMaximo ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (RuntimeException rte) {
                    JOptionPane.showMessageDialog(this, "Fecha no valida", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            l.add(empleado);
            onOK();
        });
        
        btnCancelar.addActionListener(e -> onCancel());
        
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void compruebaComboBox() {
        tipo = (String) cbCargo.getSelectedItem();
        
        if (tipo.equals("Analista")) {
            lblNewOpcion1.setText("Plus Anual");
            lblNewOpcion2.setText("Años de experiencia");
        } else if (tipo.equals("Programador")) {
            lblNewOpcion1.setText("Sueldo Extra Mensual");
            lblNewOpcion2.setText("Lenguaje Principal");
        }
    }
    

    private void onOK() {
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}
