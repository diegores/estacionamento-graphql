package br.com.estacionamento.application.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EstabelecimentoDTOTest {

    @Test
    public void testEstabelecimentoDTOConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String nome = "Estacionamento Central";
        String cnpj = "12.345.678/0001-90";
        String endereco = "Rua Principal, 123";
        String telefone = "(11) 98765-4321";
        int vagasMotos = 10;
        int vagasCarros = 20;

        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO(id, nome, cnpj, endereco, telefone, vagasMotos, vagasCarros, null);

        // Act
        Long retrievedId = estabelecimentoDTO.getId();
        String retrievedNome = estabelecimentoDTO.getNome();
        String retrievedCnpj = estabelecimentoDTO.getCnpj();
        String retrievedEndereco = estabelecimentoDTO.getEndereco();
        String retrievedTelefone = estabelecimentoDTO.getTelefone();
        int retrievedVagasMotos = estabelecimentoDTO.getVagasMotos();
        int retrievedVagasCarros = estabelecimentoDTO.getVagasCarros();

        // Assert
        assertThat(retrievedId).isEqualTo(id);
        assertThat(retrievedNome).isEqualTo(nome);
        assertThat(retrievedCnpj).isEqualTo(cnpj);
        assertThat(retrievedEndereco).isEqualTo(endereco);
        assertThat(retrievedTelefone).isEqualTo(telefone);
        assertThat(retrievedVagasMotos).isEqualTo(vagasMotos);
        assertThat(retrievedVagasCarros).isEqualTo(vagasCarros);
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();

        // Act
        estabelecimentoDTO.setId(1L);
        estabelecimentoDTO.setNome("Estacionamento A");
        estabelecimentoDTO.setCnpj("12.345.678/0001-90");
        estabelecimentoDTO.setEndereco("Avenida Brasil, 100");
        estabelecimentoDTO.setTelefone("(11) 98765-4321");
        estabelecimentoDTO.setVagasMotos(5);
        estabelecimentoDTO.setVagasCarros(10);

        // Assert
        assertThat(estabelecimentoDTO.getId()).isEqualTo(1L);
        assertThat(estabelecimentoDTO.getNome()).isEqualTo("Estacionamento A");
        assertThat(estabelecimentoDTO.getCnpj()).isEqualTo("12.345.678/0001-90");
        assertThat(estabelecimentoDTO.getEndereco()).isEqualTo("Avenida Brasil, 100");
        assertThat(estabelecimentoDTO.getTelefone()).isEqualTo("(11) 98765-4321");
        assertThat(estabelecimentoDTO.getVagasMotos()).isEqualTo(5);
        assertThat(estabelecimentoDTO.getVagasCarros()).isEqualTo(10);
    }

    @Test
    public void testVeiculosAssociation() {
        // Arrange
        VeiculoDTO veiculo1 = new VeiculoDTO(/* preencha com os parâmetros necessários */);
        VeiculoDTO veiculo2 = new VeiculoDTO(/* preencha com os parâmetros necessários */);
        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setVeiculos(Arrays.asList(veiculo1, veiculo2));

        // Act
        List<VeiculoDTO> veiculos = estabelecimentoDTO.getVeiculos();

        // Assert
        assertThat(veiculos).hasSize(2);
        assertThat(veiculos).containsExactly(veiculo1, veiculo2);
    }
}
