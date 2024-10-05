package br.com.estacionamento.application.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class VeiculoInputTest {

    @Test
    public void testVeiculoInputConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String marca = "Fiat";
        String modelo = "Palio";
        String cor = "Vermelho";
        String placa = "ABC-1234";
        String tipo = "CARRO";
        boolean estaEstacionado = true;
        Long estabelecimentoId = 2L;

        // Act
        VeiculoInput veiculoInput = new VeiculoInput(id, marca, modelo, cor, placa, tipo, estaEstacionado, estabelecimentoId);

        // Assert
        assertThat(veiculoInput.getId()).isEqualTo(id);
        assertThat(veiculoInput.getMarca()).isEqualTo(marca);
        assertThat(veiculoInput.getModelo()).isEqualTo(modelo);
        assertThat(veiculoInput.getCor()).isEqualTo(cor);
        assertThat(veiculoInput.getPlaca()).isEqualTo(placa);
        assertThat(veiculoInput.getTipo()).isEqualTo(tipo);
        assertThat(veiculoInput.isEstaEstacionado()).isEqualTo(estaEstacionado);
        assertThat(veiculoInput.getEstabelecimentoId()).isEqualTo(estabelecimentoId);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        VeiculoInput veiculoInput = new VeiculoInput();

        // Act
        veiculoInput.setId(3L);
        veiculoInput.setMarca("Toyota");
        veiculoInput.setModelo("Corolla");
        veiculoInput.setCor("Preto");
        veiculoInput.setPlaca("XYZ-9876");
        veiculoInput.setTipo("CARRO");
        veiculoInput.setEstaEstacionado(false);
        veiculoInput.setEstabelecimentoId(4L);

        // Assert
        assertThat(veiculoInput.getId()).isEqualTo(3L);
        assertThat(veiculoInput.getMarca()).isEqualTo("Toyota");
        assertThat(veiculoInput.getModelo()).isEqualTo("Corolla");
        assertThat(veiculoInput.getCor()).isEqualTo("Preto");
        assertThat(veiculoInput.getPlaca()).isEqualTo("XYZ-9876");
        assertThat(veiculoInput.getTipo()).isEqualTo("CARRO");
        assertThat(veiculoInput.isEstaEstacionado()).isFalse();
        assertThat(veiculoInput.getEstabelecimentoId()).isEqualTo(4L);
    }
}