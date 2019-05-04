package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.TrelloConfig;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FacadeTrelloTest {
    @InjectMocks
    private TrelloFacade trelloFacade;      // @InjectMocks podpinamy do klasy którą testujemy
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private TrelloConfig trelloConfig;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloMapper trelloMapper;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private TrelloValidator trelloValidator;

    @Before
    public void init(){
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }


    @Test
    public void  shouldReturnEmptyListWithFacade()throws URISyntaxException{
        //Given
        TrelloBoardDto[] trelloBoards = null;

        URI uri = new URI("http://test.com/members/dariuszharanczyk/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloFacade.fetchTrelloBoards();

        //Then
        assertEquals(0, fetchedTrelloBoards.size());
    }

    @Test
    public  void shouldFetchTrelloBoardsWithFacade() throws URISyntaxException {
        //Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        TrelloBoard[] trelloB = new TrelloBoard[1];
        trelloB[0] = new TrelloBoard("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/dariuszharanczyk/boards?key=test&token=test&fields=name,id&lists=all");

        /***
         * Zawsze mockujemy wszystkie metody klas, które są wykorzystywane przez testowaną klasę
         */
        when(trelloMapper.mapToBoards(trelloService.fetchTrelloBoards())).thenReturn(Arrays.asList(trelloB));
        when(trelloValidator.validateTrelloBoards(Arrays.asList(trelloB))).thenReturn(Arrays.asList(trelloB));
        when(trelloMapper.mapToBoardsDto(Arrays.asList(trelloB))).thenReturn(Arrays.asList(trelloBoards));

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloFacade.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }
}
