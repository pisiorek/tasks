package com.crud.tasks.domain;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloListTest {
    @Test
    public void testTrelloEmptyList() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        
        //When
        //Then
        assertEquals(0, list.size());
    }

    @Test
    public void testTrelloListName() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name", true);
        list.add(trelloList);

        //When
        String name = list.get(0).getName();
        //Then
        assertEquals("name", name);
    }

    @Test
    public void testTrelloNotEmptyList() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name", true);
        list.add(trelloList);

        //When
        //Then
        assertEquals(1, list.size());
    }

    @Test
    public void testTrelloListId() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name", true);
        list.add(trelloList);

        //When
        //Then
        assertEquals("1", list.get(0).getId());
    }

    @Test
    public void testTrelloListIsClosed() {
        //Given
        List<TrelloList> list = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name", true);
        list.add(trelloList);

        //When
        //Then
        assertEquals(true, list.get(0).isClosed());
    }


}
