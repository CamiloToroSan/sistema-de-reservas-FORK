package com.mycompany.sistema.reservas.dominio.modelo;

public class Habitacion {
    private final NumeroHabitacion numero;
    private final CapacidadMaxima capacidad;
    private EstadoHabitacion estado;

    public Habitacion(NumeroHabitacion numero, CapacidadMaxima capacidad) {
        if (numero == null || capacidad == null) {
            throw new IllegalArgumentException("Número y capacidad son obligatorios");
        }
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = EstadoHabitacion.DISPONIBLE;
    }

    public boolean estaDisponible() {
        return this.estado == EstadoHabitacion.DISPONIBLE;
    }

    public void ocupar() {
        if (this.estado == EstadoHabitacion.DISPONIBLE) {
            this.estado = EstadoHabitacion.OCUPADA;
        } else {
            throw new IllegalStateException("La habitación no está disponible para ocupar");
        }
    }

    public void liberar() {
        if (this.estado == EstadoHabitacion.OCUPADA) {
            this.estado = EstadoHabitacion.DISPONIBLE;
        } else {
            throw new IllegalStateException("La habitación no está ocupada");
        }
    }


    public NumeroHabitacion getNumero() { return numero; }
    public CapacidadMaxima getCapacidad() { return capacidad; }
    public EstadoHabitacion getEstado() { return estado; }
}