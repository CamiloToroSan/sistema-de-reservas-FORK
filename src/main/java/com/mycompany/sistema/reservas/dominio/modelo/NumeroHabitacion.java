package com.mycompany.sistema.reservas.dominio.modelo;


import java.util.regex.Pattern;

public record NumeroHabitacion(String valor) {
    private static final Pattern PATRON_NUMERO = 
        Pattern.compile("^[0-9+]+{3,4}$");
    
    public NumeroHabitacion {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El número de habitación no puede estar vacío");
        }
        if (!PATRON_NUMERO.matcher(valor).matches()) {
            throw new IllegalArgumentException("l número de habitación debe tener entre 3 y 4 dígitos");
        }
    }
}
