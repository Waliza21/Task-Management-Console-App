
import java.util.Vector;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
// import AllUsers.java;

public class User {
    private static Vector<AllUsers> users = new Vector<>();
    private static Vector<Task> tasks = new Vector<>();
    private static Vector<String> all_members = new Vector<>();
    private static Vector<Task> user_tasks = new Vector<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int counter_id = 1;

    public static void logIn() {
        System.out.println("Enter your Username");
        String username = scanner.nextLine();
        System.out.println("Enter your password");
        String pass = scanner.nextLine();
        boolean subMenu=true;
        for (AllUsers temUser : users) {
            if (temUser.getName().equals(username) && temUser.getPass().equals(pass)) {
                while (subMenu) {
                    System.out.println();
                    System.out.println();
                    System.out.println("Choose an option");
                    System.out.println();
                    System.out.println("a.Create a New Task");
                    System.out.println("b.Delete a Task");
                    System.out.println("c.Edit a Task");
                    System.out.println("d.View My task ");
                    System.out.println("e.Share Task");
                    System.out.println("f.Log Out");

                    
                    String loginOption = scanner.nextLine();
                    switch (loginOption) {
                        case "a":
                            System.out.println("Give Task Name");
                            String task_name = scanner.nextLine();
                            System.out.println("Give Task Description");
                            String task_description = scanner.nextLine();
                            System.out.println("Give Task Deadline");
                            String task_deadline = scanner.nextLine();
                            System.out.println("Give Task Members");
                            String task_members = scanner.nextLine();
                            String[] members = task_members.split(",");
                            String task_id = "t_" + counter_id;
                            counter_id++;
                            String task_owner = temUser.getName();
                            all_members.add(task_owner);
                            LocalDateTime last_edited_time = LocalDateTime.now();
                            Task task = new Task(task_name, task_description, task_deadline, task_id,
                                    task_owner, last_edited_time);
                            for (String member : members) {
                                task.addMember(member);
                            }
                            tasks.add(task);
                            break;
                        case "b":

                            System.out.println("Give the Task ID you want to delete");
                            String taskId = scanner.nextLine();
                            String taskOwner = temUser.getName();
                            Iterator<Task> iterator = tasks.iterator();
                            boolean found = false;
                            while (iterator.hasNext()) {
                                Task tempTask = iterator.next();
                                if (tempTask.getTaskOwner().equals(taskOwner) && tempTask.getTaskId().equals(taskId)) {
                                    iterator.remove();
                                    found = true;
                                    System.out.println("The task has been successfully deleted.");
                                    break;
                                }

                            }
                            if (!found) {
                                System.out.println(
                                        "You need to be the task owner to delete the task or he Task ID doesn't exist.");
                            }
                            break;
                        case "c":

                            System.out.println("Give the Task ID you want to edit");
                            String taskID = scanner.nextLine();
                            String taskowner = temUser.getName();
                            for (Task tempTask : tasks) {
                                if (tempTask.getTaskOwner().equals(taskowner) && tempTask.getTaskId().equals(taskID)) {
                                    System.out.println("Give Task Name");
                                    String taskname = scanner.nextLine();
                                    System.out.println("Give Task Description ");
                                    String taskdescription = scanner.nextLine();
                                    System.out.println("Give Task Deadline");
                                    String taskdeadline = scanner.nextLine();
                                    System.out.println("Give Task Members");
                                    String taskmembers = scanner.nextLine();
                                    LocalDateTime lastEditedTime = LocalDateTime.now();
                                    tempTask.editTask(taskname, taskdescription, taskdeadline, taskmembers,
                                            lastEditedTime);
                                } else {
                                    System.out.println("You need to be the task owner to edit the task.");
                                }
                            }
                            break;
                        case "d":
                            System.out.println("1.View All Tasks");
                            System.out.println("2.View Task Details");
                            int viewOption = scanner.nextInt();
                            scanner.nextLine();
                            if (viewOption == 1) {
                                String viewtaskowner = temUser.getName();
                                for (Task tempTask : tasks) {
                                    ArrayList<String> temp = tempTask.getTaskMembers();
                                    for (String memb : temp) {
                                        if (memb.equals(viewtaskowner)) {
                                            {
                                                user_tasks.add(tempTask);
                                                System.out
                                                        .print("Task ID: "+tempTask.getTaskId() + "  " + "Task Name: "+tempTask.getTaskName() + "  "
                                                                + "Task Deadline: "+ tempTask.getTaskDeadline());

                                            }
                                            break;
                                        }
                                    }
                                }
                            } else if (viewOption == 2){
                                System.out.println("Give Task ID");
                                String taskid = scanner.nextLine();
                                for (Task temTask : tasks) {
                                    if (temTask.getTaskId().equals(taskid)) {
                                        System.out.print("Task Name: "+
                                                temTask.getTaskName() + "  " + "Task Description: "+temTask.getTaskDescription() + "  " +"Task Deadline: "+
                                                        temTask.getTaskDeadline() + "  " +"Task Owner: "+ temTask.getTaskOwner() + "  ");
                                        System.out.print("Task Members: ");
                                        for (String mem : temTask.getTaskMembers())
                                        {
                                            System.out.print(mem+ " ");
                                        }
                                        System.out.print("  "+"Last Edited Time: "+
                                                        temTask.getLastEditedTime());
                                    }
                                }
                            }
                            break;
                        case "e":
                            System.out.println("Give Task ID of the task to be shared");
                            String taskid = scanner.nextLine();

                            for (Task temTask : tasks) {
                                if (temTask.getTaskOwner().equals(temUser.getName())
                                        && temTask.getTaskId().equals(taskid)) {
                                    System.out.println("Give Recipient's Mail");
                                    String recipient = scanner.nextLine();
                                    for (AllUsers user : users) {
                                        if (user.getEmail().equals(recipient)) {
                                            String sharedmember = user.getName();
                                            temTask.addMember(sharedmember);
                                            System.out.println("The task has been successfully shared.");
                                            break;
                                        }
                                    }
                                }
                            }
                            break;
                        case "f":
                            subMenu=false;
                            break;
                    }
                }
            } 
        else{
                System.out.println("Wrong Credentials");
        }
    }
    }

    public static void main(String[] args) throws Exception {
        int option;
        String name, email, pass;
        boolean mainMenu=true;
        while(mainMenu)
        {
            System.out.println("1.Sign Up");
            System.out.println("2.Log In");
            System.out.println("3.Exit");
            System.out.println();
            System.out.println("Choose an option");
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                  System.out.println("Enter your Username");
                  name = scanner.nextLine();
                  System.out.println("Enter your email address");
                  email = scanner.nextLine();
                  System.out.println("Enter a unique password");
                  pass = scanner.nextLine();
                  if (email.contains("@") && email.contains(".com")) {
                      AllUsers user = new AllUsers(name, email, pass);
                      users.add(user);
                      System.out.println("You have successfully created the account.Please log in now.");
                      logIn();
                  }
              } else if (option == 2) {
                  logIn();
              }
              else if(option==3)
              {
                mainMenu=false;
              }
        }
          
        }
        

    }