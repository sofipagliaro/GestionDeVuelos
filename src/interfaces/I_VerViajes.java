package interfaces;

import clases.Vuelo;
import clases.Reserva;
import java.util.List;

public interface I_VerViajes {

    List<Vuelo> verVuelos();

    List<Reserva> verReservas();
}
