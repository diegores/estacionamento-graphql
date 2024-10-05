package br.com.estacionamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;
    private int vagasMotos;
    private int vagasCarros;

    // Lista de veículos associados ao estabelecimento
    private List<VeiculoDTO> veiculos;
}
