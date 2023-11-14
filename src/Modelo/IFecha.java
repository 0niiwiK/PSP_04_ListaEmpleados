package Modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

public interface IFecha {
    public final static int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    public final static int MES_DEL_ANIO = Calendar.MONTH;
    public final static int ANIO = Calendar.YEAR;

    public abstract int dia();
    public abstract int mes();
    public abstract int anio();

    boolean cumpleMes(GregorianCalendar fecha);

    boolean cumpleAnios(GregorianCalendar fecha);


}
