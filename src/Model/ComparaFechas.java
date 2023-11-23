package Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ComparaFechas implements IFecha {
    public final static int DIA_DEL_MES = Calendar.DAY_OF_MONTH;
    public final static int MES_DEL_ANIO = Calendar.MONTH;
    public final static int ANIO = Calendar.YEAR;
    GregorianCalendar fecha_actual;

    public ComparaFechas() {
        fecha_actual = new GregorianCalendar();
    }


    @Override
    public int dia() {
        return fecha_actual.get(DIA_DEL_MES);
    }

    @Override
    public int mes() {
        return fecha_actual.get(MES_DEL_ANIO);
    }

    @Override
    public int anio() {
        return fecha_actual.get(ANIO);
    }

    @Override
    public boolean cumpleMes(GregorianCalendar fecha) {
        return fecha.get(DIA_DEL_MES) == this.dia();
    }

    @Override
    public boolean cumpleAnios(GregorianCalendar fecha) {
        return fecha.get(DIA_DEL_MES) == this.dia() && fecha.get(MES_DEL_ANIO) == this.mes();
    }
}
