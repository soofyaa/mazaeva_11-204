import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        List<PlayerNBA> players = new ArrayList<>();
        try {
            FileInputStream fileinputStream = new FileInputStream("nba_players.csv");
            byte[] bytes = new byte[fileinputStream.available()];
            fileinputStream.read(bytes);
            String file = new String(bytes);
            String[] fileString = file.split("\\r?\\n");
            List<String> strings = new ArrayList<>(Arrays.asList(fileString));
            strings.remove(0);
            for (String s : strings) {
                String[] data = s.split(",");
                players.add(PlayerNBA.builder()
                            .id(data[0] == null ? 0 : Integer.parseInt(data[0]))
                            .firstName(data[1])
                            .lastName(data[2])
                            .position(data[3])
                            .idTeam(data[4] == null ? 0 : Integer.parseInt(data[4]))
                            .abbreviation(data[5])
                            .city(data[6])
                            .conference(data[7])
                            .division(data[8])
                            .fullName(data[9])
                            .name(data[10])
                            .build());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(players);
    }





    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PlayerNBA {
        int id;
        String firstName;
        String lastName;
        String position;
        int idTeam;
        String abbreviation;
        String city;
        String conference;
        String division;
        String fullName;
        String name;
    }
}
