package hn.unah.proyecto.dtos;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ClienteDTO {

    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;

    private DireccionDTO direccionDTO;;
    
}
