package br.com.estacionamento.application.service;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.VeiculoDTO;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.domain.entity.Estabelecimento;
import br.com.estacionamento.domain.entity.Veiculo;
import br.com.estacionamento.domain.repository.EstabelecimentoRepository;
import br.com.estacionamento.domain.repository.VeiculoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;
    private Estabelecimento estabelecimento;
    private EstabelecimentoDTO estabelecimentoDTO;
    private VeiculoDTO veiculoDTO;
    private Veiculo veiculo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configuração inicial
        estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        estabelecimento.setVagasMotos(10);
        estabelecimento.setVagasCarros(5);

        estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(1L);
        estabelecimentoDTO.setVagasMotos(10);
        estabelecimentoDTO.setVagasCarros(5);


        veiculoDTO = new VeiculoDTO();
        veiculoDTO.setMarca("Honda");
        veiculoDTO.setModelo("CG 160");
        veiculoDTO.setCor("Preto");
        veiculoDTO.setPlaca("ABC-1234");
        veiculoDTO.setTipo("moto");
        veiculoDTO.setEstabelecimento(new EstabelecimentoDTO());
        veiculoDTO.getEstabelecimento().setId(1L);

        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setCor(veiculoDTO.getCor());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setTipo(veiculoDTO.getTipo());
        veiculo.setEstabelecimento(estabelecimento);
    }

    @Test
    public void testAddVeiculo_Success() {
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));
        when(veiculoMapper.toEntity(veiculoDTO)).thenReturn(veiculo);
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);
        when(veiculoMapper.toDTO(veiculo)).thenReturn(veiculoDTO);

        VeiculoDTO result = veiculoService.addVeiculo(veiculoDTO);

        assertNotNull(result);
        assertEquals(veiculoDTO.getMarca(), result.getMarca());
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }

    @Test
    public void testAddVeiculo_EstabelecimentoNotFound() {
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            veiculoService.addVeiculo(veiculoDTO);
        });

        assertEquals("Estabelecimento não encontrado com ID: 1", exception.getMessage());
    }

    @Test
    public void testUpdateVeiculo_Success() {
        veiculoDTO.setId(1L); // Atribui um ID válido ao DTO
        veiculoDTO.setEstabelecimento(estabelecimentoDTO); // Atribui um ID válido do estabelecimento

        // Mock do estabelecimento
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(1L);
        // Aqui você pode setar outras propriedades do estabelecimento se necessário

        // Configuração do mock
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento)); // Mock do repositório de estabelecimentos
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);
        when(veiculoMapper.toDTO(veiculo)).thenReturn(veiculoDTO);

        // Chamada ao serviço
        VeiculoDTO updatedVeiculoDTO = veiculoService.updateVeiculo(veiculoDTO);

        // Verificações
        assertNotNull(updatedVeiculoDTO);
        assertEquals(veiculoDTO.getMarca(), updatedVeiculoDTO.getMarca());
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
    }



    @Test
    public void testUpdateVeiculo_NotFound() {
        // Define um ID no DTO para simular a atualização
        veiculoDTO.setId(1L);

        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            veiculoService.updateVeiculo(veiculoDTO);
        });

        assertEquals("Veículo não encontrado para o ID: 1", exception.getMessage());
    }


    @Test
    public void testDeleteVeiculo_Success() {
        when(veiculoRepository.existsById(1L)).thenReturn(true);

        veiculoService.deleteVeiculo(1L);

        verify(veiculoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteVeiculo_NotFound() {
        when(veiculoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            veiculoService.deleteVeiculo(1L);
        });

        assertEquals("Veículo não encontrado para o ID: 1", exception.getMessage());
    }
}
