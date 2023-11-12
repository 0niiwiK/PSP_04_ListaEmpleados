package usarExcepciones;

public class SueldoSuperiorAMaximo extends Exception {
    private String error;

    public SueldoSuperiorAMaximo() {
        error = "El sueldo ingresado no puede superar al sueldo máximo del empledo";
    }
}
