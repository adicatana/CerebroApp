package group15.cerebro;

import group15.cerebro.session.multi.Match;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class IntegrationTestMultiplayer {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    protected MockHttpSession mockSession1;
    protected MockHttpSession mockSession2;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.mockSession1 = new MockHttpSession(webApplicationContext.getServletContext(), UUID.randomUUID().toString());
        this.mockSession2 = new MockHttpSession(webApplicationContext.getServletContext(), UUID.randomUUID().toString());
    }

    private String user1 = "{\"login\" : \"demo\", \"password\" : \"demo\"}";
    private String user2 = "{\"login\" : \"1923544977860434\", \"password\" : \"facebookLoginNoPassword\"}";

    private void startSessions() throws Exception {
        mockMvc.perform(get("/session/start/demo").session(mockSession1))
                .andExpect(status().isOk());
        mockMvc.perform(get("/session/start/1923544977860434").session(mockSession2))
                .andExpect(status().isOk());
    }

    private void joinRoom() throws Exception {
        mockMvc.perform(get("/multi/join").session(mockSession1));
        mockMvc.perform(get("/multi/join").session(mockSession2));
    }

    private void makeMatch() throws Exception {
        mockMvc.perform(get("/multi/match").session(mockSession1));
        mockMvc.perform(get("/multi/match").session(mockSession2));
    }

    private void ping() throws Exception {
        mockMvc.perform(get("/multi/ping").session(mockSession1));
        mockMvc.perform(get("/multi/ping").session(mockSession2));
    }

    private void getQuestions() throws Exception {
        mockMvc.perform(get("/multi/random").session(mockSession1));
        mockMvc.perform(get("/multi/random").session(mockSession2));
    }

    private void respond() throws Exception {
        mockMvc.perform(post("/multi/answer").content("Response 1").session(mockSession1));
        mockMvc.perform(post("/multi/answer").content("Response 2").session(mockSession2));
    }

    @Test
    public void twoPlayerGame() throws Exception {
        startSessions();
        joinRoom();
        makeMatch();
        for (int i = 0; i <= Match.getTOTAL() / 2; ++i) {
            ping();
            getQuestions();
            respond();
        }
    }
}
