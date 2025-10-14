package com.example.demo.controller;


import com.example.demo.dto.MonthlyStatementDto;
import com.example.demo.service.MonthlyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MonthlyStatementControllerTest {


    private MockMvc mockMvc;

    @Mock
    private MonthlyService monthlyService;

//    @InjectMocks
//    private MonthlyStatementControllerTest monthlyStatementController;

    private MonthlyStatementDto monthlyStatementDto;
    @BeforeEach
    void setup() {

        MonthlyStatmentController controller = new MonthlyStatmentController(monthlyService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        monthlyStatementDto = new MonthlyStatementDto();
        monthlyStatementDto.setId(1L);
        monthlyStatementDto.setAccountId(10L);
        monthlyStatementDto.setMonth("2025-10");
    }


    @Test
    void generateStatementupdate() throws Exception{

        when(monthlyService.generateStatement(any(),any(),any())).thenReturn(monthlyStatementDto);

        mockMvc.perform(post("/monthly/generate/2025-10/2025-10-20")
                     .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(monthlyStatementDto))) //ObjectMapper convert java object to json as body and after that will create new object and with different address

                .andExpect(status().isOk())
                .andExpect(jsonPath("$.month").value("2025-10"));

    }



    @Test
    void testgetfindByAccountIdAndMonth() throws Exception{

        when(monthlyService.getfindByAccountIdAndMonth(any(),any())).thenReturn(monthlyStatementDto);
        mockMvc.perform(get("/monthly/1/2025-10"))
                .andExpect(status().isOk());


    }




}
