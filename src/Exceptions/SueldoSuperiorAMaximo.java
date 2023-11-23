package Exceptions;

public class SueldoSuperiorAMaximo extends Exception {
    private String error;

    public SueldoSuperiorAMaximo() {
        super("El sueldo ingresado no puede superar al sueldo máximo del empledo");
    }
    public SueldoSuperiorAMaximo(String error){
        super(error);
    }
}
