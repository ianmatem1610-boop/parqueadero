package com.krakedev.parqueadero.controladores;

import com.krakedev.parqueadero.modelo.TicketCobro;
import com.krakedev.parqueadero.servicios.ServicioCobro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cobros")
public class CobroController {
    private final ServicioCobro servicioCobro;

    public CobroController(ServicioCobro servicioCobro) {
        this.servicioCobro = servicioCobro;
    }

    @PostMapping("/procesar/{placa}/{horas}")
    public ResponseEntity<TicketCobro> procesarSalida(@PathVariable String placa, @PathVariable int horas) {
        TicketCobro ticket = servicioCobro.procesarSalida(placa, horas);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        }
        return ResponseEntity.notFound ().build();
    }

    @GetMapping("/total")
    public double obtenerTotalRecaudado () {
        return servicioCobro.calcularTotalRecaudado();
    }

    @GetMapping("/historial")
    public ArrayList<TicketCobro> obtenerHistorial() {
        return servicioCobro.listarTickets();
    }
}