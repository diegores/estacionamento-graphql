package br.com.estacionamento.application.mapper;

import br.com.estacionamento.application.dto.UpdateVeiculoInput;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.dto.VeiculoInput;
import br.com.estacionamento.domain.entity.Veiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VeiculoMapperTest {

    private VeiculoMapper veiculoMapper;

    @BeforeEach
    public void setUp() {
        // Cria uma nova instância do mapper
        veiculoMapper = Mappers.getMapper(VeiculoMapper.class);
    }

    @Test
    public void testToEntity() {
        // Arrange
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(1L);
        dto.setMarca("Toyota");
        dto.setModelo("Corolla");
        dto.setCor("Preto");
        dto.setPlaca("ABC-1234");
        dto.setTipo("CARRO");
        dto.setEstaEstacionado(true);

        // Act
        Veiculo veiculo = veiculoMapper.toEntity(dto);

        // Assert
        assertThat(veiculo).isNotNull();
        assertThat(veiculo.getId()).isEqualTo(dto.getId());
        assertThat(veiculo.getMarca()).isEqualTo(dto.getMarca());
        assertThat(veiculo.getModelo()).isEqualTo(dto.getModelo());
        assertThat(veiculo.getCor()).isEqualTo(dto.getCor());
        assertThat(veiculo.getPlaca()).isEqualTo(dto.getPlaca());
        assertThat(veiculo.getTipo()).isEqualTo(dto.getTipo());
        assertThat(veiculo.isEstaEstacionado()).isEqualTo(dto.isEstaEstacionado());
    }

    @Test
    public void testToEntityList() {
        // Arrange
        VeiculoDTO dto1 = new VeiculoDTO();
        dto1.setId(1L);
        dto1.setMarca("Toyota");
        dto1.setModelo("Corolla");

        VeiculoDTO dto2 = new VeiculoDTO();
        dto2.setId(2L);
        dto2.setMarca("Honda");
        dto2.setModelo("Civic");

        List<VeiculoDTO> dtoList = Arrays.asList(dto1, dto2);

        // Act
        List<Veiculo> veiculoList = veiculoMapper.toEntityList(dtoList);

        // Assert
        assertThat(veiculoList).isNotNull();
        assertThat(veiculoList).hasSize(2);
        assertThat(veiculoList.get(0).getId()).isEqualTo(dto1.getId());
        assertThat(veiculoList.get(1).getId()).isEqualTo(dto2.getId());
    }

    @Test
    public void testToDTOFromInput() {
        // Arrange
        VeiculoInput input = new VeiculoInput();
        input.setId(3L);
        input.setMarca("Ford");
        input.setModelo("Fiesta");
        input.setCor("Vermelho");
        input.setPlaca("XYZ-9876");
        input.setTipo("CARRO");
        input.setEstaEstacionado(false);
        input.setEstabelecimentoId(1L);

        // Act
        VeiculoDTO dto = veiculoMapper.toDTO(input);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(input.getId());
        assertThat(dto.getMarca()).isEqualTo(input.getMarca());
        assertThat(dto.getModelo()).isEqualTo(input.getModelo());
        assertThat(dto.getCor()).isEqualTo(input.getCor());
        assertThat(dto.getPlaca()).isEqualTo(input.getPlaca());
        assertThat(dto.getTipo()).isEqualTo(input.getTipo());
        assertThat(dto.isEstaEstacionado()).isEqualTo(input.isEstaEstacionado());
        assertThat(dto.getEstabelecimento()).isNotNull();
        assertThat(dto.getEstabelecimento().getId()).isEqualTo(input.getEstabelecimentoId());
    }

    @Test
    public void testToDTOFromUpdateInput() {
        // Arrange
        UpdateVeiculoInput updateInput = new UpdateVeiculoInput();
        updateInput.setId(4L);
        updateInput.setMarca("Chevrolet");
        updateInput.setModelo("Onix");
        updateInput.setCor("Azul");
        updateInput.setPlaca("LMN-4567");
        updateInput.setTipo("CARRO");
        updateInput.setEstaEstacionado(true);

        // Act
        VeiculoDTO dto = veiculoMapper.toDTO(updateInput);

        // Assert
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(updateInput.getId());
        assertThat(dto.getMarca()).isEqualTo(updateInput.getMarca());
        assertThat(dto.getModelo()).isEqualTo(updateInput.getModelo());
        assertThat(dto.getCor()).isEqualTo(updateInput.getCor());
        assertThat(dto.getPlaca()).isEqualTo(updateInput.getPlaca());
        assertThat(dto.getTipo()).isEqualTo(updateInput.getTipo());
        assertThat(dto.isEstaEstacionado()).isEqualTo(updateInput.isEstaEstacionado());
    }
}
