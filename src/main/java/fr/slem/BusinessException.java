package fr.slem;

public class BusinessException extends Exception {

    private Object[] parameters;

    public BusinessException(final String message, final Object... parameters) {
        super(message);
        this.parameters = parameters;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
