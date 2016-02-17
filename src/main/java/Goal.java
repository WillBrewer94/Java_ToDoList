import java.util.Scanner;
import java.util.InputMismatchException;

public class Goal {
    private String name;
    private Task[] tasks;
    private boolean isDone;
    private boolean isEmpty;

    public Goal(String name, String[] taskList) {
        tasks = new Task[10];
        this.name = name;

        for (int i = 1; i < taskList.length; i++) {
            tasks[i - 1] = new Task(taskList[i]);
        }
    }

    public Goal(String name) {
        this.name = name;
        tasks = new Task[10];
        isDone = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Task getTask(int index) {
        return tasks[index];
    }

    public Task[] getTaskList() {
        return tasks;
    }

    public int getTaskLength() {
        return tasks.length;
    }

    public void setTask() {
        //empty for now
    }

    public void addTask() {
        Scanner s = new Scanner(System.in);
        String taskName;
        int priority;
        String date;
        boolean isTaskDone = false;

        System.out.println("Enter Task Name: ");
        taskName = s.nextLine();

        System.out.println("Enter Priority (1 = high, 2 = medium, 3 = low): ");
        try {
            priority = s.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Input not recognized, set to low");
            priority = 3;
        }

        s.nextLine();

        System.out.println("Enter Due Date: ");
        date = s.nextLine();

        Task temp = new Task(taskName, priority, date, isTaskDone);

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = temp;
                i = tasks.length;
            }
        }
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void checkComplete() {
        int numTasks = 0;
        int numComplete = 0;

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                numTasks++;

                if (tasks[i].getIsDone()) {
                    numComplete++;
                }
            }
        }

        if (numTasks == numComplete) {
            isDone = true;
        }
    }

    public void printString(int goalNum) {
        String isDoneText = "";

        if (isDone) {
            isDoneText = "Complete";
        } else {
            isDoneText = "Incomplete";
        }

        System.out.println("[" + (goalNum + 1) + "]"
                               + name + " - " + isDoneText);

        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                tasks[i].printString(i);
            }
        }
    }
}