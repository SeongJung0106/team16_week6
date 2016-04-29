package org.team16.team16week6;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by tchi on 2016. 4. 25..
 */
public class InGameSummonerQuerierTest {
    private InGameSummonerQuerier querier;

    @Before
    public void setup() {
        final String apiKey = "8242f154-342d-4b86-9642-dfa78cdb9d9c";
        GameParticipantListener dontCareListener = mock(GameParticipantListener.class);

        querier = new InGameSummonerQuerier(apiKey, dontCareListener);
    }

    @Test
    public void shouldQuerierIdentifyGameKeyWhenSpecificSummonerNameIsGiven() throws Exception {
        final String summonerName;

        GIVEN: {
            summonerName = "akane24";
        }

        final String actualGameKey;
        WHEN: {
            actualGameKey = querier.queryGameKey(summonerName);
        }

        final String expectedGameKey = "4/bl4DC8HBir8w7bGHq6hvuHluBd+3xM";
        THEN: {
            assertThat(actualGameKey, is(expectedGameKey));
        }
    }
}
