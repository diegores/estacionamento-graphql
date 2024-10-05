package br.com.estacionamento.domain.repository;

import br.com.estacionamento.domain.entity.Estabelecimento;
import br.com.estacionamento.domain.entity.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class VeiculoRepositoryTest {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    private Estabelecimento estabelecimento;

    @BeforeEach
    void setUp() {
        // Limpa os repositórios antes de cada teste
        veiculoRepository.deleteAll();
        estabelecimentoRepository.deleteAll();

        // Cria e salva um novo Estabelecimento
        estabelecimento = new Estabelecimento();
        estabelecimento.setNome("Estacionamento A");
        estabelecimento.setCnpj("12.345.678/0001-90");
        estabelecimento.setEndereco("Rua A, 123");
        estabelecimento.setTelefone("1234-5678");
        estabelecimento.setVagasMotos(10);
        estabelecimento.setVagasCarros(20);
        estabelecimentoRepository.save(estabelecimento);
    }

    @Test
    void testFindByEstabelecimentoId_ShouldReturnListOfVeiculos() {
        // Cria e salva veículos associados ao estabelecimento
        Veiculo veiculo1 = new Veiculo();
        veiculo1.setMarca("Marca 1");
        veiculo1.setModelo("Modelo 1");
        veiculo1.setCor("Preto");
        veiculo1.setPlaca("ABC-1234");
        veiculo1.setTipo("CARRO");
        veiculo1.setEstabelecimento(estabelecimento);
        veiculo1.setEstaEstacionado(false);
        veiculoRepository.save(veiculo1);

        Veiculo veiculo2 = new Veiculo();
        veiculo2.setMarca("Marca 2");
        veiculo2.setModelo("Modelo 2");
        veiculo2.setCor("Branco");
        veiculo2.setPlaca("DEF-5678");
        veiculo2.setTipo("MOTO");
        veiculo2.setEstabelecimento(estabelecimento);
        veiculo2.setEstaEstacionado(true);
        veiculoRepository.save(veiculo2);

        // Busca veículos pelo ID do estabelecimento
        List<Veiculo> veiculos = veiculoRepository.findByEstabelecimentoId(estabelecimento.getId());

        // Verifica se a lista de veículos foi retornada corretamente
        assertThat(veiculos).isNotEmpty();
        assertThat(veiculos.size()).isEqualTo(2);
        assertThat(veiculos).containsExactlyInAnyOrder(veiculo1, veiculo2);
    }

    @Test
    void testFindByEstabelecimentoId_ShouldReturnEmptyList_WhenNoVehiclesExist() {
        // Busca veículos pelo ID do estabelecimento que não tem veículos associados
        List<Veiculo> veiculos = veiculoRepository.findByEstabelecimentoId(estabelecimento.getId());

        // Verifica se a lista está vazia
        assertThat(veiculos).isEmpty();
    }
}