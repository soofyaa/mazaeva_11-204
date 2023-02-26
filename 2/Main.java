import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static final int TERRITORY = 0;
    static final int REGION = 1;
    static final int EDITION = 3;
    static final int STATUS = 4;
    static final int PR = 5;
    static final int A = 10;
    static final int B = 15;
    static final int C = 19;

    static class Ranking {
        private String territory;
        private String region;
        private Integer edition;
        private String status;
        private Integer politicalRightsRating;

        public String getTerritory() {
            return territory;
        }
        public String getRegion() {
            return region;
        }
        public Integer getEdition() {
            return edition;
        }
        public String getStatus() {
            return status;
        }
        public Integer getPoliticalRightsRating() {
            return politicalRightsRating;
        }

        public Ranking() {
        }

        public Ranking(String territory, String region, Integer edition, String status, Integer politicalRightsRating) {
            this.territory = territory;
            this.region = region;
            this.edition = edition;
            this.status = status;
            this.politicalRightsRating = politicalRightsRating;
        }

        public String toString() {
            return "Ranking {" +
                    "territory = " + territory +
                    ", region = " + region +
                    ", edition = " + edition +
                    ", status = " + status +
                    ", politicalRightsRating = " + politicalRightsRating +
                    '}';
        }
    }

    static class ExtendedRanking extends Ranking {
        /*
         A. Electoral Process
         B. Political Pluralism and Participation
         C. Functioning of Government
        */
        private Integer scoreA;
        private Integer scoreB;
        private Integer scoreC;

        public Integer getScoreC() {
            return scoreC;
        }

        public ExtendedRanking () {
        }

        public ExtendedRanking (String territory, String region, Integer year, String status, Integer politicalRightsRating, Integer scoreA, Integer scoreB, Integer scoreC) {
            super(territory, region, year, status, politicalRightsRating);
            this.scoreA = scoreA;
            this.scoreB = scoreB;
            this.scoreC = scoreC;
        }

        @Override
        public String toString() {
            return "ExtendedRanking {" +
                    "territory = " + getTerritory() +
                    ", region = " + getRegion() +
                    ", edition = " + getEdition() +
                    ", status = " + getStatus() +
                    ", politicalRightsRating = " + getPoliticalRightsRating() +
                    ", scoreA = " + scoreA +
                    ", scoreB = " + scoreB +
                    ", scoreC = " + scoreC +
                    '}';
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("ranking.csv"));
        file.nextLine();
        List<Ranking> rankingList = new ArrayList<>();
        List<ExtendedRanking> extendedRankingList = new ArrayList<>();

        while (file.hasNextLine()) {
            String s = file.nextLine();
            if (s.isEmpty()) {
                continue;
            }
            String[] strings = s.split(",");
            rankingList.add(new Ranking(strings[TERRITORY], strings[REGION], Integer.parseInt(strings[EDITION]), strings[STATUS], Integer.parseInt(strings[PR])));
            extendedRankingList.add(new ExtendedRanking(strings[TERRITORY], strings[REGION], Integer.parseInt(strings[EDITION]), strings[STATUS], Integer.parseInt(strings[PR]), Integer.parseInt(strings[A]), Integer.parseInt(strings[B]), Integer.parseInt(strings[C])));
        }

        Comparator<ExtendedRanking> comparator2 = (x, y) -> x.getScoreC().compareTo(y.getScoreC());
        Comparator<Ranking> comparator3 = (x, y) -> x.getStatus().compareTo(y.getStatus());

        System.out.println("-------------main-------------");
        method1(rankingList);
        method1(extendedRankingList);
        extendedRankingList.sort(comparator2);
        method2(extendedRankingList, 3);
        int from = extendedRankingList.size() - 5;
        int to = extendedRankingList.size();
        for (int i = from; i < to; i++) {
            System.out.println(extendedRankingList.get(i));
        }
        rankingList.sort(comparator3);
    }

    public static void method1(List<? extends Ranking> list) {
        Comparator<Ranking> comparator = (x, y) -> x.getPoliticalRightsRating().compareTo(y.getPoliticalRightsRating());
        list.sort(comparator);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPoliticalRightsRating() == 5) {
                System.out.println(list.get(i).getTerritory());
                System.out.println(i);
                break;
            }
        }
    }

    public static void method2(List<? super ExtendedRanking> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(new ExtendedRanking());
        }
    }
}