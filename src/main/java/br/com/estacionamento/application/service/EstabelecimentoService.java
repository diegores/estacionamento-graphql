package br.com.estacionamento.application.service;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.mapper.EstabelecimentoMapper;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.domain.entity.Estabelecimento;
import br.com.estacionamento.domain.entity.Veiculo;
import br.com.estacionamento.domain.repository.EstabelecimentoRepository;
import br.com.estacionamento.domain.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstabelecimentoService {


    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoMapper veiculoMapper;

    @Autowired
    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository, EstabelecimentoMapper estabelecimentoMapper, VeiculoRepository veiculoRepository, VeiculoMapper veiculoMapper) {
        this.estabelecimentoRepository = estabelecimentoRepository;
        this.estabelecimentoMapper = estabelecimentoMapper;
        this.veiculoRepository = veiculoRepository;
        this.veiculoMapper = veiculoMapper;
    }

    /**
     * Adiciona um novo estabelecimento ao sistema.
     * @param estabelecimentoDTO O DTO contendo as informações do novo estabelecimento.
     * @return O DTO do estabelecimento salvo.
     */
    public EstabelecimentoDTO addEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        // Mapear o estabelecimento do DTO para a entidade
        Estabelecimento estabelecimento = estabelecimentoMapper.toEntity(estabelecimentoDTO);
        Estabelecimento savedEstabelecimento = estabelecimentoRepository.save(estabelecimento);
        return estabelecimentoMapper.toDTO(savedEstabelecimento);
    }

    /**
     * Lista todos os estabelecimentos cadastrados no sistema.
     * @return Lista de DTOs representando todos os estabelecimentos.
     */
    public List<EstabelecimentoDTO> getEstabelecimentos() {
        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll();
        return estabelecimentos.stream()
                .map(estabelecimentoMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtém um estabelecimento pelo ID.
     * @param id O ID do estabelecimento.
     * @return Um Optional contendo o DTO do estabelecimento, se encontrado.
     */
    public Optional<EstabelecimentoDTO> getEstabelecimentoById(Long id) {
        return estabelecimentoRepository.findById(id)
                .map(estabelecimentoMapper::toDTO);
    }

    /**
     * Atualiza um estabelecimento existente no sistema.
     * @param estabelecimentoDTO O DTO contendo as informações atualizadas do estabelecimento.
     * @return O DTO do estabelecimento atualizado.
     * @throws RuntimeException Se o estabelecimento não for encontrado.
     */
    public EstabelecimentoDTO updateEstabelecimento(EstabelecimentoDTO estabelecimentoDTO) {
        Estabelecimento existingEstabelecimento = estabelecimentoRepository.findById(estabelecimentoDTO.getId())
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado para o ID: " + estabelecimentoDTO.getId()));

        // Atualizar os dados principais do estabelecimento
        existingEstabelecimento.setNome(estabelecimentoDTO.getNome());
        existingEstabelecimento.setCnpj(estabelecimentoDTO.getCnpj());
        existingEstabelecimento.setEndereco(estabelecimentoDTO.getEndereco());
        existingEstabelecimento.setTelefone(estabelecimentoDTO.getTelefone());
        existingEstabelecimento.setVagasMotos(estabelecimentoDTO.getVagasMotos());
        existingEstabelecimento.setVagasCarros(estabelecimentoDTO.getVagasCarros());

        // Atualizar ou adicionar veículos caso estejam presentes no DTO
        if (estabelecimentoDTO.getVeiculos() != null) {
            List<Veiculo> veiculos = estabelecimentoDTO.getVeiculos().stream()
                    .map(veiculoDTO -> {
                        Veiculo veiculo = veiculoMapper.toEntity(veiculoDTO);
                        veiculo.setEstabelecimento(existingEstabelecimento); // Associar ao estabelecimento atualizado
                        return veiculo;
                    }).collect(Collectors.toList());

            // Atualizar veículos no banco de dados
            veiculoRepository.saveAll(veiculos);
            existingEstabelecimento.setVeiculos(veiculos);
        }

        Estabelecimento updatedEstabelecimento = estabelecimentoRepository.save(existingEstabelecimento);
        return estabelecimentoMapper.toDTO(updatedEstabelecimento);
    }

    /**
     * Deleta um estabelecimento pelo ID.
     * @param id O ID do estabelecimento.
     * @return true se a operação de deleção foi bem-sucedida, false se o estabelecimento não existir.
     */
    public boolean deleteEstabelecimento(Long id) {
        if (estabelecimentoRepository.existsById(id)) {
            // Remover os veículos associados antes de deletar o estabelecimento
            List<Veiculo> veiculos = veiculoRepository.findByEstabelecimentoId(id);
            veiculoRepository.deleteAll(veiculos); // Remover todos os veículos associados ao estabelecimento

            estabelecimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
