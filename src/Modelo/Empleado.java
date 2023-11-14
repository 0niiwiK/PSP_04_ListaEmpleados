package Modelo;

import usarExcepciones.SueldoSuperiorAMaximo;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Empleado implements Serializable {
    private static String SUELDO_EXCEPTION = "SUELDO_SUPERIOR_MAXIMO";
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

        if (this.sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
    }

    public Empleado(int num_empleado, String nombre, float sueldo,String fecha, float sueldo_max) throws SueldoSuperiorAMaximo {
        this.num_empleado = num_empleado;
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.sueldo_max = sueldo_max;

        if (this.sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();

        try {
            Date dateAux = sdf.parse(fecha);
            GregorianCalendar calendarAux = new GregorianCalendar();
            calendarAux.setTime(dateAux);
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

    public void setSueldo(double sueldo) throws SueldoSuperiorAMaximo {
        this.sueldo = sueldo;
        if (sueldo > this.sueldo_max)
            throw new SueldoSuperiorAMaximo();
        else
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

    @Override
    public String toString() {
        return "Número de Empleado: " + num_empleado + ", Nombre: " + nombre + ", Sueldo: " + sueldo + ", Sueldo Máximo: " + sueldo_max;
    }

    public void calcularSueldo() throws SueldoSuperiorAMaximo {
    }
}
