package notificacion;

import com.mycompany.sistema.reservas.dominio.modelo.Cliente;

public class WhatsAppNotificadorService implements NotificadorService {

    @Override
    public void notificar(Cliente cliente, String mensaje) {
        System.out.println(
                "[WHATSAPP +57 " + cliente.getNombre() + "]: " + mensaje);
    }
}