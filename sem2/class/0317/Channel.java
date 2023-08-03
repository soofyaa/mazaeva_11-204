public class Channel {
    private int rank;
    private String name;
    private String link;
    private String brandChannel;
    private double subscribers;
    private String language;
    private String category;
    private String country;


    public Channel() {}
    public Channel(int rank, String name, String link, String brandChannel, double subscribers,
                   String language, String category, String country) {
        this.rank = rank;
        this.name = name;
        this.link = link;
        this.brandChannel = brandChannel;
        this.subscribers = subscribers;
        this.language = language;
        this.category = category;
        this.country = country;
    }


}
