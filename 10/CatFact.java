import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CatFact {
    private String text;

    public CatFact(){}

    public CatFact(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}