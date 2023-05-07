import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CatFactPerformerTest {
    public static ApiRequestExecutor apiRequestExecutor;

    public static CatFactPerformer catFactPerformer;

    @BeforeAll
    public static void init() {
        apiRequestExecutor = Mockito.mock(ApiRequestExecutor.class);
        catFactPerformer = new CatFactPerformer(new CatFactGetter(), apiRequestExecutor);
    }

    @Test
    public void testCatFactPerformer() throws Exception {
        String s = "Fact 1: cats can destroy this planet, but they are too lazy for that";
        List<CatFact> facts = apiRequestExecutor.getCatFactsFromApi("https://cat-fact.herokuapp.com/facts");
        Mockito.when(apiRequestExecutor.getCatFactsFromApi(any()))
                .thenReturn(facts);

        String res = catFactPerformer.catToPrettyFormat("");
        Assertions.assertEquals(res, s);
    }
}