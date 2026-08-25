package notificacion;

import com.mycompany.sistema.reservas.dominio.modelo.Cliente;

public class PushNotificadorService implements NotificadorService {

    @Override
    public void notificar(Cliente cliente, String mensaje) {
        System.out.println(
                "[PUSH / APP para " + cliente.getNombre() + "]: " + mensaje);
    }
}