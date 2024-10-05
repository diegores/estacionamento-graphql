package br.com.estacionamento.application.mapper;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.EstabelecimentoInput;
import br.com.estacionamento.domain.entity.Estabelecimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class EstabelecimentoMapperTest {

    private EstabelecimentoMapper estabelecimentoMapper;

    @BeforeEach
    public void setUp() {
        // Cria uma nova instância do mapper
        estabelecimentoMapper = Mappers.getMapper(EstabelecimentoMapper.class);
    }

    @Test
    public void testToEntity() {
        // Arrange
        EstabelecimentoDTO dto = new EstabelecimentoDTO();
        dto.setId(1L);
        dto.setNome("Estacionamento Central");
        dto.setCnpj("12.345.678/0001-95");
        dto.setEndereco("Rua Principal, 123");
        dto.setTelefone("1234-5678");
        dto.setVagasMotos(10);
        dto.setVagasCarros(20);

        // Act
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(dto);

        // Assert
        assertThat(estabelecimento).isNotNull();
        assertThat(estabelecimento.getId()).isEqualTo(dto.getId());
        assertThat(estabelecimento.getNome()).isEqualTo(dto.getNome());
        assertThat(estabelecimento.getCnpj()).isEqualTo(dto.getCnpj());
        assertThat(estabelecimento.getEndereco()).isEqualTo(dto.getEndereco());
        assertThat(estabelecimento.getTelefone()).isEqualTo(dto.getTelefone());
        assertThat(estabelecimento.getVagasMotos()).isEqualTo(dto.getVagasMotos());
        assertThat(estabelecimento.getVagasCarros()).isEqualTo(dto.getVagasCarros());
    }

    @Test
    public void testToDTOFromEntity() {
        // Arrange
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(2L);
        estabelecimento.setNome("Estacionamento Sul");
        estabelecimento.setCnpj("98.765.432/0001-01");
        estabelecimento.setEndereco("Avenida do Sul, 456");
        estabelecimento.setTelefone("9876-5432");
        estabelecimento.setVagasMotos(5);
        estabelecimento.setVagasCarros(15);

        // Act
        EstabelecimentoDTO dto = estabelecimentoMapper.toDTO(estabelecimento);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(estabelecimento.getId());
        assertThat(dto.getNome()).isEqualTo(estabelecimento.getNome());
        assertThat(dto.getCnpj()).isEqualTo(estabelecimento.getCnpj());
        assertThat(dto.getEndereco()).isEqualTo(estabelecimento.getEndereco());
        assertThat(dto.getTelefone()).isEqualTo(estabelecimento.getTelefone());
        assertThat(dto.getVagasMotos()).isEqualTo(estabelecimento.getVagasMotos());
        assertThat(dto.getVagasCarros()).isEqualTo(estabelecimento.getVagasCarros());
    }

    @Test
    public void testToDTOFromInput() {
        // Arrange
        EstabelecimentoInput input = new EstabelecimentoInput();
        input.setId(3L);
        input.setNome("Estacionamento Norte");
        input.setCnpj("11.223.344/0001-55");
        input.setEndereco("Rua do Norte, 789");
        input.setTelefone("1111-2222");
        input.setVagasMotos(12);
        input.setVagasCarros(8);

        // Act
        EstabelecimentoDTO dto = estabelecimentoMapper.toDTO(input);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(input.getId());
        assertThat(dto.getNome()).isEqualTo(input.getNome());
        assertThat(dto.getCnpj()).isEqualTo(input.getCnpj());
        assertThat(dto.getEndereco()).isEqualTo(input.getEndereco());
        assertThat(dto.getTelefone()).isEqualTo(input.getTelefone());
        assertThat(dto.getVagasMotos()).isEqualTo(input.getVagasMotos());
        assertThat(dto.getVagasCarros()).isEqualTo(input.getVagasCarros());
    }
}

