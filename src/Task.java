import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
     private String task_name, task_description, task_id, task_owner, task_deadline;
    private ArrayList<String> task_members = new ArrayList<>();
    private LocalDateTime last_edited_time;

    public Task(String task_name, String task_description, String task_deadline, String task_id,
            String task_owner, LocalDateTime last_edited_time) {

        this.task_name = task_name;
        this.task_description = task_description;
        this.task_deadline = task_deadline;
        this.task_members.add(task_owner);
        this.task_id = task_id;
        this.task_owner = task_owner;
        this.last_edited_time = last_edited_time;
    }

    public String getTaskName() {
        return task_name;
    }

    public String getTaskDescription() {
        return task_description;
    }

    public String getTaskDeadline() {
        return task_deadline;
    }

    public ArrayList<String> getTaskMembers() {
        return task_members;
    }

    public String getTaskId() {
        return task_id;
    }

    public String getTaskOwner() {
        return task_owner;
    }

    public LocalDateTime getLastEditedTime() {
        return last_edited_time;
    }

    public void addMember(String member) {
        task_members.add(member);
    }

    public void editTask(String taskName, String taskDesc, String deadline, String members,
            LocalDateTime lastEditedTime) {
        this.task_name = taskName;
        this.task_description = taskDesc;
        this.task_deadline = deadline;
        this.last_edited_time = lastEditedTime;
        String[] temp = members.split(",");
        task_members.clear();
        for (String member : temp) {
            task_members.add(member);
        }
        task_members.add(task_owner);
    }
}
