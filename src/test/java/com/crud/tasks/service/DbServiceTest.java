package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    DbService service;

    @Mock
    TaskRepository repository;

    @Test
    public void shouldGetAllTask() throws Exception{
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Title task", "Task content"));

        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> retrieveTaskList = service.getAllTasks();

        //Then
        assertNotNull(retrieveTaskList);
        assertEquals("Title task", retrieveTaskList.get(0).getTitle());
    }
    @Test
    public void shouldSaveTask() throws Exception{
        //Given
        Task task = new Task(1L, "Title task", "Task content");
        String title = task.getTitle();
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = service.saveTask(task);

        //Then
        assertEquals(title,savedTask.getTitle());
    }

    @Test
    public void shouldGetTask() {
        //Given
        Task task = new Task(1L, "Title task", "Task content");
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(task));
        //When
        Optional<Task> retrieveTask = service.getTask(1L);
        //Then
        assertNotNull(retrieveTask);
    }
}