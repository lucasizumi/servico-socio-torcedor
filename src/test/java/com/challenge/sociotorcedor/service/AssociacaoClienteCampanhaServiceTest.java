package com.challenge.sociotorcedor.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.challenge.sociotorcedor.dto.CampanhaDto;
import com.challenge.sociotorcedor.dto.ClienteDto;

@RunWith(MockitoJUnitRunner.class)
public class AssociacaoClienteCampanhaServiceTest {

	@Mock
    private CampanhaService campanhaService;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private AssociacaoClienteCampanhaService service;
	
    @Test
    public void associarClienteCampanhaNovoCliente() {
        ClienteDto clienteDto = Mockito.spy(ClienteDto.class);
        CampanhaDto campanhaDto = Mockito.mock(CampanhaDto.class);
        
        Long campanhaId = 1L;
        Mockito.when(campanhaDto.getId()).thenReturn(campanhaId);
        
        List<CampanhaDto> campanhas = Arrays.asList(campanhaDto);
        Mockito.when(campanhaService.buscarCampanhasPorTime(clienteDto.getTime())).thenReturn(campanhas);

        service.associarCadastroClienteCampanha(clienteDto);

        Mockito.verify(clienteService, Mockito.times(1)).atualizarClienteCampanha(clienteDto);
        Assert.assertFalse(clienteDto.getCampanhas().isEmpty());
        Assert.assertEquals(clienteDto.getCampanhas().size(), 1);

        Long value = clienteDto.getCampanhas().get(0);
        Assert.assertEquals(value, campanhaId);
    }
    
}
