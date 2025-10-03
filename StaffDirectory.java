import java.io.*;
import java.util.Scanner;

class Staff implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String designation;
    double salary;

    public Staff(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    } 
}

public class StaffDirectory {
    static final String FILE_NAME = "staff_records.dat";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Staff Directory Menu ---");
            System.out.println("1. Register Staff");
            System.out.println("2. Show Staff Records");
            System.out.println("0. Quit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerStaff(sc);
                    break;
                case 2:
                    showRecords();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }
            if (choice == 0) {
                break;
            }
        }

        sc.close();
    }

    private static void registerStaff(Scanner sc) {
        System.out.print("Enter Staff ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("Enter Staff Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Designation: ");
        String designation = sc.nextLine();
        System.out.print("Enter Salary: ");
        double salary = sc.nextDouble();

        Staff staff = new Staff(id, name, designation, salary);

        // Append to file
        try (ObjectOutputStream oos = new CustomObjectOutputStream(new FileOutputStream(FILE_NAME, true))) {
            oos.writeObject(staff);
            System.out.println("Staff registered successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showRecords() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("\n--- Staff Records ---");
            while (true) {
                Staff staff = (Staff) ois.readObject();
                System.out.println(staff);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No staff records found.");
        }
    }
}

// Custom ObjectOutputStream to append objects without writing header multiple times
class CustomObjectOutputStream extends ObjectOutputStream {
    public CustomObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        File file = new File(StaffDirectory.FILE_NAME);
        if (file.length() == 0) {
            super.writeStreamHeader();
        } else {
            reset();
        }
    }
}
