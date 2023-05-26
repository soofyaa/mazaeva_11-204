import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("drinks.txt");
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        String json = new String(bytes);
        Cocktail cocktails = new ObjectMapper().readValue(json, Cocktail.class);
        System.out.println(cocktails);
        List<Drink> cocktailList = cocktails.getDrinks()
                .stream()
                .filter(x ->
                        !"Salt".equals(x.strIngredient1) &&
                        !"Salt".equals(x.strIngredient2) &&
                        !"Salt".equals(x.strIngredient3) &&
                        !"Salt".equals(x.strIngredient4) &&
                        !"Salt".equals(x.strIngredient5) &&
                        !"Salt".equals(x.strIngredient6))
                .collect(Collectors.toList());
        System.out.println(cocktailList);
        try (FileWriter writer = new FileWriter("test.txt")) {
            for (int i = 0; i < cocktailList.size(); i++) {
                int id = i+1;
                String line = id + " - " + cocktailList.get(i).strDrink + " - " + cocktailList.get(i).strGlass + "\n";
                writer.write(line);
            }
        }
    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class Drink {
        public String idDrink;
        public String strDrink;
        public String strGlass;
        public String strIngredient1;
        public String strIngredient2;
        public String strIngredient3;
        public String strIngredient4;
        public String strIngredient5;
        public String strIngredient6;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    static public class Cocktail{
        public ArrayList<Drink> drinks;
    }
}