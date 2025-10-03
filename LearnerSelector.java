import java.util.*;

class Learner {
    String fullName;
    double score;

    public Learner(String fullName, double score) {
        this.fullName = fullName;
        this.score = score;
    }
}

public class LearnerSelector {
    public static void main(String[] args) {
        List<Learner> learners = Arrays.asList(
            new Learner("Ananya", 82),
            new Learner("Bharat", 70),
            new Learner("Chitra", 90),
            new Learner("Dev", 60)
        );

        System.out.println("Learners with scores above 75, arranged by score:");
        learners.stream()
                .filter(s -> s.score > 75)                     // filter score > 75
                .sorted(Comparator.comparingDouble(s -> s.score)) // sort by score ascending
                .map(s -> s.fullName)                               // extract fullNames
                .forEach(System.out::println);                 // display fullNames
    }
}
