package usarExcepciones;

public class ESaldoNoValido extends Exception {        
    public ESaldoNoValido() {
        super("El saldo no puede ser negativo");
    }
    public ESaldoNoValido(String mensaje) {
        super(mensaje);
    }    
}
