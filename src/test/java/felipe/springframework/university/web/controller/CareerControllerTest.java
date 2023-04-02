package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.domain.model.Career;
import felipe.springframework.university.domain.services.CareerService;
import felipe.springframework.university.mapper.CareerMapper;
import lombok.RequiredArgsConstructor;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.Matchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = CareerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CareerControllerTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private  ObjectMapper objectMapper;

    @MockBean
    private CareerService careerService;

    private Career career;
    private CareerDto careerDto;

    @BeforeEach
    public void init(){
        career = Career.builder()
                .id(1L)
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();

        careerDto = CareerDto.builder()
                .id(1L)
                .name("Ingenieria de sistemas")
                .description("Software")
                .totalCredits(160).build();
    }

    @Test
    void saveCareer() throws Exception {
        given(careerService.createCareer(careerDto)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/career")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(careerDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(careerDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(careerDto.getDescription())));
    }

    @Test
    void updateCareer() throws Exception{
        when(careerService.updateCareer(careerDto)).thenReturn(careerDto);

        ResultActions response = mockMvc.perform(put("/api/career")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(careerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(careerDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(careerDto.getDescription())));
    }

    @Test
    void findCareerById() throws Exception{
        Long id = 1L;

        when(careerService.findCareerById(id)).thenReturn(careerDto);

        ResultActions response = mockMvc.perform(get("/api/career/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(careerDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(careerDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(careerDto.getDescription())));

    }

    @Test
    void findAllCareer() throws Exception{
        List<CareerDto> listCareerDto = new ArrayList<>();
        listCareerDto.add(careerDto);

        when(careerService.findAllCareers(1, 10)).thenReturn(listCareerDto);

        ResultActions response = mockMvc.perform(get("/api/career?pageNumber=1&pageSize=10")
                        .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteCareerById() throws Exception{

        Long id = 1L;
        doNothing().when(careerService).deleteCareerById(id);

        ResultActions response = mockMvc.perform(delete("/api/career/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}