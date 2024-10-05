package br.com.estacionamento.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String tipo; // "CARRO" ou "MOTO"
    private boolean estaEstacionado;
    private EstabelecimentoDTO estabelecimento;
}
