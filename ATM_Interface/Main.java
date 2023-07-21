import java.util.*;

public class Main {
    public static void main(String[] args) {
    	String userFileName = "users.txt"; 
        ATM atm = new ATM();

        Scanner sc = new Scanner(System.in);
        
        System.out.println("\t\t\t---------------------------------");
		System.out.println("\t\t\t-------------- ATM --------------");
		System.out.println("\t\t\t---------------------------------");
		System.out.println("\t\t\t\t      Hello!\n");
		System.out.println("\t\t\t1. Login\n");
		System.out.println("\t\t\t2. New User\n");
		System.out.print("\t\t\tChoice: ");
		int choice = sc.nextInt();
		
		User currUser = null;
		
		switch(choice)
		{
		case 1:
			break;
		case 2:
			int f;
			do
			{
				f = 1;
				System.out.print("\t\t\tEnter your user id: ");
				int uid = sc.nextInt();
				System.out.print("\n\t\t\tEnter your pin: ");
				int upin = sc.nextInt();
				currUser = new User(uid,upin);
				ArrayList<User> list = atm.loadUsersFromFile("users.txt");
				for(User user : list) 
	            {
	                if(user.getUid() == uid)
	                {
	                    System.out.println("\t\t\tUser with same ID already exists! Enter again!");
	                    f = 0;
	                    break;
	                }
	            }
				
			}while(f != 1);
			
			atm.addUser(currUser);
			break;
		}
		System.out.println("\t\t\t---------------------------------");

        atm.saveUsersToFile(userFileName);

        atm.run(currUser);
        
        sc.close();
    }
}
