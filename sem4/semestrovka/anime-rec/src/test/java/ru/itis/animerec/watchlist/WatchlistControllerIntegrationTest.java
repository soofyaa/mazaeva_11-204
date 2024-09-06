package ru.itis.animerec.watchlist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.animerec.controller.watchlist.WatchlistController;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.service.UserService;
import ru.itis.animerec.service.WatchlistService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WatchlistController.class)
public class WatchlistControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WatchlistService watchlistService;

    @MockBean
    private CommentWatchlistService commentWatchlistService;

    @MockBean
    private UserService userService;

    @Test
    public void testGetWatchlist() throws Exception {
        mockMvc.perform(get("/watchlist/{id}", 1L))
               .andExpect(status().isOk());
    }
}