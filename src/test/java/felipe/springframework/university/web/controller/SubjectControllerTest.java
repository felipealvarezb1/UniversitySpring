package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.common.dto.SubjectDto;
import felipe.springframework.university.domain.model.Subject;
import felipe.springframework.university.domain.services.SubjectService;
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
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = SubjectController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubjectService subjectService;

    private Subject subject;
    private SubjectDto subjectDto;

    @BeforeEach
    public void init(){

        subject = Subject.builder()
                .id(1L)
                .name("Programacion")
                .credits(3)
                .startDate(new Date(2022, 12, 12))
                .endDate(new Date(2023, 05, 12))
                .careerId(1L).build();

        subjectDto = SubjectDto.builder()
                .id(1L)
                .name("Programacion 2")
                .credits(4)
                .startDate(new Date(2022, 12, 12))
                .endDate(new Date(2023, 05, 12))
                .careerId(1L).build();

    }

    @Test
    void createSubject() throws Exception{
        given(subjectService.createSubject(subjectDto)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(subjectDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.credits", CoreMatchers.is(subjectDto.getCredits())));
    }

    @Test
    void updateSubject() throws Exception{

        when(subjectService.updateSubject(subjectDto)).thenReturn(subjectDto);

        ResultActions response = mockMvc.perform(put("/api/subject")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(subjectDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.credits", CoreMatchers.is(subjectDto.getCredits())));
    }

    @Test
    void findSubjectById() throws Exception{

        Long id = 1L;

        when(subjectService.findSubjectById(id)).thenReturn(subjectDto);

        ResultActions response = mockMvc.perform(get("/api/subject/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(subjectDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(subjectDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.credits", CoreMatchers.is(subjectDto.getCredits())));

    }

    @Test
    void findAllSubject() throws Exception{
        List<SubjectDto> listSubjectDto = new ArrayList<>();
        listSubjectDto.add(subjectDto);

        when(subjectService.findAllSubjects(1,10)).thenReturn(listSubjectDto);

        ResultActions response = mockMvc.perform(get("/api/subject?pageNumber=1&pageSize=10")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteSubjectById() throws Exception{
        Long id = 1L;
        doNothing().when(subjectService).deleteSubjectById(id);

        ResultActions response = mockMvc.perform(delete("/api/subject/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}