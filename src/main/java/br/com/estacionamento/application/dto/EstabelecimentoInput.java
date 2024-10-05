package br.com.estacionamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoInput {
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private int vagasMotos;
    private int vagasCarros;
}
