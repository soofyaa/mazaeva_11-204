import java.io.IOException;
import java.util.List;

public class CatFactPerformer {
    private CatFactGetter catFactGetter;
    private ApiRequestExecutor apiRequestExecutor;

    public CatFactPerformer() {}

    public CatFactPerformer(CatFactGetter catFactGetter, ApiRequestExecutor apiRequestExecutor) {
        this.catFactGetter = catFactGetter;
        this.apiRequestExecutor = apiRequestExecutor;
    }

    public String catToPrettyFormat(String url) throws IOException {
        List<CatFact> facts = apiRequestExecutor.getCatFactsFromApi(url);
        return catFactGetter.getCatFacts(facts);
    }
}
