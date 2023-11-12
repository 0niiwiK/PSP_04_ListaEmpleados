package usarExcepciones;

public class EmpleadoNoEncontrado extends Exception {
    private String error;

    public EmpleadoNoEncontrado(int numEmp) {
        error = "El empleado " + numEmp + " no ha sido encontrado";
    }
}
