package com.krakedev.parqueadero.servicios;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.modelo.Vehiculo;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ServicioCobro {
    private final ServicioVehiculos servicioVehiculos;
    private ArrayList<TicketCobro> historicoTickets = new ArrayList<>();
    public ServicioCobro(ServicioVehiculos  servicioVehiculos) {
        this.servicioVehiculos =servicioVehiculos;
    }
    public TicketCobro procesarSalida(String placa, int horas) {
        Vehiculo vehiculo = servicioVehiculos.retirarVehiculo(placa);
        if (vehiculo == null) {
            return null;
        }
        double total = vehiculo.calcularTarifa(horas);
        
        String codigoGenerado = "TCK-" + (int)(Math.random () * 900 +100);
        
        TicketCobro ticket = new TicketCobro(codigoGenerado, vehiculo, horas, total, LocalDate.now ());
        historicoTickets.add(ticket);
        
        return ticket;
    }

    public double calcularTotalRecaudado() {
        double sumaTotal = 0;
        for (TicketCobro ticket : historicoTickets) {
            sumaTotal += ticket.getTotalPagar();
        }
        return sumaTotal;
    }

    public ArrayList<TicketCobro> listarTickets () {
        return historicoTickets;
    }
}