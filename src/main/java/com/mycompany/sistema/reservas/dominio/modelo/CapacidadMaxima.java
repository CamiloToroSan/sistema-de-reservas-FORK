package com.mycompany.sistema.reservas.dominio.modelo;

public record CapacidadMaxima(int valor) {

    public CapacidadMaxima {
        if (valor < 1) {
            throw new IllegalArgumentException(
                "La capacidad máxima debe ser al menos 1 persona ");
        }
    }
}