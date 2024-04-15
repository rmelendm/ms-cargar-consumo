package org.example.Service;
import org.example.Entity.Credit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditServices {
    private final List<Credit> creditos = new ArrayList<>();

    public CreditServices() {
        creditos.add(new Credit("001", "Personal", 1000.0, 200.0, "Activo"));
        creditos.add(new Credit("002", "Empresarial", 5000.0, 1000.0, "Activo"));
        creditos.add(new Credit("003", "Personal", 200000.0, 50000.0, "Activo"));
    }
    public Credit obtenerCreditoPorCodigo(String codigo) {
        return creditos.stream()
                .filter(credito -> credito.getCod_cred().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("El crédito no existe"));
    }

    public Credit cargarConsumo(String codigo, double monto) {
        return creditos.stream()
                .filter(credito -> credito.getCod_cred().equals(codigo))
                .findFirst()
                .map(credito -> {
                    double nuevoConsumo = credito.getConsumo_actual() + monto;
                    if (nuevoConsumo <= credito.getLimite_cred()) {
                        credito.setConsumo_actual(nuevoConsumo);
                        return credito;
                    } else {
                        throw new IllegalArgumentException("El monto del consumo supera el límite del crédito");
                    }
                })
                .orElseThrow(() -> new IllegalArgumentException("El crédito no existe"));
    }

    // Método para calcular el saldo disponible de un crédito
    public double consultarSaldoDisponible(String codigoCredito) {
        return creditos.stream()
                .filter(credito -> credito.getCod_cred().equals(codigoCredito))
                .findFirst()
                .map(credito -> credito.getLimite_cred() - credito.getConsumo_actual())
                .orElseThrow(() -> new IllegalArgumentException("El crédito no existe"));

    }


    public List<Credit> obtenerTodosLosCreditos() {
        return creditos;
    }

    public Credit agregarCredito(Credit credito) {
        creditos.add(credito);
        return credito;
    }

    public Credit actualizarCredito(String codigo, Credit newCredito) {
        creditos.stream()
                .filter(credito -> credito.getCod_cred().equals(codigo))
                .findFirst()
                .ifPresentOrElse(
                        creditoExistente -> {
                            creditoExistente.setTipo_cred(newCredito.getTipo_cred());
                            creditoExistente.setLimite_cred(newCredito.getLimite_cred());
                            creditoExistente.setConsumo_actual(newCredito.getConsumo_actual());
                            creditoExistente.setEstado(newCredito.getEstado());
                        },
                        () -> {
                            throw new IllegalArgumentException("El crédito no existe");
                        }
                );

        return obtenerCreditoPorCodigo(codigo);
    }

    public void eliminarCredito(String codigo) {
        creditos.removeIf(credito -> credito.getCod_cred().equals(codigo));
    }


}

