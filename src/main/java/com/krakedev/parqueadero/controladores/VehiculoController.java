package com.krakedev.parqueadero.controladores;

import com.krakedev.parqueadero.modelo.Auto;
import com.krakedev.parqueadero.modelo.Motocicleta;
import com.krakedev.parqueadero.modelo.Vehiculo;
import com.krakedev.parqueadero.servicios.ServicioVehiculos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    
    private final ServicioVehiculos  servicioVehiculos;

    public VehiculoController(ServicioVehiculos servicioVehiculos) {
        this.servicioVehiculos =  servicioVehiculos;
    }

    @PostMapping("/auto")
    public ResponseEntity<String> ingresarAuto(@RequestBody Auto auto) {
        boolean ingresado =servicioVehiculos.ingresarVehiculo(auto);
        if (ingresado) {
            return ResponseEntity.ok("el auto  a ingresado correctamente.");
        }
        return ResponseEntity.badRequest().body("no se pudo ingresar el auto por capacidad llena o placa doble.");
    }

    @PostMapping("/moto")
    public ResponseEntity<String> ingresarMoto(@RequestBody Motocicleta moto) {
        boolean ingresado =servicioVehiculos.ingresarVehiculo(moto);
        if (ingresado) {
            return ResponseEntity.ok("la motocicleta  a ingresada correctamente.");
        }
        return ResponseEntity.badRequest().body("no se pudo ingresar la motocicleta porcapacidad llena o placa doble.");
    }

    @GetMapping
    public ArrayList<Vehiculo> listarEstacionados() {
        return servicioVehiculos.listarVehiculos();
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
        Vehiculo vehiculo =servicioVehiculos.buscarPorPlaca(placa);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        }
        return ResponseEntity.notFound().build();
    }
}