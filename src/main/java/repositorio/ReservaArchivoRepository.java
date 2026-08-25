package repositorio;

import com.mycompany.sistema.reservas.dominio.modelo.Reserva;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReservaArchivoRepository implements ReservaRepository {
    private static final String ARCHIVO = "reservas.txt";
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void guardar(Reserva reserva) {
        try (FileWriter writer = new FileWriter(ARCHIVO, true)) {
            String linea = String.format(
                "ID:%s | Cliente:%s | Habitación:%s | Periodo:%s a %s | Estado:%s | Confirmada:%s%n",
                reserva.getId(),
                reserva.getCliente().getNombre(),
                reserva.getHabitacion().getNumero().valor(),
                reserva.getPeriodo().fechaInicio().format(FORMATO),
                reserva.getPeriodo().fechaFin().format(FORMATO),
                reserva.getEstado(),
                LocalDateTime.now().format(FORMATO)
            );
            writer.write(linea);
            System.out.println("[Repositorio Archivo] Reserva " + reserva.getId() + " guardada en " + ARCHIVO);
        } catch (IOException e) {
            System.err.println("Error al guardar en archivo: " + e.getMessage());
        }
    }
}