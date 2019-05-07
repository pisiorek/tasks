package com.crud.tasks.domain;

import com.crud.tasks.service.DbService;
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
public class TrelloDtoTest {
/*    @InjectMocks
    TrelloDto trelloDto;*/

    @Test
    public void getBoard() {
        //Given
        TrelloDto mockTrello = mock(TrelloDto.class);
        int board = 1;
        when(mockTrello.getBoard()).thenReturn(board);

        //When
        int fetchedTrelloBoard = mockTrello.getBoard();

        //Then
        assertEquals(1, fetchedTrelloBoard);
    }

    @Test
    public void getCard() {
        TrelloDto mockTrello = mock(TrelloDto.class);
        int card = 1;
        when(mockTrello.getCard()).thenReturn(card);

        //When
        int fetchedTrelloCard = mockTrello.getCard();

        //Then
        assertEquals(1, fetchedTrelloCard);
    }
}