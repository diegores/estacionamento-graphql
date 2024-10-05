package br.com.estacionamento.infrastructure.graphql;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.EstabelecimentoInput;
import br.com.estacionamento.application.dto.VeiculoDTO; // Importar o DTO do veículo
import br.com.estacionamento.application.mapper.EstabelecimentoMapper;
import br.com.estacionamento.application.service.EstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

/**
 * Controlador responsável por gerenciar operações relacionadas aos estabelecimentos via GraphQL.
 */
@Controller
public class EstabelecimentoGraphQL {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private EstabelecimentoMapper estabelecimentoMapper;

    /**
     * Recupera um estabelecimento pelo ID.
     *
     * @param id O ID do estabelecimento a ser recuperado.
     * @return Um Optional contendo o DTO do estabelecimento, se encontrado.
     */
    @QueryMapping
    public Optional<EstabelecimentoDTO> getEstabelecimento(@Argument Long id) {
        return estabelecimentoService.getEstabelecimentoById(id);
    }

    /**
     * Recupera todos os estabelecimentos registrados no sistema.
     *
     * @return Uma lista de DTOs de todos os estabelecimentos.
     */
    @QueryMapping
    public List<EstabelecimentoDTO> getEstabelecimentos() {
        return estabelecimentoService.getEstabelecimentos();
    }

    /**
     * Adiciona um novo estabelecimento ao sistema.
     *
     * @param input O input contendo as informações do estabelecimento a ser adicionado.
     * @return O DTO do estabelecimento salvo.
     */
    @MutationMapping
    public EstabelecimentoDTO saveEstabelecimento(@Argument EstabelecimentoInput input) {
        // Converter o EstabelecimentoInput para EstabelecimentoDTO
        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.toDTO(input);
        return estabelecimentoService.addEstabelecimento(estabelecimentoDTO);
    }

    /**
     * Atualiza as informações de um estabelecimento existente.
     *
     * @param input O input contendo as informações atualizadas do estabelecimento.
     * @return O DTO do estabelecimento atualizado.
     */
    @MutationMapping
    public EstabelecimentoDTO updateEstabelecimento(@Argument EstabelecimentoInput input) {
        // Converter o UpdateEstabelecimentoInput para EstabelecimentoDTO
        EstabelecimentoDTO estabelecimentoDTO = estabelecimentoMapper.toDTO(input);
        return estabelecimentoService.updateEstabelecimento(estabelecimentoDTO);
    }

    /**
     * Deleta um estabelecimento pelo ID.
     *
     * @param id O ID do estabelecimento a ser deletado.
     * @return true se o estabelecimento foi deletado com sucesso, false caso contrário.
     */
    @MutationMapping
    public boolean deleteEstabelecimento(@Argument Long id) {
        return estabelecimentoService.deleteEstabelecimento(id);
    }
}