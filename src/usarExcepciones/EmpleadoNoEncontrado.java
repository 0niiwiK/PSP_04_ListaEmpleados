package usarExcepciones;

public class EmpleadoNoEncontrado extends Exception {
    public EmpleadoNoEncontrado(int numEmp) {
        super("El empleado " + numEmp + " no ha sido encontrado"); 
    }
    public EmpleadoNoEncontrado(String error) {
        super(error);
    } 
}
