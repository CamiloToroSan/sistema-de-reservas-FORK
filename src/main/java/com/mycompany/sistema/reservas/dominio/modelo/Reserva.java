package com.mycompany.sistema.reservas.dominio.modelo;

import java.util.UUID;

public class Reserva {
    private final UUID id;
    private final Cliente cliente;
    private final Habitacion habitacion;
    private RangoFechas periodo;
    private EstadoReserva estado;

    // Constructor con habitación (3 parámetros)
    public Reserva(Cliente cliente, Habitacion habitacion, RangoFechas periodo) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente es obligatorio");
        }
        if (!cliente.puedeRealizarReservas()) {
            throw new IllegalStateException("El cliente '" + cliente.getNombre() + "' no está habilitado");
        }
        if (habitacion == null) {
            throw new IllegalArgumentException("La habitación es obligatoria");
        }
        if (!habitacion.estaDisponible()) {
            throw new IllegalStateException("La habitación " + habitacion.getNumero().valor() + " no está disponible");
        }
        if (periodo == null) {
            throw new IllegalArgumentException("El periodo de la reserva es obligatorio");
        }

        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.periodo = periodo;
        this.estado = EstadoReserva.PENDIENTE;
    }

    // Constructor original (2 parámetros) - para compatibilidad
    public Reserva(Cliente cliente, RangoFechas periodo) {
        this(cliente,
                new Habitacion(new NumeroHabitacion("000"), new CapacidadMaxima(1)),
                periodo);
    }

    public void confirmar() {
        if (this.estado == EstadoReserva.CANCELADA) {
            throw new IllegalStateException("No se puede confirmar una reserva cancelada");
        }
        this.estado = EstadoReserva.CONFIRMADA;
        this.habitacion.ocupar();
    }

    public void cancelar() {
        if (this.estado == EstadoReserva.CONFIRMADA) {
            this.habitacion.liberar();
        }
        this.estado = EstadoReserva.CANCELADA;
    }

    public UUID getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Habitacion getHabitacion() { return habitacion; }
    public RangoFechas getPeriodo() { return periodo; }
    public EstadoReserva getEstado() { return estado; }
}