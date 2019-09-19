package com.challenge.sociotorcedor.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.challenge.sociotorcedor.dto.ClienteDto;
import com.challenge.sociotorcedor.exception.RestResponseEntityExceptionHandler;
import com.challenge.sociotorcedor.repository.ClienteRepository;
import com.challenge.sociotorcedor.service.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController controller;

    @Mock
    private ClienteRepository repository;
    
    @Mock
    private ClienteService service;

    @InjectMocks
    private RestResponseEntityExceptionHandler exceptionHandler;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(exceptionHandler).build();
    }
    
    @Test
    public void create() throws Exception {
        String json = "{\"nomeCompleto\":\"Cliente de Teste\", " +
                "\"email\":\"cliente_teste@gmail.com\", " +
                "\"dataNascimento\":\"1990-09-01\", " +
                "\"time\": \"Time Teste\"}";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/cliente")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void update() throws Exception {
        String json = "{\"id\": \"1\"," +
                "\"nomeCompleto\":\"Cliente de Teste\", " +
                "\"email\":\"cliente_teste@gmail.com\", " +
                "\"dataNascimento\":\"1990-09-01\", " +
                "\"time\": \"Time Teste\"}";

        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1L);
        clienteDto.setNomeCompleto("Cliente de Teste");
        clienteDto.setEmail("cliente_teste@gmail.com");
        clienteDto.setDataNascimento(LocalDate.of(1990, 9, 1));
        clienteDto.setTime("Time Teste");
        
        Mockito.when(service.atualizar(Mockito.any(ClienteDto.class))).thenReturn(clienteDto);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.put("/cliente")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk()).andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nomeCompleto").value("Cliente de Teste"))
                .andExpect(jsonPath("$.email").value("cliente_teste@gmail.com"))
                .andExpect(jsonPath("$.dataNascimento[0]").value("1990"))
                .andExpect(jsonPath("$.dataNascimento[1]").value("9"))
                .andExpect(jsonPath("$.dataNascimento[2]").value("1"))
                .andExpect(jsonPath("$.time").value("Time Teste"));
    }

    @Test
    public void findById() throws Exception {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1L);
        clienteDto.setNomeCompleto("Teste da Silva");
        clienteDto.setEmail("teste_silva@gmail.com.br");
        clienteDto.setDataNascimento(LocalDate.of(1990, 9, 1));
        clienteDto.setTime("Time Teste");
        clienteDto.setIdTime(1L);

        Mockito.when(service.buscarPorId(Mockito.anyLong())).thenReturn(clienteDto);
        
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/cliente/list/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.nomeCompleto").value("Teste da Silva"))
                .andExpect(jsonPath("$.email").value("teste_silva@gmail.com.br"))
                .andExpect(jsonPath("$.dataNascimento[0]").value("1990"))
                .andExpect(jsonPath("$.dataNascimento[1]").value("9"))
                .andExpect(jsonPath("$.dataNascimento[2]").value("1"))
                .andExpect(jsonPath("$.time").value("Time Teste"))
                .andExpect(jsonPath("$.idTime").value("1"));
    }
    
}