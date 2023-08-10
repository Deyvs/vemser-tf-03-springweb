package br.com.dbc.vemser.ecommerce.dto.endereco;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCreateDTO {

    @Schema(description = "ID da pessoa associada ao endereço", required = true)
    private Integer idCliente;

    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    @Schema(description = "Logradouro do endereço", required = true, example = "Rua A")
    private String logradouro;

    @NotNull(message = "O número não pode ser nulo")
    @Schema(description = "Número do endereço", required = true, example = "123")
    private Integer numero;

    private String complemento;

    @NotNull(message = "O CEP não pode ser vazio ou nulo")
    @Size(max = 8, message = "O CEP deve conter no máximo 8 caracteres")
    @Schema(description = "CEP do endereço", required = true, example = "12345678")
    private String cep;

    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    @Schema(description = "Cidade do endereço", required = true, example = "Florianópolis")
    private String cidade;

    @NotNull(message = "O estado não pode ser nulo")
    @Schema(description = "Cidade do endereço", required = true, example = "SC")
    private String estado;

    @NotBlank(message = "O Bairro não pode ser vazio ou nulo")
    @Size(max = 50, message = "O Bairro deve conter no máximo 50 caracteres")
    @Schema(description = "Bairro do endereço", required = true, example = "Bairro Qualquer")
    private String bairro;


}