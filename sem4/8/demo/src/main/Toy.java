@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Toy {

    private Long id;

    private String name;

    private List<Pet> pets;
}