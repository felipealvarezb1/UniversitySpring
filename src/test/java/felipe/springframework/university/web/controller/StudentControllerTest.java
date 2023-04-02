package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.common.dto.StudentDto;
import felipe.springframework.university.domain.model.Student;
import felipe.springframework.university.domain.services.StudentService;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = StudentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService studentService;

    private Student student;
    private StudentDto studentDto;
    private felipe.springframework.university.domain.model.Test test;

    List<felipe.springframework.university.domain.model.Test> testList = new ArrayList<>();

    @BeforeEach
    public void init(){

        student = Student.builder()
                .id(1L)
                .firstName("Felipe")
                .lastName("Alvarez")
                .email("f@hotmail.com")
                .username("falvarez")
                .careerId(1L).build();

        test = felipe.springframework.university.domain.model.Test.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();

        testList.add(test);

        studentDto = StudentDto.builder()
                .id(1L)
                .firstName("Felipe")
                .lastName("Alvarez")
                .email("f@hotmail.com")
                .username("falvarez")
                .careerId(1L).build();

    }

    @Test
    void createStudent() throws Exception{
        given(studentService.createStudent(studentDto)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(studentDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(studentDto.getEmail())));
    }

    @Test
    void updateStudent() throws Exception{
        when(studentService.updateStudent(studentDto)).thenReturn(studentDto);

        ResultActions response = mockMvc.perform(put("/api/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(studentDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(studentDto.getEmail())));

    }

    @Test
    void findAllStudents() throws Exception{
        List<StudentDto> listStudentDto = new ArrayList<>();
        listStudentDto.add(studentDto);

        when(studentService.findAllStudents(1,10)).thenReturn(listStudentDto);

        ResultActions response = mockMvc.perform(get("/api/student?pageNumber=1&pageSize=10")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void findStudentById() throws Exception{
        Long id = 1L;

        when(studentService.findStudentById(id)).thenReturn(studentDto);

        ResultActions response = mockMvc.perform(get("/api/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(studentDto.getFirstName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(studentDto.getEmail())));
    }

    @Test
    void deleteStudentById() throws Exception{
        Long id = 1L;
        doNothing().when(studentService).deleteStudentById(id);

        ResultActions response = mockMvc.perform(delete("/api/student/1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getStudentGroupRegistration() throws Exception{

        GroupRegistrationGeneralDto generalDto = new GroupRegistrationGeneralDto();
        generalDto.setStudentId(student.getId());
        generalDto.setGroupId(1L);
        generalDto.setRegisteredAt(LocalDateTime.now());

        GroupRegistrationDto groupRegistrationDto = new GroupRegistrationDto();
        groupRegistrationDto.setTests(testList);
        groupRegistrationDto.setFinalScore(4.6);
        groupRegistrationDto.setApproved(true);

        when(studentService.getStudentGroupRegistration(generalDto.getStudentId(), generalDto.getGroupId())).thenReturn(groupRegistrationDto);

        ResultActions response = mockMvc.perform(get("/api/student/1/group/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupRegistrationDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalScore", CoreMatchers.is(groupRegistrationDto.getFinalScore())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved", CoreMatchers.is(groupRegistrationDto.getApproved())));

    }

    @Test
    void registerStudent() throws Exception{

        Long id = 1L;

        GroupRegistrationGeneralDto generalDto = new GroupRegistrationGeneralDto();
        generalDto.setStudentId(id);
        generalDto.setGroupId(id);
        generalDto.setRegisteredAt(LocalDateTime.now());

        when(studentService.registerStudent(generalDto)).thenReturn(generalDto);

        ResultActions response = mockMvc.perform(post("/api/student/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(generalDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.registeredAt", CoreMatchers.is(generalDto.getRegisteredAt())));
    }
}