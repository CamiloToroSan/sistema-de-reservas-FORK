package politicas;

/**
 *
 * @author Aleja y Camilo
 */

public class DescuentoTemporadaBaja implements PoliticaDescuento {

    @Override
    public double aplicarDescuento(double montoBase) {
        return montoBase * 0.85; // 15% de descuento
    }
}