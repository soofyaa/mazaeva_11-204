import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ApiRequestExecutor {
    private String url;

    public ApiRequestExecutor() {}

    public ApiRequestExecutor(String url) {
        this.url = url;
    }

    public List<CatFact> getCatFactsFromApi(String url) throws IOException {
        InputStream inputStream = new URL(url).openStream();
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        String json = new String(bytes);
        List<CatFact> facts = new ObjectMapper().readValue(json, new TypeReference<List<CatFact>>(){});
        return facts;
    }
}
