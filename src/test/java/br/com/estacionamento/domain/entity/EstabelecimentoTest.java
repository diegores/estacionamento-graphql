package br.com.estacionamento.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstabelecimentoTest {

    private Estabelecimento estabelecimento;

    @BeforeEach
    void setUp() {
        // Inicializa a instância da classe Estabelecimento antes de cada teste
        estabelecimento = new Estabelecimento();
    }

    @Test
    void testEstabelecimentoAttributes() {
        // Define valores
        estabelecimento.setId(1L);
        estabelecimento.setNome("Estacionamento A");
        estabelecimento.setCnpj("12.345.678/0001-90");
        estabelecimento.setEndereco("Rua A, 123");
        estabelecimento.setTelefone("1234-5678");
        estabelecimento.setVagasMotos(10);
        estabelecimento.setVagasCarros(20);

        // Verifica se os valores foram atribuídos corretamente
        assertEquals(1L, estabelecimento.getId());
        assertEquals("Estacionamento A", estabelecimento.getNome());
        assertEquals("12.345.678/0001-90", estabelecimento.getCnpj());
        assertEquals("Rua A, 123", estabelecimento.getEndereco());
        assertEquals("1234-5678", estabelecimento.getTelefone());
        assertEquals(10, estabelecimento.getVagasMotos());
        assertEquals(20, estabelecimento.getVagasCarros());
    }

    @Test
    void testDefaultConstructor() {
        // Verifica se a instância é criada com valores padrão
        assertNotNull(estabelecimento);
        assertNull(estabelecimento.getId());
        assertNull(estabelecimento.getNome());
        assertNull(estabelecimento.getCnpj());
        assertNull(estabelecimento.getEndereco());
        assertNull(estabelecimento.getTelefone());
        assertEquals(0, estabelecimento.getVagasMotos());
        assertEquals(0, estabelecimento.getVagasCarros());
    }

    @Test
    void testAllArgsConstructor() {
        // Cria uma instância utilizando o construtor com todos os argumentos
        Estabelecimento estabelecimentoCompleto = new Estabelecimento(1L, "Estacionamento B", "12.345.678/0001-90",
                "Rua B, 456", "8765-4321", 15, 25, null);

        // Verifica se os valores foram atribuídos corretamente
        assertEquals(1L, estabelecimentoCompleto.getId());
        assertEquals("Estacionamento B", estabelecimentoCompleto.getNome());
        assertEquals("12.345.678/0001-90", estabelecimentoCompleto.getCnpj());
        assertEquals("Rua B, 456", estabelecimentoCompleto.getEndereco());
        assertEquals("8765-4321", estabelecimentoCompleto.getTelefone());
        assertEquals(15, estabelecimentoCompleto.getVagasMotos());
        assertEquals(25, estabelecimentoCompleto.getVagasCarros());
    }
}

