package br.com.estacionamento.infrastructure.graphql;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.UpdateVeiculoInput;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.dto.VeiculoInput;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.application.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

/**
 * Controlador responsável por gerenciar operações relacionadas a veículos via GraphQL.
 */
@Controller
public class VeiculoGraphQL {

    private final VeiculoService veiculoService;
    private final VeiculoMapper veiculoMapper;

    @Autowired
    public VeiculoGraphQL(VeiculoService veiculoService,
                          VeiculoMapper veiculoMapper) {
        this.veiculoService = veiculoService;
        this.veiculoMapper = veiculoMapper;
    }

    /**
     * Recupera um veículo pelo ID.
     *
     * @param id O ID do veículo a ser recuperado.
     * @return Um Optional contendo o DTO do veículo, se encontrado.
     */
    @QueryMapping
    public Optional<VeiculoDTO> getVeiculo(@Argument Long id) {
        return veiculoService.getVeiculo(id);
    }

    /**
     * Recupera todos os veículos registrados no sistema.
     *
     * @return Uma lista de DTOs de todos os veículos.
     */
    @QueryMapping
    public List<VeiculoDTO> getVeiculos() {
        return veiculoService.listVeiculos();
    }

    /**
     * Adiciona um novo veículo ao sistema.
     *
     * @param input O input contendo as informações do veículo a ser adicionado.
     * @return O DTO do veículo salvo.
     */
    @MutationMapping
    public VeiculoDTO saveVeiculo(@Argument VeiculoInput input) {
        VeiculoDTO veiculoDTO = veiculoMapper.toDTO(input);

        EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(input.getEstabelecimentoId());
        veiculoDTO.setEstabelecimento(estabelecimentoDTO);
        veiculoDTO = veiculoService.addVeiculo(veiculoDTO);
        return veiculoDTO;
    }

    /**
     * Atualiza as informações de um veículo existente.
     *
     * @param input O input contendo as informações atualizadas do veículo.
     * @return O DTO do veículo atualizado.
     */
    @MutationMapping
    public VeiculoDTO updateVeiculo(@Argument UpdateVeiculoInput input) {
        // Converter o UpdateVeiculoInput para VeiculoDTO
        VeiculoDTO veiculoDTO = veiculoMapper.toDTO(input);

        if (input.getEstabelecimento() != null) {
            EstabelecimentoDTO estabelecimentoDTO = new EstabelecimentoDTO();
            estabelecimentoDTO.setId(input.getEstabelecimento().getId());
            veiculoDTO.setEstabelecimento(estabelecimentoDTO);
        }
        return veiculoService.updateVeiculo(veiculoDTO);
    }

    /**
     * Deleta um veículo pelo ID.
     *
     * @param id O ID do veículo a ser deletado.
     */
    @MutationMapping
    public void deleteVeiculo(@Argument Long id) {
        veiculoService.deleteVeiculo(id);
    }
}