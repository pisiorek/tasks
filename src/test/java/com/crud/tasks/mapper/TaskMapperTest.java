package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTest {
    @InjectMocks
    TaskMapper taskMapper;
    @Mock
    TaskDto taskDto;
    @Mock
    Task task;

    @Test
    public void testMapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Task Title", "Task content");

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals("Task Title", mappedTask.getTitle());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L, "Task Title", "Task content");

        //When
        TaskDto mappedTaskToDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals("Task Title", mappedTaskToDto.getTitle());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        Task task = new Task(1L, "Task Title", "Task content");
        List<Task> tasksList = new ArrayList<>();
        tasksList.add(task);

        //When
        List<TaskDto> mappedTasksList = taskMapper.mapToTaskDtoList(tasksList);

        //Then
        assertEquals("Task Title", mappedTasksList.get(0).getTitle());
    }
}