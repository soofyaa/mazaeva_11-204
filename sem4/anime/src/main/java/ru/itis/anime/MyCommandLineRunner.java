package ru.itis.anime;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itis.anime.entity.anime.Anime;

@RequiredArgsConstructor
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        String json = "{\"response\":{\"description\":\"Основано на серии детских книг, написанных Нарита Сатоко и проиллюстрированных Сэнно Энага.\\n\\nСерия содержит истории о приключениях, первой любви, дружбе и семейной тайне Фуки, маленькой ведьмы, проходящей обучение.\",\"poster\":{\"fullsize\":\"//img.yani.tv/posters/full/1636667988.jpg\",\"big\":\"//img.yani.tv/posters/big/1636667988.webp\",\"small\":\"//img.yani.tv/posters/small/1636667988.webp\",\"medium\":\"//img.yani.tv/posters/medium/1636667988.webp\",\"huge\":\"//img.yani.tv/posters/huge/1636667988.avif\"},\"title\":\"Ведьма-неудачница: Фука и тёмная ведьма\",\"anime_url\":\"22-7\",\"anime_id\":1,\"status\":0,\"rating\":{\"average\":6.99999999999999,\"counters\":12,\"kp_rating\":0,\"shikimori_rating\":6.43939393939394,\"anidub_rating\":0,\"myanimelist_rating\":6.31,\"worldart_rating\":0},\"year\":2023,\"min_age\":{\"value\":3,\"title\":\"PG-13 (от 13 лет)\"},\"views\":23930,\"season\":2,\"anime_status\":{\"value\":0,\"title\":\"вышел\",\"alias\":\"released\",\"class\":\"\"},\"type\":{\"value\":2,\"shortname\":\"п/ф\",\"name\":\"Полнометражный фильм\"},\"other_titles\":[\"Failure Witch\",\"Rakudai Majo: Fuuka to Yami no Majo\",\"Rakumajo\",\"The Klutzy Witch: Fuuka and the Dark Witch\",\"らくだい魔女 フウカと闇の魔女\"],\"remote_ids\":{\"worldart_id\":11084,\"shikimori_id\":51404,\"anidub_id\":0,\"anilibria_alias\":\"\",\"myanimelist_id\":51404,\"kp_id\":5002977,\"worldart_type\":\"animation\",\"sr_id\":0},\"translates\":[],\"creators\":[{\"url\":\"/catalog/director/34\",\"title\":\"Хамана Такаюки\",\"id\":34}],\"studios\":[{\"url\":\"/catalog/studio/productionig\",\"title\":\"Production I.G\",\"id\":52}],\"genres\":[{\"title\":\"Приключения\",\"url\":\"/catalog/category/priklyucheniya\",\"id\":32,\"alias\":\"priklyucheniya\"},{\"title\":\"Фэнтези\",\"url\":\"/catalog/category/fentezi\",\"id\":42,\"alias\":\"fentezi\"}],\"original\":\"Книга\",\"viewing_order\":[],\"blocked_in\":[],\"episodes\":{\"count\":1,\"aired\":0}}}";
        ObjectMapper objectMapper = new ObjectMapper();
        Anime anime = objectMapper.readValue(json, Anime.class);

        System.out.println(anime);
    }
}