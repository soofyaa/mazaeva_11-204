import java.io.IOException;
import java.util.List;

public class CatFactPerformer {
    private CatFactGetter catFactGetter;
    private ApiRequestExecutor apiRequestExecutor;
    private String url;

    public CatFactPerformer() {}

    public CatFactPerformer(CatFactGetter catFactGetter, ApiRequestExecutor apiRequestExecutor, String url) {
        this.catFactGetter = catFactGetter;
        this.apiRequestExecutor = apiRequestExecutor;
        this.url = url;
    }

    public String catToPrettyFormat() throws IOException {
        List<CatFact> facts = apiRequestExecutor.getCatFactsFromApi(url);
        return catFactGetter.getCatFacts(facts);
    }
}
