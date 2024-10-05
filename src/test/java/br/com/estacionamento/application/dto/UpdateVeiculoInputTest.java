package br.com.estacionamento.application.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateVeiculoInputTest {

    @Test
    public void testUpdateVeiculoInputConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String marca = "Toyota";
        String modelo = "Corolla";
        String cor = "Prata";
        String placa = "ABC-1234";
        String tipo = "CARRO";
        boolean estaEstacionado = true;
        EstabelecimentoDTO estabelecimento = new EstabelecimentoDTO();
        estabelecimento.setId(2L);
        estabelecimento.setNome("Estacionamento Central");

        UpdateVeiculoInput updateVeiculoInput = new UpdateVeiculoInput(id, marca, modelo, cor, placa, tipo, estaEstacionado, estabelecimento);

        // Act
        Long retrievedId = updateVeiculoInput.getId();
        String retrievedMarca = updateVeiculoInput.getMarca();
        String retrievedModelo = updateVeiculoInput.getModelo();
        String retrievedCor = updateVeiculoInput.getCor();
        String retrievedPlaca = updateVeiculoInput.getPlaca();
        String retrievedTipo = updateVeiculoInput.getTipo();
        boolean retrievedEstaEstacionado = updateVeiculoInput.isEstaEstacionado();
        EstabelecimentoDTO retrievedEstabelecimento = updateVeiculoInput.getEstabelecimento();

        // Assert
        assertThat(retrievedId).isEqualTo(id);
        assertThat(retrievedMarca).isEqualTo(marca);
        assertThat(retrievedModelo).isEqualTo(modelo);
        assertThat(retrievedCor).isEqualTo(cor);
        assertThat(retrievedPlaca).isEqualTo(placa);
        assertThat(retrievedTipo).isEqualTo(tipo);
        assertThat(retrievedEstaEstacionado).isEqualTo(estaEstacionado);
        assertThat(retrievedEstabelecimento).isEqualTo(estabelecimento);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        UpdateVeiculoInput updateVeiculoInput = new UpdateVeiculoInput();

        // Act
        updateVeiculoInput.setId(1L);
        updateVeiculoInput.setMarca("Honda");
        updateVeiculoInput.setModelo("Civic");
        updateVeiculoInput.setCor("Preto");
        updateVeiculoInput.setPlaca("XYZ-9876");
        updateVeiculoInput.setTipo("CARRO");
        updateVeiculoInput.setEstaEstacionado(false);
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(3L);
        estabelecimentoDTO.setNome("Estacionamento do Shopping");
        updateVeiculoInput.setEstabelecimento(estabelecimentoDTO);

        // Assert
        assertThat(updateVeiculoInput.getId()).isEqualTo(1L);
        assertThat(updateVeiculoInput.getMarca()).isEqualTo("Honda");
        assertThat(updateVeiculoInput.getModelo()).isEqualTo("Civic");
        assertThat(updateVeiculoInput.getCor()).isEqualTo("Preto");
        assertThat(updateVeiculoInput.getPlaca()).isEqualTo("XYZ-9876");
        assertThat(updateVeiculoInput.getTipo()).isEqualTo("CARRO");
        assertThat(updateVeiculoInput.isEstaEstacionado()).isFalse();
        assertThat(updateVeiculoInput.getEstabelecimento()).isEqualTo(estabelecimentoDTO);
    }
}