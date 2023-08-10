package br.com.dbc.vemser.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    private Integer idProduto;
    private String modelo;
    private String tamanho;
    private String cor;
    private String setor;
    private Double valor;

}