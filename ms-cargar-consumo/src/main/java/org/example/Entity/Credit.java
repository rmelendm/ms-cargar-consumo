package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Credit {
    String cod_cred;
    String tipo_cred;
    double limite_cred;
    double consumo_actual;
    String estado;
}
