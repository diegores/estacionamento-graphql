package br.com.estacionamento.application.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VeiculoDTOTest {

    @Test
    public void testVeiculoDTOConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String marca = "Honda";
        String modelo = "CB500";
        String cor = "Verde";
        String placa = "XYZ-1234";
        String tipo = "MOTO";
        boolean estaEstacionado = true;
        EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();
        estabelecimento.setId(2L);
        estabelecimento.setNome("Estacionamento Centro");

        // Act
        VeiculoDTO veiculoDTO = new VeiculoDTO(id, marca, modelo, cor, placa, tipo, estaEstacionado, estabelecimento);

        // Assert
        assertThat(veiculoDTO.getId()).isEqualTo(id);
        assertThat(veiculoDTO.getMarca()).isEqualTo(marca);
        assertThat(veiculoDTO.getModelo()).isEqualTo(modelo);
        assertThat(veiculoDTO.getCor()).isEqualTo(cor);
        assertThat(veiculoDTO.getPlaca()).isEqualTo(placa);
        assertThat(veiculoDTO.getTipo()).isEqualTo(tipo);
        assertThat(veiculoDTO.isEstaEstacionado()).isEqualTo(estaEstacionado);
        assertThat(veiculoDTO.getEstabelecimento()).isEqualTo(estabelecimento);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        VeiculoDTO veiculoDTO = new VeiculoDTO();

        // Act
        veiculoDTO.setId(1L);
        veiculoDTO.setMarca("Yamaha");
        veiculoDTO.setModelo("MT-07");
        veiculoDTO.setCor("Azul");
        veiculoDTO.setPlaca("ABC-9876");
        veiculoDTO.setTipo("MOTO");
        veiculoDTO.setEstaEstacionado(false);
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(3L);
        estabelecimentoDTO.setNome("Estacionamento Shopping");
        veiculoDTO.setEstabelecimento(estabelecimentoDTO);

        // Assert
        assertThat(veiculoDTO.getId()).isEqualTo(1L);
        assertThat(veiculoDTO.getMarca()).isEqualTo("Yamaha");
        assertThat(veiculoDTO.getModelo()).isEqualTo("MT-07");
        assertThat(veiculoDTO.getCor()).isEqualTo("Azul");
        assertThat(veiculoDTO.getPlaca()).isEqualTo("ABC-9876");
        assertThat(veiculoDTO.getTipo()).isEqualTo("MOTO");
        assertThat(veiculoDTO.isEstaEstacionado()).isFalse();
        assertThat(veiculoDTO.getEstabelecimento()).isEqualTo(estabelecimentoDTO);
    }
}