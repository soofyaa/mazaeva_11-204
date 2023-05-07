import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Collections;
import static org.mockito.Matchers.any;


public class TestCatFactPerformer {
    public static ApiRequestExecutor apiRequestExecutor;

    public static CatFactPerformer catFactPerformer;

    @BeforeAll
    public static void init() {
        apiRequestExecutor = Mockito.mock(ApiRequestExecutor.class);
        catFactPerformer = new CatFactPerformer(
                new CatFactGetter(),
                apiRequestExecutor,
                "https://cat-fact.herokuapp.com/facts"
        );
    }

    @Test
    public void test() throws Exception {
        String s = "Fact 1: Cats rule the world\n";
        Mockito.when(apiRequestExecutor.getCatFactsFromApi(any()))
                .thenReturn(Collections.singletonList(new CatFact("Cats rule the world")));

        String res = catFactPerformer.catToPrettyFormat();
        Assertions.assertEquals(res, s);
    }
}