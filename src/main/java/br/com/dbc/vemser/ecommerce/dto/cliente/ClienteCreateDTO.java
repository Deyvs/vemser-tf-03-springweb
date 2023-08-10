package br.com.dbc.vemser.ecommerce.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {

    @NotBlank
    @Size(min = 2, message = "O nome deve ter no minimo 2 caracteres")
    @Schema(description = "Nome da Pessoa", example = "Maria Matos", required = true)
    private String nome;

    @NotNull(message = "O número não pode ser nulo")
    @Size(min = 9, max = 13, message = "O número precisa ter no mínimo 9 caracteres e no máximo 13")
    @Schema(description = "Número de contato", required = true, example = "5548999008877")
    private String telefone;

    @Email
    @Schema(description = "Endereço de e-mail da pessoa", example = "nome.sobrenome@mail.com")
    private String email;

    @CPF
    @NotNull
    @Schema(description = "CPF da pessoa", example = "123.456.789-09")
    private String cpf;

}