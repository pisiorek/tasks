package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void shouldFetchTrelloBoards(){
        //Given
        List<TrelloListDto> listLists = new ArrayList<>();
        List<TrelloBoardDto> boardList = new ArrayList<>();
        boardList.add(new TrelloBoardDto("1", "Trello Board", listLists));

        when(trelloClient.getTrelloBoards()).thenReturn(boardList);

        //When
        List<TrelloBoardDto> fetchTrelloBoard = trelloClient.getTrelloBoards();

        //Then
        assertEquals("Trello Board", fetchTrelloBoard.get(0).getName());
    }

    @Test
    public void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto( "Card name", "Card Description", "top", "listId");
        CreatedTrelloCardDto createdTrelloCard = new CreatedTrelloCardDto("1", "Card name", "http://test.com");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        //When
        CreatedTrelloCardDto createdCard = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("1", createdCard.getId());
        assertEquals("Card name", createdCard.getName());
        assertEquals("http://test.com", createdCard.getShortUrl());
    }


}