package com.mycompany.sistema.reservas.dominio;

import com.mycompany.sistema.reservas.dominio.modelo.*;
import java.time.LocalDateTime;
import notificacion.*;
import politicas.*;
import repositorio.*;
import servicio.ConfirmacionReservaService;

/**
 * Clase de prueba para demostrar las extensiones del taller:
 * - Gestión de habitaciones (NumeroHabitacion, CapacidadMaxima, EstadoHabitacion, Habitacion)
 * - Nuevas políticas de descuento (Estadía Larga: 25% si > 7 días)
 * - Nuevos canales de notificación (WhatsApp)
 * - Persistencia en archivo (ReservaArchivoRepository)
 */
public class pruebaMain2 {

    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACION DE EXTENSIONES DEL TALLER ===");
        System.out.println();

        // 1. Crear cliente
        Cliente cliente = new Cliente("Beatriz Morales", new Email("beatriz@empresa.com"));
        System.out.println("Cliente creado: " + cliente.getNombre());

        // 2. Crear habitación real (número "101", capacidad 2)
        Habitacion habitacion = new Habitacion(
            new NumeroHabitacion("101"),
            new CapacidadMaxima(2)
        );
        System.out.println("Habitacion creada: Nro " + habitacion.getNumero().valor()
                           + ", Capacidad: " + habitacion.getCapacidad().valor()
                           + " personas, Estado: " + habitacion.getEstado());

        // 3. Periodo de 8 días (del día 1 al día 9) para activar descuento por estadía larga
        RangoFechas periodo = new RangoFechas(
            LocalDateTime.now().plusDays(1),
            LocalDateTime.now().plusDays(9)
        );
        System.out.println("Periodo creado: " + periodo.fechaInicio() + " a " + periodo.fechaFin());
        System.out.println("Duracion: "
            + java.time.temporal.ChronoUnit.DAYS.between(periodo.fechaInicio(), periodo.fechaFin())
            + " dias");
        System.out.println();

        // 4. Crear reserva con la habitación real (constructor de 3 parámetros)
        Reserva reserva = new Reserva(cliente, habitacion, periodo);
        System.out.println("Reserva creada con ID: " + reserva.getId()
                           + ", Estado inicial: " + reserva.getEstado());

        // 5. Configurar infraestructura (DIP): repositorio en archivo y notificador WhatsApp
        ReservaRepository repositorio = new ReservaArchivoRepository();
        NotificadorService notificador = new WhatsAppNotificadorService();
        System.out.println("Infraestructura configurada: ArchivoRepository + WhatsAppNotificador");
        System.out.println();

        // 6. Crear el servicio inyectando dependencias (sin cambios)
        ConfirmacionReservaService servicio = new ConfirmacionReservaService(repositorio, notificador);
        System.out.println("Servicio de confirmacion creado con inyeccion de dependencias");
        System.out.println();

        // 7. Aplicar política de descuento por estadía larga (OCP)
        PoliticaDescuento descuento = new DescuentoEstadiaLarga(periodo);
        System.out.println("Politica de descuento aplicada: Estadia Larga (25% si > 7 dias)");
        System.out.println();

        // 8. Ejecutar el caso de uso
        double precioBase = 300.0;
        System.out.println("Procesando reserva... (precio base: $" + precioBase + ")");
        double precioFinal = servicio.procesar(reserva, descuento, precioBase);

        // 9. Resultados finales
        System.out.println();
        System.out.println("=== RESULTADOS FINALES ===");
        System.out.println("Total pagado: $" + precioFinal);
        System.out.println("Estado de la habitacion: " + habitacion.getEstado());
        System.out.println("La reserva se ha guardado en el archivo 'reservas.txt'");
        System.out.println("Se ha enviado una notificacion por WhatsApp.");
        System.out.println();
        System.out.println("=== DEMOSTRACION COMPLETADA CON EXITO ===");
    }
}