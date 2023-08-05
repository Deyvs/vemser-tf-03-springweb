package br.com.dbc.vemser.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    private String nome;
    private String telefone;
    private String email;
    private String cpf;

}
