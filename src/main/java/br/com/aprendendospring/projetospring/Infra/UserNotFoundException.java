package br.com.aprendendospring.projetospring.Infra;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(Object id){
        super("Usuario com ID:" + id + " não existente.");
    }
}
