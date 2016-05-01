package org.team16.team16week6;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
    @Mock private InGameSummonerQuerier querier;
    
    @Before
    public void setup() {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);
        
        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
        querier = mock(InGameSummonerQuerier.class);
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
      
        final String summonerName;
        final String actualGameKey; 
        final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
        
        GIVEN:
        {
        	summonerName = "akane24";
        }
        
        WHEN:
        {
        	when(querier.queryGameKey(summonerName)).thenReturn(expectedGameKey);
        	actualGameKey = querier.queryGameKey(summonerName);
    	}
        
        THEN:
        {
        	assertThat(actualGameKey, is(expectedGameKey));
        }
    }    
    @Test
    public void shouldQuerierReportMoreThan5Summoners() throws Exception{

        final int EXCEED_NUMBER_OF_SUMMONER;
        final String summonerName;
        final int actualNumOfSummoners;
        
        GIVEN:
        {
        	summonerName ="akane24";    
        	querier.queryGameKey(summonerName);     
        	EXCEED_NUMBER_OF_SUMMONER = 4;
        }
        
        WHEN :
        {
        	when(querier.getParticipantsCount()).thenReturn(10);         
        	actualNumOfSummoners = querier.getParticipantsCount();
        }
        
        THEN:
        {
        	assertTrue(actualNumOfSummoners >= EXCEED_NUMBER_OF_SUMMONER);
        }
    }
}
