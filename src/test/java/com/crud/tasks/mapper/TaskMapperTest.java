package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
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
    public void mapToTask() {
/*        //Given
        //TaskDto taskDtoMock = mock(TaskDto.class);
        //Task task = new Task();

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(task, mappedTask);*/
    }

    @Test
    public void mapToTaskDto() {
    }

    @Test
    public void mapToTaskDtoList() {
    }
}