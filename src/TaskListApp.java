import java.util.*;

/*
 * @ David Breitenbeck 
 */

public class TaskListApp {
	static ArrayList<Task> taskList = new ArrayList<Task>();
	static boolean end = false;
	static String dateRegex = "\\d{2}/\\d{2}/\\d{4}";

	public static void main(String[] args) {

		Task t1 = new Task("Ted", "Make task list program", "10/12/2020");
		Task t2 = new Task("Jim", "Test task list program", "10/13/2020");
		Task t3 = new Task("Sally", "Break task list program", "10/15/2020");
		Task t4 = new Task("Ted", "Fix task list program", "10/16/2020");
		Task t5 = new Task("Bill", "Change task list program", "11/01/2020");
		Task t6 = new Task("Ted", "Remake task list program. Question own existence", "11/02/2020");
		taskList.add(t1);
		taskList.add(t2);
		taskList.add(t3);
		taskList.add(t4);
		taskList.add(t5);
		taskList.add(t6);
		System.out.println("Welcome to the Task List Manager:");
		
		do {
//			showMenu();
			menuSelect();
		} while (!end);

		System.out.println("Goodbye");

	}

//Menu Select Tool
	public static void menuSelect() {
		showMenu();
		System.out.println();
		int a = getInt("What would you like to do next? \n");
		switch (a) {
		case 1: {
			showList();
			menuSelect();
			break;
		}
		case 2: {
			Task T = addTask();
			taskList.add(T);
			System.out.println("Task added");
			menuSelect();
			break;
		}
		case 3: {
			deleteTask();
			menuSelect();
			break;
		}
		case 4: {
			compTask();
			menuSelect();
			break;
		}
		case 5: {
			searchName();
			menuSelect();
			break;
		}
		case 6: {
			searchDate();
			menuSelect();
			break;
		}
		case 7: {
			editTask();
			menuSelect();
			break;
		}
		case 8: {
			end = quit();
			break;
		}
		}
	}

//1
	public static void showList() {
		System.out.println("Task #\t" + "Teammember:\t" + "Due Date:\t" + "Complete?\t " + "Task Description:");
		System.out.println("======");
		int i = 1;
		for (Task t : taskList) {
			String name = t.getTMName();
			String date = t.getDueDate();
			String complete;
			boolean comp = t.getComplete();
			if (comp) {
				complete = "Yes";
			} else {
				complete = "No";
			}
			String description = t.getTaskDescription();
			System.out.print(i + ". ");
			System.out.println("\t" + name + "\t\t" + date + "\t\t" + complete + "\t\t" + description);
			i++;
		}
		if (!yesNo("Return to menu?")) {
			showList();
		}
	}

// 2
	public static Task addTask() {
		Scanner scan = new Scanner(System.in);
		String tName = Validator.getString(scan, "Enter Team Member name: ");
		String taskDes = Validator.getString(scan, "Enter Task Description: ");
		String dueDate = Validator.getStringMatchingRegex(scan, "Enter due date: ", dateRegex);
		Task task = new Task(tName, taskDes, dueDate);

		return task;
	}

//3
	public static void deleteTask() {
		Scanner scan = new Scanner(System.in);
		int a = pickTask(scan, "Which task would you like to delete?");
		if (!(a < 0)) {
			showTask(taskList.get(a));
			boolean sure = yesNo("Are you sure you want to delete this task?");
			if (sure) {
				Task t = taskList.get(a);
				taskList.remove(t);
				System.out.println("Task deleted.");
				System.out.println();
			}
			System.out.println();
			if (yesNo("Would you like to delete another task?")) {
				deleteTask();
			}
		}
	}

//4
	public static void compTask() {
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		int a = pickTask(scanner, "Which task would you like to mark complete?");
		if (!(a < 0)) {
			showTask(taskList.get(a));
			if (yesNo("Are you sure this task is complete?")) {
				Task t = taskList.get(a);
				t.setComplete(true);
				System.out.println("Task completed");

				System.out.println();
				if (yesNo("Would you like to mark another task complete?")) {
					compTask();
				}
			}
		}
	}

//5
	public static void searchName() {
		Scanner scanner = new Scanner(System.in);
		String name = Validator.getString(scanner, "Enter the name you wish to search (enter '0' to cancel)\n").trim()
				.toLowerCase();
		if (!name.contentEquals("0")) {
			for (Task t : taskList) {
				String tMN = t.getTMName().toLowerCase();
				if (name.contentEquals(tMN)) {
					showTask(t);
				}
			}
			System.out.println();
			if (yesNo("Would You like to search another name?")) {
				searchName();
			}
		}

	}

//6
	public static void searchDate() {
		Scanner scan = new Scanner(System.in);
		String date = Validator.getStringMatchingRegex(scan, "Enter Date: mm/dd/yyyy \n", dateRegex).trim();
		DateSearcher d = new DateSearcher();
		for (Task t : taskList) {
			String dueDate = t.getDueDate();
			if (d.dateOneBigger(date, dueDate)) {
				showTask(t);
			}
		}
		System.out.println();
		if (yesNo("Would You like to search another date?")) {
			searchDate();
		}
	}

//7
	public static void editTask() {
		Scanner scanner = new Scanner(System.in);
		int t = pickTask(scanner, "Which task would you like to edit?");
		if (!(t < 0)) {
			showTask(taskList.get(t));
			System.out.println("You can edit:");
			System.out.println("1. The Teammember Name");
			System.out.println("2. The Task Description");
			System.out.println("3. The Due Date");
			int j = Validator.getInt(scanner, "Which would you like to edit? (press 0 to cancel)", 0, 3);
			if (!(j < 1)) {
				switch (j) {
				case 1: {
					System.out.println();
					String tm = Validator.getString(scanner, "Enter new Teammember name:\n");
					taskList.get(t).setTMName(tm);
					break;
				}
				case 2: {
					String tm = Validator.getString(scanner, "Enter new Task Description:\n");
					taskList.get(t).setTaskDescription(tm);
					break;
				}
				case 3: {
					String tm = Validator.getStringMatchingRegex(scanner, "Enter new Due Date:\n", dateRegex);
					taskList.get(t).setDueDate(tm);
					break;
				}
				}
				System.out.println("Okay, the task now reads:");
				showTask(taskList.get(t));
				if (yesNo("Would You like to edit anything else?")) {
					editTask();
				}
			}
		}
	}

//8
	public static boolean quit() {
		Scanner scanner = new Scanner(System.in);
		boolean quit = yesNo("Are you sure you want to quit?");

		return quit;
	}

//shows menu options
	public static void showMenu() {
		System.out.println();
		System.out.println("Choose from the Following Options:");
		System.out.println("----------------------------------");
		System.out.println("1. List Tasks");
		System.out.println("2. Add Task");
		System.out.println("3. Delete Task");
		System.out.println("4. Mark Task as Complete");
		System.out.println("5. Search by Teammember Name");
		System.out.println("6. Display All Tasks Due Before a Certain Date");
		System.out.println("7. Edit Task");
		System.out.println("8. Quit");
	}

//Multipurpose boolean method	
	public static boolean yesNo(String prompt) {
		Scanner scanner = new Scanner(System.in);
		boolean yes = false;
		String y = Validator.getStringMatchingRegex(scanner, prompt + "[y/n]\n", "[y,n]").trim().toLowerCase();
		if (y.contentEquals("y")) {
			yes = true;
		}
		return yes;
	}

//get the integer to run the menu
	public static int getInt(String prompt) {
		Scanner scanner = new Scanner(System.in);
		int t = Validator.getInt(scanner, prompt, 1, 8);
		return t;
	}

//Display individual task
	public static void showTask(Task t) {
		String name = t.getTMName();
		String date = t.getDueDate();
		boolean comp = t.getComplete();
		String complete;
		if (comp) {
			complete = "Yes";
		} else {
			complete = "No";
		}
		String description = t.getTaskDescription();
		System.out.println("\t" + "Teammember:\t" + "Due Date:\t" + "Complete?\t " + "Task Description:");
		System.out.println("\t" + name + "\t\t" + date + "\t\t" + complete + "\t\t" + description);
	}

//Select individual task
	public static int pickTask(Scanner scan, String prompt) {
		int choice = Validator.getInt(scan, prompt + "(press 0 to cancel)", 0, taskList.size());
		if (choice == 0) {
			return -1;
		} else {
			choice -= 1;
		}
		return choice;
	}

}
