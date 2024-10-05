package br.com.estacionamento.domain.repository;

import br.com.estacionamento.domain.entity.Estabelecimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EstabelecimentoRepositoryTest {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @BeforeEach
    void setUp() {
        // Limpa o repositório antes de cada teste
        estabelecimentoRepository.deleteAll();
    }

    @Test
    void testSaveAndFindEstabelecimento() {
        // Cria uma nova instância de Estabelecimento
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome("Estacionamento A");
        estabelecimento.setCnpj("12.345.678/0001-90");
        estabelecimento.setEndereco("Rua A, 123");
        estabelecimento.setTelefone("1234-5678");
        estabelecimento.setVagasMotos(10);
        estabelecimento.setVagasCarros(20);

        // Salva o estabelecimento no repositório
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);

        // Busca o estabelecimento pelo ID
        Optional<Estabelecimento> foundEstabelecimento = estabelecimentoRepository.findById(savedEstabelecimento.getId());

        // Verifica se o estabelecimento foi encontrado e se os dados estão corretos
        assertThat(foundEstabelecimento).isPresent();
        assertThat(foundEstabelecimento.get().getNome()).isEqualTo("Estacionamento A");
        assertThat(foundEstabelecimento.get().getCnpj()).isEqualTo("12.345.678/0001-90");
    }

    @Test
    void testDeleteEstabelecimento() {
        // Cria e salva um novo estabelecimento
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setNome("Estacionamento B");
        estabelecimento.setCnpj("12.345.678/0001-91");
        estabelecimento.setEndereco("Rua B, 456");
        estabelecimento.setTelefone("8765-4321");
        estabelecimento.setVagasMotos(5);
        estabelecimento.setVagasCarros(15);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);

        // Deleta o estabelecimento
        estabelecimentoRepository.deleteById(savedEstabelecimento.getId());

        // Verifica se o estabelecimento foi deletado
        Optional<Estabelecimento> foundEstabelecimento = estabelecimentoRepository.findById(savedEstabelecimento.getId());
        assertThat(foundEstabelecimento).isNotPresent();
    }
}