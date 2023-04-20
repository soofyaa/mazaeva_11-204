import lombok.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Playlist{
        String track_name;
        String track_add_date;
        String track_add_time;
        boolean multiple_artists_bool;
        String name_of_artists;
        String album_name;
        String album_release_date;
        String album_release_date_precision;
        Integer number_of_tracks_in_album;
        Integer position_in_playlist;
        Long track_duration_ms;
        Integer track_popularity;
        boolean track_explicit;
        String images_path;
        String data_collection_date;
    }

    public static void main(String[] args) {
        List<Playlist> playlists = new ArrayList<>();
        try {
            FileInputStream fileinputStream = new FileInputStream("playlist_1.csv");
            byte[] bytes = new byte[fileinputStream.available()];
            fileinputStream.read(bytes);
            String file = new String(bytes);
            String[] fileString = file.split("\\r?\\n");
            List<String> strings = new ArrayList<>(Arrays.asList(fileString));
            strings.remove(0);
            for (String s : strings) {
                String[] data = s.split(";");
                playlists.add(Playlist.builder()
                        .track_name(data[0])
                        .name_of_artists(data[4])
                        .number_of_tracks_in_album(Integer.parseInt(data[8]))
                        .track_popularity(Integer.parseInt(data[11]))
                        .build());
            }
        } catch(IOException e){
            throw new RuntimeException(e);
        }
        System.out.println(playlists);

        playlists.stream().filter(s -> s.name_of_artists.)
                // содержит , => больше 1






    }
}
