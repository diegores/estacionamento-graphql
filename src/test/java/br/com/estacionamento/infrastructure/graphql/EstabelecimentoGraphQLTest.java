package br.com.estacionamento.infrastructure.graphql;

import br.com.estacionamento.application.dto.EstabelecimentoDTO;
import br.com.estacionamento.application.dto.EstabelecimentoInput;
import br.com.estacionamento.application.mapper.EstabelecimentoMapper;
import br.com.estacionamento.application.service.EstabelecimentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class EstabelecimentoGraphQLTest {

    @InjectMocks
    private EstabelecimentoGraphQL estabelecimentoGraphQL;

    @Mock
    private EstabelecimentoService estabelecimentoService;

    @Mock
    private EstabelecimentoMapper estabelecimentoMapper;

    private EstabelecimentoDTO estabelecimentoDTO;
    private EstabelecimentoInput estabelecimentoInput;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estabelecimentoDTO = new EstabelecimentoDTO();
        estabelecimentoDTO.setId(1L);
        estabelecimentoDTO.setNome("Estabelecimento Teste");

        estabelecimentoInput = new EstabelecimentoInput();
        estabelecimentoInput.setNome("Estabelecimento Teste");
    }

    @Test
    void getEstabelecimento_ShouldReturnEstabelecimento_WhenFound() {
        when(estabelecimentoService.getEstabelecimentoById(1L)).thenReturn(Optional.of(estabelecimentoDTO));

        Optional<EstabelecimentoDTO> result = estabelecimentoGraphQL.getEstabelecimento(1L);

        assertNotNull(result);
        assertEquals(estabelecimentoDTO, result.get());
    }

    @Test
    void getEstabelecimento_ShouldReturnEmpty_WhenNotFound() {
        when(estabelecimentoService.getEstabelecimentoById(1L)).thenReturn(Optional.empty());

        Optional<EstabelecimentoDTO> result = estabelecimentoGraphQL.getEstabelecimento(1L);

        assertNotNull(result);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void getEstabelecimentos_ShouldReturnListOfEstabelecimentos() {
        when(estabelecimentoService.getEstabelecimentos()).thenReturn(Collections.singletonList(estabelecimentoDTO));

        var result = estabelecimentoGraphQL.getEstabelecimentos();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(estabelecimentoDTO, result.get(0));
    }

    @Test
    void saveEstabelecimento_ShouldReturnSavedEstabelecimento() {
        when(estabelecimentoMapper.toDTO(estabelecimentoInput)).thenReturn(estabelecimentoDTO);
        when(estabelecimentoService.addEstabelecimento(estabelecimentoDTO)).thenReturn(estabelecimentoDTO);

        EstabelecimentoDTO result = estabelecimentoGraphQL.saveEstabelecimento(estabelecimentoInput);

        assertNotNull(result);
        assertEquals(estabelecimentoDTO, result);
    }

    @Test
    void updateEstabelecimento_ShouldReturnUpdatedEstabelecimento() {
        when(estabelecimentoMapper.toDTO(estabelecimentoInput)).thenReturn(estabelecimentoDTO);
        when(estabelecimentoService.updateEstabelecimento(estabelecimentoDTO)).thenReturn(estabelecimentoDTO);

        EstabelecimentoDTO result = estabelecimentoGraphQL.updateEstabelecimento(estabelecimentoInput);

        assertNotNull(result);
        assertEquals(estabelecimentoDTO, result);
    }

    @Test
    void deleteEstabelecimento_ShouldReturnTrue_WhenDeletedSuccessfully() {
        when(estabelecimentoService.deleteEstabelecimento(1L)).thenReturn(true);

        boolean result = estabelecimentoGraphQL.deleteEstabelecimento(1L);

        assertEquals(true, result);
    }

    @Test
    void deleteEstabelecimento_ShouldReturnFalse_WhenNotFound() {
        when(estabelecimentoService.deleteEstabelecimento(1L)).thenReturn(false);

        boolean result = estabelecimentoGraphQL.deleteEstabelecimento(1L);

        assertEquals(false, result);
    }
}