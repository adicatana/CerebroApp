package group15.cerebro;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


/**
 * Created by adicatana on 06.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
@WebAppConfiguration
public class IntegrationTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    protected MockHttpSession mockSession;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
        this.mockSession = new MockHttpSession(webApplicationContext.getServletContext(), UUID.randomUUID().toString());
    }

    @Test
    public void startSingleGameTest() throws Exception {
        mockMvc.perform(get("/singleplayer/single/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void getRandomQuestionTest() throws Exception {

        mockMvc.perform(get("/session/start/demo").session(mockSession))
                .andExpect(status().isOk());

        mockMvc.perform(get("/singleplayer/topic").session(mockSession))
                .andExpect(status().isOk());

        mockMvc.perform(get("/singleplayer/single/1").session(mockSession))
                .andExpect(status().isOk());

        mockMvc.perform(get("/singleplayer/random").session(mockSession))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andDo(print());

    }

}