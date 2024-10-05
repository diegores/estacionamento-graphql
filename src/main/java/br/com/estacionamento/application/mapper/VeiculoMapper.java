package br.com.estacionamento.application.mapper;

import br.com.estacionamento.application.dto.UpdateVeiculoInput;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.dto.VeiculoInput;
import br.com.estacionamento.domain.entity.Veiculo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VeiculoMapper {

    Veiculo toEntity(VeiculoDTO dto);

    List<Veiculo> toEntityList(List<VeiculoDTO> dtoList);

    @Mapping(target = "estabelecimento.id", source = "estabelecimentoId")
    VeiculoDTO toDTO(VeiculoInput input);

    VeiculoDTO toDTO(UpdateVeiculoInput input);

    Object toDTO(Veiculo veiculo);
    VeiculoDTO toEntity(Veiculo veiculo);
}
