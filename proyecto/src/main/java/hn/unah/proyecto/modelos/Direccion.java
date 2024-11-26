package hn.unah.proyecto.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="direcciones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {

    private String Departamento;
    private String ciudad;
    private String casa;

    
}
