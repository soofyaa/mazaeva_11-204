public class Billionaire {
    private int rank;
    private String personName;
    private int age;
    private int finalWorth;
    private String category;
    private String source;
    private String country;
    private String state;
    private String city;
    private String organization;
    private String selfMade;
    private String gender;
    private String birthDate;
    private String title;
    private int philanthropyScore;

    public Billionaire() {}

    public Billionaire( int rank, String personName, int age, int finalWorth, String category,
                        String source, String country, String state, String city, String organization,
                        String selfMade, String gender, String birthDate, String title, int philanthropyScore) {
        this.rank = rank;
        this.personName = personName;
        this.age = age;
        this.finalWorth = finalWorth;
        this.category = category;
        this.source = source;
        this.country = country;
        this.state = state;
        this.city = city;
        this.organization = organization;
        this.selfMade = selfMade;
        this.gender = gender;
        this.birthDate = birthDate;
        this.title = title;
        this.philanthropyScore = philanthropyScore;
    }

    @Override
    public String toString() {
        return "Billionaire{" +
                "rank=" + rank +
                ", personName=" + personName +
                ", age=" + age +
                ", finalWorth=" + finalWorth +
                ", category=" + category +
                ", source=" + source +
                ", country=" + country +
                ", state=" + state +
                ", city=" + city +
                ", organization=" + organization +
                ", selfMade=" + selfMade +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", title=" + title +
                ", philanthropyScore=" + philanthropyScore +
                '}';
    }
}
