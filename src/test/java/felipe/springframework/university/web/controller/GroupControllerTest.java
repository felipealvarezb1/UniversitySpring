package felipe.springframework.university.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import felipe.springframework.university.common.dto.CareerDto;
import felipe.springframework.university.common.dto.GroupDto;
import felipe.springframework.university.domain.model.Group;
import felipe.springframework.university.domain.services.GroupService;
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

@WebMvcTest(controllers = GroupController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GroupService groupService;

    private Group group;
    private GroupDto groupDto;

    @BeforeEach
    public void init(){

        group = Group.builder()
                .id(1L)
                .name("STM231")
                .schedule("J/9:00-12:00pm")
                .room("33-105")
                .teacher("Felipe")
                .subjectId(1L).build();

        groupDto = GroupDto.builder()
                .id(1L)
                .name("STM231")
                .schedule("J/9:00-12:00pm")
                .room("33-105")
                .teacher("Felipe")
                .subjectId(1L).build();

    }

    @Test
    void createGroup() throws Exception{
        given(groupService.createGroup(groupDto)).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(groupDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher", CoreMatchers.is(groupDto.getTeacher())));
    }

    @Test
    void updateGroup() throws Exception{
        when(groupService.updateGroup(groupDto)).thenReturn(groupDto);

        ResultActions response = mockMvc.perform(put("/api/group")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(groupDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher", CoreMatchers.is(groupDto.getTeacher())));
    }

    @Test
    void findGroupById() throws Exception{
        Long id = 1L;

        when(groupService.findGroupById(id)).thenReturn(groupDto);

        ResultActions response = mockMvc.perform(get("/api/group/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(groupDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.teacher", CoreMatchers.is(groupDto.getTeacher())));
    }

    @Test
    void findAllGroups() throws Exception{
        List<GroupDto> listGroupDto = new ArrayList<>();
        listGroupDto.add(groupDto);

        when(groupService.findAllGroups(1,10)).thenReturn(listGroupDto);

        ResultActions response = mockMvc.perform(get("/api/group?pageNumber=1&pageSize=10")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));
    }

    @Test
    void deleteGroupById() throws Exception{
        Long id = 1L;
        doNothing().when(groupService).deleteGroupById(id);

        ResultActions response = mockMvc.perform(delete("/api/group/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}