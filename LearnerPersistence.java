import java.io.*;
import java.util.Scanner;

// LearnerRecord class implementing Serializable
class LearnerRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    int rollNumber;
    String fullName;
    double score;

    public LearnerRecord(int rollNumber, String fullName, double score) {
        this.rollNumber = rollNumber;
        this.fullName = fullName;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNumber + ", Name: " + fullName + ", Score: " + score;
    }
}

public class LearnerPersistence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take learner input
        System.out.print("Enter roll number: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter learner name: ");
        String name = sc.nextLine();
        System.out.print("Enter learner score: ");
        double score = sc.nextDouble();

        LearnerRecord learner = new LearnerRecord(id, name, score);

        // Serialize learner object to file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("learner.dat"))) {
            oos.writeObject(learner);
            System.out.println("Learner object serialized to learner.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize learner object from file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("learner.dat"))) {
            LearnerRecord deserializedLearner = (LearnerRecord) ois.readObject();
            System.out.println("Deserialized Learner: " + deserializedLearner);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
