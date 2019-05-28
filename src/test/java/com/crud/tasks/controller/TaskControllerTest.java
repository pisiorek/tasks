package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
//import org.aspectj.lang.annotation.Before;
import org.junit.Before;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private DbService service;

    @Test
    public void shouldGetEmptyTasks() throws Exception {

        //Given
        List<TaskDto> tasks = new ArrayList<>();
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(tasks);

        //When $ Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetTasks() throws Exception {

        //Given
        List<TaskDto> tasks = new ArrayList<>();
        tasks.add(new TaskDto(1L,"Task Title", "Task content"));
        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(tasks);

        //When $ Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Tasks Fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Task Title")))
                .andExpect(jsonPath("$[0].content", is("Task content")));
    }

    @Test
    public void shouldGetTheTask() throws Exception {

        //Given
        TaskDto taskDto = new TaskDto(1L,"Task Title", "Task content");
        Task task = new Task(1L, "Task Title", "Task content");

        Optional<Task> taskOptional = Optional.of(task);
        Long taskId = 1L;

        when(service.getTask(taskId)).thenReturn(taskOptional);
        when(taskMapper.mapToTaskDto(service.getTask(taskId).orElseThrow(TaskNotFoundException::new))).thenReturn(taskDto);

        //When $ Then
        mockMvc.perform(get("/v1/task/getTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Tasks Fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task Title")))
                .andExpect(jsonPath("$.content", is("Task content")));
    }

    @Test
    public void shouldUpdateTask() throws Exception {

        //Given
        TaskDto taskDto = new TaskDto(1L,"Task Title", "Task content");
        Task task = new Task(1L, "Task Title", "Task content");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);  //dlaczego tu, i w jakich przypadkach stosujemy Matchers.any()

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When $ Then
        mockMvc.perform(put("/v1/task/updateTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                //Tasks Fields
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task Title")))
                .andExpect(jsonPath("$.content", is("Task content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {

        //Given
        Task task = new Task(1L, "Task Title", "Task content");

        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTask(any(TaskDto.class))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When $ Then
        mockMvc.perform(post("/v1/task/createTask").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteTask() throws Exception {

        //Given
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/task/deleteTask")
                .param("taskId", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}