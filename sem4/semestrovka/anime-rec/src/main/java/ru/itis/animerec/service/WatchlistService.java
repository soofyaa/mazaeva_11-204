package ru.itis.animerec.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.animerec.dto.WatchlistDTO;

import java.util.List;

public interface WatchlistService {
    Long createWatchlist(WatchlistDTO watchlistDTO, MultipartFile poster);
    WatchlistDTO findById(Long id);
    List<WatchlistDTO> findListsNamesCurrentUser();
    void addAnimeToWatchlist(Long animeId, Long userListId);

    void deleteWatchlistById(Long id);
    List<WatchlistDTO> findListsByUserId(Long id);

}
