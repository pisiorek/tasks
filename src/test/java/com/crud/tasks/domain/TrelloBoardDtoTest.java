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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloBoardDtoTest {

    @Test
    public void testCreateTrelloBoardDto(){
        //Given
        List<TrelloListDto> listListDto = new ArrayList<>();
        TrelloBoardDto mockTrelloBoard = new TrelloBoardDto("1", "name", listListDto);

        //When
        String nameBoard = mockTrelloBoard.getName();

        //Then
        assertEquals("name", nameBoard);
    }
}
