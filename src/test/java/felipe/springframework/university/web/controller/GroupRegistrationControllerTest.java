package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.GroupRegistrationDto;
import felipe.springframework.university.common.dto.GroupRegistrationGeneralDto;
import felipe.springframework.university.domain.model.GroupRegistration;
import felipe.springframework.university.domain.services.GroupRegistrationService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = GroupRegistrationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GroupRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupRegistrationService groupRegistrationService;


    private GroupRegistration groupRegistration;
    private felipe.springframework.university.domain.model.Test test;
    List<felipe.springframework.university.domain.model.Test> testList = new ArrayList<>();

    @BeforeEach
    public void init(){
        test = felipe.springframework.university.domain.model.Test.builder()
                .id(1L)
                .percentage(25)
                .name("Parical 1")
                .score(4.6)
                .registrationId(1L).build();

        testList.add(test);

        groupRegistration = GroupRegistration.builder()
                .id(1L)
                .studentId(1L)
                .groupId(1L)
                .registeredAt(LocalDateTime.now())
                .tests(testList)
                .build();
    }

    @Test
    void findRegistrationById() throws Exception{
        Long id = 1L;

        GroupRegistrationDto groupRegistrationDto = new GroupRegistrationDto();
        groupRegistrationDto.setTests(testList);
        groupRegistrationDto.setFinalScore(4.6);
        groupRegistrationDto.setApproved(true);

        when(groupRegistrationService.findRegistrationById(id)).thenReturn(groupRegistrationDto);

        ResultActions response = mockMvc.perform(get("/api/registration/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupRegistrationDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.finalScore", CoreMatchers.is(groupRegistrationDto.getFinalScore())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.approved", CoreMatchers.is(groupRegistrationDto.getApproved())));
    }

    @Test
    void deleteRegistrationById() throws Exception{
        Long id = 1L;

        doNothing().when(groupRegistrationService).deleteRegistrationById(id);

        ResultActions response = mockMvc.perform(get("/api/registration/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}