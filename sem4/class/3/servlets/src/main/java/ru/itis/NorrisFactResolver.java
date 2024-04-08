package ru.itis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component("Norris")
public class NorrisFactResolver implements FactResolver {
    @Override
    @SneakyThrows
    public String getFact() {
        return new ObjectMapper()
                .readValue(new URL("https://api.chucknorris.io/jokes/random"), Norris.class).getValue();
    }
}
