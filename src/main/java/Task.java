public class Task {
    private String name;
    private String priority;
    private String dueDate;
    private boolean isDone;

    public Task(String taskLine) {
        String[] split = taskLine.split("\\|");

        name = split[0];

        if (split[1].equals("1")) {
            priority = "High Priority";
        } else if (split[1].equals("2")) {
            priority = "Medium Priority";
        } else {
            priority = "Low Priority";
        }

        dueDate = split[2];

        if (split[3].equals("true")) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    public Task(String name, int priorityInt, String dueDate, boolean isDone) {
        this.name = name;

        if (priorityInt == 1) {
            priority = "High Priority";
        } else if (priorityInt == 2) {
            priority = "Medium Priority";
        } else {
            priority = "Low Priority";
        }

        this.dueDate = dueDate;
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        String temp = "";

        if (priority.equals("High Priority")) {
            temp = "1";
        } else if (priority.equals("Medium Priority")) {
            temp = "2";
        } else {
            temp = "3";
        }

        return temp;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return dueDate;
    }

    public void setDate(String dueDateIn) {
        dueDate = dueDateIn;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void printString(int taskNum) {
        String isDoneText;
        String priorityText = "";

        if (isDone) {
            isDoneText = "Complete";
        } else {
            isDoneText = "Incomplete";
        }

        System.out.println("\t[" + (taskNum + 1) + "]- " + name + " - "
                                 + priority + " - "
                                 + dueDate + " - " + isDoneText);
    }
}