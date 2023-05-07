import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CatFactGetter catFactGetter = new CatFactGetter();
        ApiRequestExecutor apiRequestExecutor = new ApiRequestExecutor();
        CatFactPerformer catFactPerformer = new CatFactPerformer(catFactGetter, apiRequestExecutor, "https://cat-fact.herokuapp.com/facts");
        System.out.println(catFactPerformer.catToPrettyFormat());
    }
}
