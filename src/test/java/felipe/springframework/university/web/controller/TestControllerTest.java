package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.common.dto.TestDto;
import felipe.springframework.university.domain.services.TestService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TestService testService;

    private felipe.springframework.university.domain.model.Test test;
    private TestDto testDto;

    @BeforeEach
    public void init(){

        test = felipe.springframework.university.domain.model.Test.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();

        testDto = TestDto.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();
    }

    @Test
    void createTest() throws Exception{
        given(testService.createTest(testDto)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percentage", CoreMatchers.is(testDto.getPercentage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", CoreMatchers.is(testDto.getScore())));
    }

    @Test
    void updatedTest() throws Exception{
        when(testService.updateTest(testDto)).thenReturn(testDto);

        ResultActions response = mockMvc.perform(put("/api/test")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percentage", CoreMatchers.is(testDto.getPercentage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", CoreMatchers.is(testDto.getScore())));
    }

    @Test
    void findTestById() throws Exception{
        Long id = 1L;

        when(testService.findTestById(id)).thenReturn(testDto);

        ResultActions response = mockMvc.perform(get("/api/test/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.percentage", CoreMatchers.is(testDto.getPercentage())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score", CoreMatchers.is(testDto.getScore())));

    }

    @Test
    void findAllTests() throws Exception{
        List<TestDto> listTestDto = new ArrayList<>();
        listTestDto.add(testDto);

        when(testService.findAllTests()).thenReturn(listTestDto);

        ResultActions response = mockMvc.perform(get("/api/test")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteTestById() throws Exception{

        Long id = 1L;
        doNothing().when(testService).deleteTestById(id);

        ResultActions response = mockMvc.perform(delete("/api/test/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}