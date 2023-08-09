package br.com.dbc.vemser.ecommerce.dto.endereco;

import br.com.dbc.vemser.ecommerce.entity.enums.TipoEndereco;
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

    private Integer idCliente;
    @NotNull
    private TipoEndereco tipoEndereco;
    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    private String logradouro;
    @NotNull
    private Integer numero;
    private String complemento;
    @NotBlank
    @Size(max = 8, message = "Não pode conter mais que 8 caracteres")
    private String cep;
    @NotBlank
    @Size(max = 250, message = "Não pode conter mais de 250 caracteres")
    private String cidade;
    @NotNull
    private String estado;
    @NotNull
    private String pais;
}
