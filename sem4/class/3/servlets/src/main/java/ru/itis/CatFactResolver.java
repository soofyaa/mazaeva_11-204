package ru.itis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component("Cat")
public class CatFactResolver implements FactResolver {
    @Override
    @SneakyThrows
    public String getFact() {
        return new ObjectMapper()
                .readValue(new URL("https://cat-fact.herokuapp.com/facts/random"), Cat.class).getText();
    }
}
