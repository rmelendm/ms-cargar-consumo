package org.example.Controller;

import org.example.Entity.Credit;
import org.example.Service.CreditServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class CreditController {



    private final CreditServices creditServices;

    @Autowired
    public CreditController(CreditServices creditServices) {
        this.creditServices = creditServices;
    }

    @GetMapping("/{codCredito}")
    public Credit obtenerCredito(@PathVariable String codCredito) {
        return creditServices.obtenerCreditoPorCodigo(codCredito);
    }

    //CargarConsumo
    @PostMapping("/{codCredito}/consumo")
    public Credit cargarConsumo(@PathVariable String codCredito, @RequestParam double monto) {
        return creditServices.cargarConsumo(codCredito, monto);
    }

    //Consultar Saldos Diponibles
    @GetMapping("/{codCredito}/saldo")
    public double consultarSaldoDisponible(@PathVariable String codCredito) {
        return creditServices.consultarSaldoDisponible(codCredito);
    }

    //Operaciones CRUD
    @GetMapping
    public List<Credit> obtenerTodosLosCreditos() {
        return creditServices.obtenerTodosLosCreditos();
    }
    @PostMapping
    public Credit agregarCredito(@RequestBody Credit credito) {
        return creditServices.agregarCredito(credito);
    }

    @PutMapping("/{codCredito}")
    public Credit actualizarCredito(@PathVariable String codCredito, @RequestBody Credit credito) {
        return creditServices.actualizarCredito(codCredito, credito);
    }

    @DeleteMapping("/{codCredito}")
    public void eliminarCredito(@PathVariable String codCredito) {
        creditServices.eliminarCredito(codCredito);
    }


}
