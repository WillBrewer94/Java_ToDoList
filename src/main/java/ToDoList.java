import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ToDoList {
    private Goal[] goals;
    private User currUser;
    private String inputName;
    private BufferedReader file;
    private BufferedWriter output;
    private boolean isQuit = false;
    private boolean loop = true;

    public static void main(String[] args) throws IOException {
        ToDoList list = new ToDoList();
        list.run();
    }

    //driver code
    public void run() throws IOException {
        while (loop) {
            Scanner s = new Scanner(System.in);
            String input;
            isQuit = false;
            goals = new Goal[10];

            if (checkUser()) { //if user is found, loads user
                loadUser(inputName);
            } else { //if user is missing, new user is created
                currUser = new User(inputName);
                output = new BufferedWriter(new FileWriter("users.csv", true));
                output.write(", " + inputName);
                output.close();
            }

            while (!isQuit) {
                printString();
                printMenu();

                System.out.println("Enter Option: ");
                input = s.nextLine();

                if (input.equals("1")) {
                    addTask();

                } else if (input.equals("2")) {
                    addGoal();

                } else if (input.equals("3")) {
                    completeTask();

                } else if (input.equals("4")) {
                    isQuit = true;
                    writeFile();

                } else {
                    System.out.println("Input Not Recognized");
                }
            }
        }
    }

    public boolean checkUser() throws IOException {
        String currLine = "";
        boolean userFound = false;
        Scanner s = new Scanner(System.in);
        file = new BufferedReader(new FileReader("users.csv"));

        System.out.println("Enter your User Name: ");
        inputName = s.next();

        while ((currLine = file.readLine()) != null) {
            currLine = currLine.replaceAll("\\s+", "");
            String[] split = currLine.split(",");

            for (int i = 0; i < split.length; i++) {
                if (split[i].equals(inputName)) {
                    userFound = true;
                }
            }
        }

        makeFile();

        return userFound;
    }

    //sets current user
    public void loadUser(String user) throws IOException {
        String currLine = "";
        System.out.println("Loading user " + user + "...");

        String fileName = inputName + ".csv";
        file = new BufferedReader(new FileReader(fileName));

        while ((currLine = file.readLine()) != null) {
            String[] split = currLine.split(",");

            for (int i = 0; i < goals.length; i++) {
                if (goals[i] == null) {
                    goals[i] = new Goal(split[0], split);
                    i = goals.length;
                }
            }
        }
    }

    public void makeFile() throws IOException {
        String fileName = inputName + ".csv";

        //creates [user].csv file
        File newFile = new File(fileName);

        if (newFile.createNewFile()) {
            System.out.println("File " + fileName + " Created");
        } else {
            System.out.println("File " + fileName + " Already Exists");
        }
    }

    //called when user quits, overwrites the old file with the new info
    public void writeFile() throws IOException {
        String fileName = inputName + ".csv";
        String line = "";
        output = new BufferedWriter(new FileWriter(fileName));
        output.write("");

        for (int i = 0; i < goals.length; i++) {
            if (goals[i] != null) {
                Goal tempGoal = goals[i];
                line = tempGoal.getName() + ",";

                for (int j = 0; j < tempGoal.getTaskLength(); j++) {
                    Task tempTask = tempGoal.getTask(j);

                    if (tempTask != null) {
                        line += tempTask.getName() + "|"
                             + tempTask.getPriority()
                             + "|" + tempTask.getDate()
                             + "|" + tempTask.getIsDone() + ",";
                    }
                }
                output.append(line);
                output.append("\n");
            }
        }
        output.close();
    }

    public void printMenu() {
        System.out.println("\n[1] Add Task");
        System.out.println("[2] Add Goal");
        System.out.println("[3] Complete Task");
        System.out.println("[4] Quit and Save");
    }

    public void addTask() {
        Scanner s = new Scanner(System.in);
        System.out.println("Choose goal to add task to: ");
        int input = 0;

        try {
            input = s.nextInt();
            goals[input - 1].addTask();
        } catch (InputMismatchException e) {
            System.out.println("Input must be number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not a valid task.");
        } catch (NullPointerException e) {
            System.out.println("Not a valid task.");
        }
    }

    public void addGoal() {
        Scanner s = new Scanner(System.in);
        String tempName;

        System.out.println("Enter Goal Name: ");
        tempName = s.nextLine();

        for (int i = 0; i < goals.length; i++) {
            if (goals[i] == null) {
                goals[i] = new Goal(tempName);
                i = goals.length;
            }
        }
    }

    public void completeTask() {
        Scanner s = new Scanner(System.in);
        int input = 0;
        Goal temp = null;

        try {
            System.out.println("Choose goal to complete task in: ");
            input = s.nextInt();
            temp = goals[input - 1];

            try {
                System.out.println("Choose task to complete: ");
                input = s.nextInt();

                try {
                    temp.getTask(input - 1).setIsDone(true);
                    temp.checkComplete();

                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task doesn't exist");
                } catch (NullPointerException e) {
                    System.out.println("Not a valid option");
                }

            } catch (InputMismatchException e) {
                System.out.println("Input must be number.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Input must be number.");
        } catch (NullPointerException e) {
            System.out.println("Not a valid option");
        }
    }

    public void printString() {
        System.out.println("\nGoals: ");

        for (int i = 0; i < goals.length; i++) {
            if (goals[i] != null) {
                goals[i].printString(i);
            }
        }
    }
}