import java.io.*;
import java.util.*;

public class ATM {
    private ArrayList<User> userList;
    
    private ArrayList<Transaction> transactionHistory;
    
    public ATM() {
        userList = (loadUsersFromFile("users.txt") != null)? loadUsersFromFile("users.txt"):new ArrayList<User>();
        transactionHistory = new ArrayList<Transaction>();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public void noteTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }
    
    public void saveUsersToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) 
        {
            Set<Integer> userIdSet = new HashSet<>();
            for(User user : userList) 
            {
                if(userIdSet.add(user.getUid()))
                {
                    writer.println(user.getUid() + "," + user.getPin());
                }
            }
        }
        catch (IOException e) 
        {
            System.out.println("Error saving user data to file: " + e.getMessage());
        }
    }
    
    public ArrayList<User> loadUsersFromFile(String fileName) {
		ArrayList<User> list = null; 
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) 
		{
			String line;
			list = new ArrayList<User>();
		
		    while ((line = reader.readLine()) != null) 
		    {
		    	String userData[] = line.split(",");
		        User user = new User(Integer.parseInt(userData[0]),Integer.parseInt(userData[1]));
		        list.add(user);
		    }
		} 
		catch (IOException e)
		{
			System.out.println("Error retrieving user data to file: " + e.getMessage());
		}
    	return list;
    }
    
    void displayHistory(Transaction transaction,User currentUser) {
    	System.out.println("\t\t\tUser: "+ currentUser.getUid());
    	System.out.println("\t\t\tType: "+ transaction.getTransactionType());
    	System.out.println("\t\t\tAmount for transaction: Rs. "+ transaction.getAmountUsed());
    	System.out.println("\t\t\tBalance: Rs. "+ transaction.getBalance());
    	System.out.println("\t\t\tDate: "+ transaction.getDate());
    }
    
    void withdraw(User user,double amt) {
    	Transaction newT;
    	if(amt > user.getAmount())
    	{
    		System.out.println("\n\t\t\tInsufficient money in Account!");
    		newT = new Transaction("Failed withdrawal", amt, user.getAmount(), new Date());
    		transactionHistory.add(newT);
    		return;
    	}
    	
    	user.setAmount(user.getAmount() - amt);
    	newT = new Transaction("Withdraw", amt, user.getAmount(), new Date());
    	transactionHistory.add(newT);
    	System.out.println("\n\t\t\tRs. "+amt+" withdrawn successfully!");
    	
    }
    
    void deposit(User user,double amt) {
    	Transaction newT;
    	if(amt <= 0)
    	{
    		System.out.println("\n\t\t\tInvalid amount!");
    		newT = new Transaction("Failed deposit", amt, user.getAmount(), new Date());
    		transactionHistory.add(newT);
    		return;
    	}
    	
    	user.setAmount(user.getAmount() + amt);
    	newT = new Transaction("Deposit", amt, user.getAmount(),new Date());
    	transactionHistory.add(newT);
    	System.out.println("\n\t\t\tRs. "+amt+" deposited successfully!"); 
    }
    
    void transfer(User user1,User user2,double amt) {
    	Transaction newT;
    	
    	if(amt > user1.getAmount())
    	{
    		System.out.println("\n\t\t\tInsufficient money in Account!");
    		newT = new Transaction("Failed tranfer to user "+ user2.getUid(), amt, user1.getAmount(), new Date());
    		transactionHistory.add(newT);
    		return;
    	}
    	
    	user1.setAmount(user1.getAmount() - amt);
    	user2.setAmount(user2.getAmount() + amt);
    	newT = new Transaction("Transfer to user "+user2.getUid(), amt, user1.getAmount(),new Date());
    	transactionHistory.add(newT);
    	System.out.println("\n\t\t\tTransferred Rs. "+amt+" to "+user2.getUid()+" successfully!");
    }
    
    public void displayBalance(User user) {
    	System.out.println("\t\t\tUser: "+ user.getUid());
    	System.out.println("\t\t\tBalance: Rs. "+ user.getAmount());
    }
    
    public void run(User currUser) {
        
    	int choice = 0;
    	char c = 'Y';
		User currentUser = null;
		
    	Scanner sc = new Scanner(System.in);
		if(currUser == null)
		{
	    	System.out.println("\t\t\t------------ Welcome! -----------");
	    	choice = 0;
			currentUser = null;
			do
			{
				System.out.print("\t\t\tEnter your user id: ");
				int uid = sc.nextInt();
				System.out.print("\n\t\t\tEnter your pin: ");
				int upin = sc.nextInt();
				
				for (User user : userList) {
		            if (user.getUid() == uid && user.getPin() == upin) {
		                currentUser = user;
		                break;
		            }
		        }
				
				if(currentUser == null)
				{
					System.out.println("\n\t\t\tInvalid ID or pin!");
					System.out.print("\n\t\t\tDo you want to continue?(Y/N) ");
					c = sc.next().charAt(0);
				}
				
			}while(currentUser == null && (c != 'N' && c != 'n'));
		}
		else
		{
			currentUser = currUser;
		}
		
		if(c == 'N' || c == 'n')
		{
			System.out.println("\t\t\t---------------------------------");
			System.out.println("\t\t\t\t    Thank you!");
			System.out.println("\t\t\t---------------------------------");
			sc.close();
			return;
		}
		
		System.out.println("\t\t\t---------------------------------");
		System.out.println("\n\t\t\tWelcome, User " + currentUser.getUid() + "!\n");
		
		do
		{
			System.out.println("\t\t\t---------------------------------");
			System.out.println("\t\t\t1. Transaction History\n");
			System.out.println("\t\t\t2. Withdraw\n");
			System.out.println("\t\t\t3. Deposit\n");
			System.out.println("\t\t\t4. Tranfer\n");
			System.out.println("\t\t\t5. Check Balance\n");
			System.out.println("\t\t\t6. Quit\n");
			System.out.print("\t\t\tChoice: ");
			choice = sc.nextInt();
			System.out.println("\t\t\t---------------------------------");
			
			switch(choice)
			{
			case 1:
				for(int i=0;i<transactionHistory.size();i++)
				{
					displayHistory(transactionHistory.get(i),currentUser);
					System.out.println("\t\t\t*********************************");
				}
				break;
				
			case 2:
				System.out.print("\t\t\tEnter the amount to be withdrawn: ");
				double amt = sc.nextDouble();
				withdraw(currentUser,amt);
				break;
				
			case 3:
				System.out.print("\t\t\tEnter the amount to be deposited: ");
				double amt1 = sc.nextDouble();
				deposit(currentUser,amt1);
				break;
				
			case 4:
				User recepient = null;
				char ch = 'y';
				do
				{
					System.out.print("\t\t\tEnter the user ID of the recepient user: ");
					int recepientID = sc.nextInt();
					
					
					for (User user : userList)
					{
			            if (user.getUid() == recepientID && recepientID != currentUser.getUid()) 
			            {
			                recepient = user;
			                break;
			            }
			        }
					
					if(recepient == null)
					{
						System.out.println("\n\t\t\tInvalid ID!");
						System.out.print("\n\t\t\tDo you want to continue?(Y/N) ");
						ch = sc.next().charAt(0);
					}
					
				}while(recepient == null && (ch == 'Y' || ch == 'y'));
				
				if(recepient != null)
				{	System.out.print("\n\t\t\tEnter the amount to be transferred: ");
					double amt2 = sc.nextDouble();
					transfer(currentUser,recepient,amt2);
				}
				
				break;
		
			case 5:
				displayBalance(currentUser);
				break;
			
			case 6:
				System.out.println("\t\t\t\t    Thank you!");
				System.out.println("\t\t\t---------------------------------");
				break;
				
			default:
				System.out.println("\t\t\tInvalid Choice!");
			}
		
		}while(choice != 6);
		
		sc.close();
    }
}
