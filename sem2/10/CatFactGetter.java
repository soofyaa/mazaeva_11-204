import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CatFactGetter {
    private CatFact catFact;

    public CatFactGetter() {}

    public CatFactGetter(CatFact catFact) {
        this.catFact = catFact;
    }

    public String getCatFacts(List<CatFact> catFacts) {
        String s = "";
        int cnt = 1;
        for (CatFact fact : catFacts) {
            s += "Fact " + cnt + ": " + fact + "\n";
            cnt++;
        }
        return s;
    }
}