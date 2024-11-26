package hn.unah.proyecto.servicios;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.modelos.Cliente;
import hn.unah.proyecto.dtos.ClienteDTO;
import hn.unah.proyecto.dtos.DireccionDTO;
import hn.unah.proyecto.modelos.Direccion;
import hn.unah.proyecto.repositorios.ClienteRepositorio;

@Service
public class ClienteServicio {
     @Autowired
    private ClienteRepositorio clienteRepositorio;

    
    private ModelMapper modelMapper;
    
    public List<Cliente> obtenerTodos(){
        return clienteRepositorio.findAll();
    }

    public Optional<ClienteDTO> obtenerPorDni(String dni){
        modelMapper = new ModelMapper();
        Optional<Cliente> cliente = clienteRepositorio.findById(dni);
        ClienteDTO clienteDto =  this.modelMapper.map(cliente, ClienteDTO.class);

        Direccion direccion = cliente.get().getDireccion();
        DireccionDTO direccioneDTO = this.modelMapper.map(direccion, DireccionDTO.class);
        clienteDto.setDireccionDTO(direccioneDTO);

        
        return Optional.ofNullable(clienteDto) ;
    }


    public String crearCliente(ClienteDTO nvoCliente){        
        if(this.clienteRepositorio.existsById(nvoCliente.getDni())){
            return "Ya existe el cliente";
        }
        modelMapper = new ModelMapper();        
        Cliente nvoClienteBd = this.modelMapper.map(nvoCliente, Cliente.class);
        
        this.clienteRepositorio.save(nvoClienteBd);
        return "Cliente almacenado satisfactoriamente";
    }

    public String eliminarClientePorId(String id){
        if(!this.clienteRepositorio.existsById(id)){
            return "No existe el cliente";
        }
        this.clienteRepositorio.deleteById(id);
        return "Cliente eliminado satisfactoriamente";
    }

    public String actualizarCliente(String dni, ClienteDTO cliente){
        if(!this.clienteRepositorio.existsById(cliente.getDni())){
            return "No existe el cliente a actualizar";
        }
        modelMapper = new ModelMapper();        

        Cliente clienteActualizar = this.modelMapper.map(cliente, Cliente.class);
        this.clienteRepositorio.save(clienteActualizar);
        return "El cliente: " + cliente.getDni() + " se ha actualizao!";


    }
    
}
