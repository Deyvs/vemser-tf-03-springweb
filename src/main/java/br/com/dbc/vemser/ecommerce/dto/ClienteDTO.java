package br.com.dbc.vemser.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO extends ClienteCreateDTO{

    private Integer idCliente;
}
