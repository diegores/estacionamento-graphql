package br.com.estacionamento.application.mapper;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.EstabelecimentoInput;
import br.com.estacionamento.domain.entity.Estabelecimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {

    Estabelecimento toEntity(EstabelecimentoDTO dto);

    @Mapping(target = "veiculos", ignore = true) // Ignora a lista de veículos na conversão de entidade para DTO
    EstabelecimentoDTO toDTO(Estabelecimento estabelecimento);

    EstabelecimentoDTO toDTO(EstabelecimentoInput input);

}
