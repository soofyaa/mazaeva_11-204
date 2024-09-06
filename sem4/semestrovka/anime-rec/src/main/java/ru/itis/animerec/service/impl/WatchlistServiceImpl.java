package ru.itis.animerec.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.animerec.dto.WatchlistDTO;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.entity.UserEntity;
import ru.itis.animerec.entity.WatchlistEntity;
import ru.itis.animerec.repository.AnimeRepository;
import ru.itis.animerec.repository.CommentWatchlistRepository;
import ru.itis.animerec.repository.WatchlistRepository;
import ru.itis.animerec.security.UserDetailsImpl;
import ru.itis.animerec.service.CommentWatchlistService;
import ru.itis.animerec.service.WatchlistService;
import ru.itis.animerec.utils.mapper.WatchlistMapper;

import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;
    private final AnimeRepository animeRepository;
    private final WatchlistMapper watchlistMapper;
    private final CommentWatchlistRepository commentWatchlistRepository;

    @SneakyThrows
    public Long createWatchlist(WatchlistDTO watchlistDTO, MultipartFile poster) {
        log.info("Creating watchlist: {}", watchlistDTO);
        WatchlistEntity watchlistEntity = watchlistMapper.toEntity(watchlistDTO);

        byte[] posterPath = poster.getBytes();
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();

        watchlistEntity.setPoster(posterPath);
        watchlistEntity.setUser(user);

        WatchlistEntity savedWatchlist = watchlistRepository.save(watchlistEntity);
        log.info("Watchlist created successfully with id: {}", savedWatchlist.getId());

        return savedWatchlist.getId();
    }

    @Transactional
    public WatchlistDTO findById(Long id) {
        log.info("Fetching watchlist by id: {}", id);
        WatchlistEntity watchlistEntity = watchlistRepository.findWatchlistWithAnimesById(id);
        log.info("Watchlist fetched: {}", watchlistEntity);
        return watchlistMapper.toDto(watchlistEntity);
    }

    @Override
    public List<WatchlistDTO> findListsNamesCurrentUser() {
        log.info("Fetching watchlists names for current user");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity user = userDetails.getUser();
        List<WatchlistDTO> watchlists = watchlistRepository.findListNamesAndIdsByUserId(user.getId());
        log.info("Watchlists names fetched: {}", watchlists);
        return watchlists;
    }

    @Override
    @Transactional
    public void addAnimeToWatchlist(Long animeId, Long userListId) {
        log.info("Adding anime with id {} to watchlist with id {}", animeId, userListId);
        WatchlistEntity watchlist = watchlistRepository.findById(userListId)
                .orElseThrow(() -> new NoSuchElementException("Watchlist not found with id: " + userListId));

        AnimeEntity anime = animeRepository.findById(animeId)
                .orElseThrow(() -> new NoSuchElementException("Anime not found with id: " + animeId));

        if (!watchlist.getAnimes().contains(anime)) {
            watchlist.getAnimes().add(anime);
            watchlistRepository.save(watchlist);
            log.info("Anime added successfully to watchlist");
        } else {
            log.warn("Anime with id {} is already in watchlist with id {}", animeId, userListId);
        }
    }

    @Transactional
    @Override
    public List<WatchlistDTO> findListsByUserId(Long id) {
        log.info("Fetching watchlists by user id: {}", id);
        List<WatchlistEntity> watchlists = watchlistRepository.findByUserId(id);
        log.info("Watchlists fetched: {}", watchlists);
        return watchlists.stream()
                .map(watchlistMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteWatchlistById(Long id) {
        log.info("Deleting watchlist by id: {}", id);
        commentWatchlistRepository.deleteByWatchlistId(id);
        watchlistRepository.deleteById(id);
        log.info("Watchlist deleted successfully");
    }
}

