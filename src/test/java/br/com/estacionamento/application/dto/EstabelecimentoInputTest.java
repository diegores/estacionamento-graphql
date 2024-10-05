package br.com.estacionamento.application.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EstabelecimentoInputTest {

    @Test
    public void testEstabelecimentoInputConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String nome = "Estacionamento Central";
        String cnpj = "12.345.678/0001-90";
        String endereco = "Rua Principal, 123";
        String telefone = "(11) 98765-4321";
        int vagasMotos = 10;
        int vagasCarros = 20;

        EstabelecimentoInput estabelecimentoInput = new EstabelecimentoInput(id, nome, cnpj, endereco, telefone, vagasMotos, vagasCarros);

        // Act
        Long retrievedId = estabelecimentoInput.getId();
        String retrievedNome = estabelecimentoInput.getNome();
        String retrievedCnpj = estabelecimentoInput.getCnpj();
        String retrievedEndereco = estabelecimentoInput.getEndereco();
        String retrievedTelefone = estabelecimentoInput.getTelefone();
        int retrievedVagasMotos = estabelecimentoInput.getVagasMotos();
        int retrievedVagasCarros = estabelecimentoInput.getVagasCarros();

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
        EstabelecimentoInput estabelecimentoInput = new EstabelecimentoInput();

        // Act
        estabelecimentoInput.setId(1L);
        estabelecimentoInput.setNome("Estacionamento A");
        estabelecimentoInput.setCnpj("12.345.678/0001-90");
        estabelecimentoInput.setEndereco("Avenida Brasil, 100");
        estabelecimentoInput.setTelefone("(11) 98765-4321");
        estabelecimentoInput.setVagasMotos(5);
        estabelecimentoInput.setVagasCarros(10);

        // Assert
        assertThat(estabelecimentoInput.getId()).isEqualTo(1L);
        assertThat(estabelecimentoInput.getNome()).isEqualTo("Estacionamento A");
        assertThat(estabelecimentoInput.getCnpj()).isEqualTo("12.345.678/0001-90");
        assertThat(estabelecimentoInput.getEndereco()).isEqualTo("Avenida Brasil, 100");
        assertThat(estabelecimentoInput.getTelefone()).isEqualTo("(11) 98765-4321");
        assertThat(estabelecimentoInput.getVagasMotos()).isEqualTo(5);
        assertThat(estabelecimentoInput.getVagasCarros()).isEqualTo(10);
    }
}
