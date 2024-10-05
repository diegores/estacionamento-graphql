package br.com.estacionamento.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VeiculoTest {

    private Veiculo veiculo;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
    }

    @Test
    void testVeiculoAttributes() {
        veiculo.setId(1L);
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setCor("Preto");
        veiculo.setPlaca("XYZ-1234");
        veiculo.setTipo("CARRO");
        veiculo.setEstabelecimento(new Estabelecimento(1L, "Estacionamento A", "12.345.678/0001-90",
                "Rua A, 123", "1234-5678", 10, 20, null));
        veiculo.setEstaEstacionado(true);

        assertThat(veiculo.getId()).isEqualTo(1L);
        assertThat(veiculo.getMarca()).isEqualTo("Toyota");
        assertThat(veiculo.getModelo()).isEqualTo("Corolla");
        assertThat(veiculo.getCor()).isEqualTo("Preto");
        assertThat(veiculo.getPlaca()).isEqualTo("XYZ-1234");
        assertThat(veiculo.getTipo()).isEqualTo("CARRO");
        assertThat(veiculo.getEstabelecimento()).isNotNull();
        assertThat(veiculo.isEstaEstacionado()).isTrue();
    }

    @Test
    void testDefaultConstructor() {
        assertThat(veiculo).isNotNull();
        assertThat(veiculo.getId()).isNull();
        assertThat(veiculo.getMarca()).isNull();
        assertThat(veiculo.getModelo()).isNull();
        assertThat(veiculo.getCor()).isNull();
        assertThat(veiculo.getPlaca()).isNull();
        assertThat(veiculo.getTipo()).isNull();
        assertThat(veiculo.getEstabelecimento()).isNull();
        assertThat(veiculo.isEstaEstacionado()).isFalse();
    }

    @Test
    void testAllArgsConstructor() {
        Estabelecimento estabelecimento = new Estabelecimento(1L, "Estacionamento B", "12.345.678/0001-90",
                "Rua B, 456", "8765-4321", 15, 25, null);
        Veiculo veiculoCompleto = new Veiculo(1L, "Honda", "Civic", "Azul", "ABC-1234",
                "CARRO", estabelecimento, true);

        assertThat(veiculoCompleto.getId()).isEqualTo(1L);
        assertThat(veiculoCompleto.getMarca()).isEqualTo("Honda");
        assertThat(veiculoCompleto.getModelo()).isEqualTo("Civic");
        assertThat(veiculoCompleto.getCor()).isEqualTo("Azul");
        assertThat(veiculoCompleto.getPlaca()).isEqualTo("ABC-1234");
        assertThat(veiculoCompleto.getTipo()).isEqualTo("CARRO");
        assertThat(veiculoCompleto.getEstabelecimento()).isNotNull();
        assertThat(veiculoCompleto.isEstaEstacionado()).isTrue();
    }
}