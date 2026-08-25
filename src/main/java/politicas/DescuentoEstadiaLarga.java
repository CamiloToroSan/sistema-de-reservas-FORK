package politicas;

import com.mycompany.sistema.reservas.dominio.modelo.RangoFechas;
import java.time.Duration;

/**
 *
 * @author Aleja y Camilo
 */

public class DescuentoEstadiaLarga implements PoliticaDescuento {

    private final RangoFechas rangoFechas;

    public DescuentoEstadiaLarga(RangoFechas rangoFechas) {
        if (rangoFechas == null) {
            throw new IllegalArgumentException("El rango de fechas no puede ser nulo");
        }

        this.rangoFechas = rangoFechas;
    }

    @Override
    public double aplicarDescuento(double montoBase) {
        long dias = Duration.between(
                rangoFechas.fechaInicio(),
                rangoFechas.fechaFin()).toDays();

        if (dias > 7) {
            return montoBase * 0.75;
        }

        return montoBase;
    }
}