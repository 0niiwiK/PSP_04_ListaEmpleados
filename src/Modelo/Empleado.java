package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado implements Serializable {
    int num_empleado;
    transient String nombre;
    double sueldo;
    float sueldo_max;
    GregorianCalendar fecha_alta;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


    public Empleado(int num_empleado, String nombre, float sueldo, float sueldo_max) throws SueldoSuperiorAMaximo {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.sueldo_max = sueldo_max;
        this.fecha_alta = new GregorianCalendar();
        compruebaSueldos(sueldo);

        if (this.sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
    }

    public Empleado(int num_empleado, String nombre, float sueldo,String fecha, float sueldo_max) throws SueldoSuperiorAMaximo {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.sueldo_max = sueldo_max;
        compruebaSueldos(sueldo);

        try {
            Date dateAux = sdf.parse(fecha);
            GregorianCalendar calendarAux = new GregorianCalendar();
            calendarAux.setTime(dateAux);
            if ((calendarAux.getTime().getYear() > (new GregorianCalendar().getTime().getYear()))) {
                throw new RuntimeException(("El año no es valido"));
            }
            if ((calendarAux.getTime().getYear() < (new GregorianCalendar().getTime().getYear()-100))){
                throw new RuntimeException(("El año no es valido"));
            }
            this.fecha_alta = calendarAux;
        } catch (ParseException e) {
            throw new RuntimeException("la cadena de fecha no tiene el formato adecuado", e);
        }
    }


    public int getNum_empleado() {
        return num_empleado;
    }

    public double getSueldo() {

        return sueldo;
    }

    public void setSueldo(double sueldo){
        this.sueldo = sueldo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GregorianCalendar getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(GregorianCalendar fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public void setNum_empleado(int num_empleado) {
        this.num_empleado = num_empleado;
    }
    

    public float getSueldo_max() {
        return sueldo_max;
    }

    public void setSueldo_max(float sueldo_max) throws SueldoSuperiorAMaximo {
            if (this.sueldo > sueldo_max)
                throw new SueldoSuperiorAMaximo();
            else
                this.sueldo_max = sueldo_max;
    }
    public String getsdfFecha(GregorianCalendar calendar) {
        return sdf.format(calendar.getTime());
    }

    public String getTipo() {
        return "Empleado";
    }
    
    private void compruebaSueldos(float sueldo) throws SueldoSuperiorAMaximo {
        double op1, op2;

        try {
            op1 = sueldo;
            op2 = this.sueldo_max;
        }catch (NumberFormatException ex) {
            throw new NumberFormatException();
        }

        if (op1 > op2)
            throw new SueldoSuperiorAMaximo();
        else
            this.sueldo = sueldo;
    }
    
    @Override
    public String toString() {
        return "Número de Empleado: " + num_empleado + ", Nombre: " + nombre + ", Sueldo: " + sueldo + ", Sueldo Máximo: " + sueldo_max;
    }

    public void calcularSueldo() throws SueldoSuperiorAMaximo {
    }
}