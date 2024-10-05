package br.com.estacionamento.application.service;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.mapper.EstabelecimentoMapper;
import br.com.estacionamento.application.mapper.VeiculoMapper;
import br.com.estacionamento.domain.entity.Estabelecimento;
import br.com.estacionamento.domain.entity.Veiculo;
import br.com.estacionamento.domain.repository.EstabelecimentoRepository;
import br.com.estacionamento.domain.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EstabelecimentoServiceTest {

    @Mock
    private EstabelecimentoRepository estabelecimentoRepository;

    @Mock
    private EstabelecimentoMapper estabelecimentoMapper;

    @Mock
    private VeiculoRepository veiculoRepository;

    @Mock
    private VeiculoMapper veiculoMapper;

    @InjectMocks
    private EstabelecimentoService estabelecimentoService;

    private EstabelecimentoDTO estabelecimentoDTO;
    private Estabelecimento estabelecimento;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Criação de um objeto EstabelecimentoDTO e Estabelecimento para testes
        estabelecimentoDTO = new EstabelecimentoDTO(1L, "Estabelecimento Teste", "12345678000195", "Rua Teste, 123", "(11) 99999-9999", 10, 20, null);
        estabelecimento = new Estabelecimento(1L, "Estabelecimento Teste", "12345678000195", "Rua Teste, 123", "(11) 99999-9999", 10, 20, null);
    }

    @Test
    public void testAddEstabelecimento() {
        // Arrange
        when(estabelecimentoMapper.toEntity(estabelecimentoDTO)).thenReturn(estabelecimento);
        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenReturn(estabelecimento);
        when(estabelecimentoMapper.toDTO(estabelecimento)).thenReturn(estabelecimentoDTO);

        // Act
        EstabelecimentoDTO savedEstabelecimentoDTO = estabelecimentoService.addEstabelecimento(estabelecimentoDTO);

        // Assert
        assertThat(savedEstabelecimentoDTO).isNotNull();
        assertThat(savedEstabelecimentoDTO.getId()).isEqualTo(estabelecimentoDTO.getId());
        assertThat(savedEstabelecimentoDTO.getNome()).isEqualTo(estabelecimentoDTO.getNome());
        verify(estabelecimentoRepository, times(1)).save(any(Estabelecimento.class));
    }

    @Test
    public void testGetEstabelecimentos() {
        // Arrange
        when(estabelecimentoRepository.findAll()).thenReturn(Arrays.asList(estabelecimento));
        when(estabelecimentoMapper.toDTO(estabelecimento)).thenReturn(estabelecimentoDTO);

        // Act
        List<EstabelecimentoDTO> estabelecimentos = estabelecimentoService.getEstabelecimentos();

        // Assert
        assertThat(estabelecimentos).isNotEmpty();
        assertThat(estabelecimentos.size()).isEqualTo(1);
        assertThat(estabelecimentos.get(0).getId()).isEqualTo(estabelecimentoDTO.getId());
        verify(estabelecimentoRepository, times(1)).findAll();
    }

    @Test
    public void testGetEstabelecimentoById() {
        // Arrange
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));
        when(estabelecimentoMapper.toDTO(estabelecimento)).thenReturn(estabelecimentoDTO);

        // Act
        Optional<EstabelecimentoDTO> foundEstabelecimento = estabelecimentoService.getEstabelecimentoById(1L);

        // Assert
        assertThat(foundEstabelecimento).isPresent();
        assertThat(foundEstabelecimento.get().getId()).isEqualTo(estabelecimentoDTO.getId());
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateEstabelecimento() {
        // Arrange
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.of(estabelecimento));
        when(estabelecimentoMapper.toEntity(estabelecimentoDTO)).thenReturn(estabelecimento);
        when(estabelecimentoRepository.save(any(Estabelecimento.class))).thenReturn(estabelecimento);
        when(estabelecimentoMapper.toDTO(estabelecimento)).thenReturn(estabelecimentoDTO);

        // Act
        EstabelecimentoDTO updatedEstabelecimentoDTO = estabelecimentoService.updateEstabelecimento(estabelecimentoDTO);

        // Assert
        assertThat(updatedEstabelecimentoDTO).isNotNull();
        assertThat(updatedEstabelecimentoDTO.getId()).isEqualTo(estabelecimentoDTO.getId());
        verify(estabelecimentoRepository, times(1)).findById(1L);
        verify(estabelecimentoRepository, times(1)).save(any(Estabelecimento.class));
    }

    @Test
    public void testDeleteEstabelecimento() {
        // Arrange
        when(estabelecimentoRepository.existsById(1L)).thenReturn(true);
        when(veiculoRepository.findByEstabelecimentoId(1L)).thenReturn(Arrays.asList(new Veiculo()));

        // Act
        boolean result = estabelecimentoService.deleteEstabelecimento(1L);

        // Assert
        assertThat(result).isTrue();
        verify(estabelecimentoRepository, times(1)).deleteById(1L);
        verify(veiculoRepository, times(1)).deleteAll(anyList());
    }

    @Test
    public void testDeleteEstabelecimentoNotFound() {
        // Arrange
        when(estabelecimentoRepository.existsById(1L)).thenReturn(false);

        // Act
        boolean result = estabelecimentoService.deleteEstabelecimento(1L);

        // Assert
        assertThat(result).isFalse();
        verify(estabelecimentoRepository, never()).deleteById(1L);
        verify(veiculoRepository, never()).deleteAll(anyList());
    }

    @Test
    public void testUpdateEstabelecimentoNotFound() {
        // Arrange
        when(estabelecimentoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
            estabelecimentoService.updateEstabelecimento(estabelecimentoDTO);
        });

        assertThat(exception.getMessage()).isEqualTo("Estabelecimento não encontrado para o ID: 1");
        verify(estabelecimentoRepository, times(1)).findById(1L);
    }
}

