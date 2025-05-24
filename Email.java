package emailapp;

import java.util.Scanner;
import java.io.Serializable;




public class Email implements Serializable {
    private static final long serialVersionUID = 1L; 
	private String firstName;
	private String lastName;
	private String password;
	private String department;
	private String email;
	private int mailboxCapacity = 500;
	private int defaultPasswordLength = 10;
	private String alternateEmail;
	private String companySuffix = "company.com";
	
		// To receive first and last name 
	public Email (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	//	System.out.println("Email Created: " + this.firstName + " " + this.lastName );
		
			this.department = setDepartment();
			//System.out.println("Department: " + this.department);
			
			//Call a method that returns a random password 
				this.password = randomPassword(defaultPasswordLength);
				System.out.println("Your password is: " + this.password);
				
			// combine Elements to generate email
				email= firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + department + "." + companySuffix;
				//System.out.println("Your email is:" + email);
				
	}
		// Ask department
	private String setDepartment() {
		System.out.println("New Worker: "+ firstName + " Departments Codes\n1 for Sales\n2 for Development\n3 fot Accounting\n0 for none\nEnter Depatrment Code: ");
		Scanner in = new Scanner(System.in);
		int depChoice = in.nextInt();
		if(depChoice == 1) {return "sales"; }
		else if(depChoice == 2) {return "development"; }
		else if(depChoice == 3) {return "accounting"; }
		else {return "";}
	}
		//Generate a random password
	private String randomPassword(int length){
		String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&";
		char [] password = new char[length];
		for (int i=0; i<length; i++) {
			int rand = (int) (Math.random() * passwordSet.length());
			password[i] = passwordSet.charAt(rand);
			
		}
		return new String(password);
		
	}
			// Set mailbox capacity
		public void setMailboxCapacity(int capacity) {
		this.mailboxCapacity = capacity;
	}
			// set alternate email
		public void setAlternateEmail(String altEmail) {
			this.alternateEmail = altEmail;
		}
			// change password
		public void changePassword(String password) {
			this.password = password;
		}
		
			public int getMailboxCapacity() {return mailboxCapacity;}
			public String getAlternateEmail() {return alternateEmail;}
			public String getPassword() {return password;}
			
			public String showInfo() {
				return "Display Name: " + firstName + " " + lastName + " " +
						"\nCompany Email: " + email + 
						"\nMailbox Capacity: " + mailboxCapacity + "mb";
			}
			
			// Add this to Email.java class
			public void profileMenu() {
			    Scanner scanner = new Scanner(System.in);
			    int choice;

			    do {
			        System.out.println("\n🔧 Profile Menu for " + firstName + " " + lastName);
			        System.out.println("1. Show Info");
			        System.out.println("2. Change Password");
			        System.out.println("3. Set Alternate Email");
			        System.out.println("4. Set Mailbox Capacity");
			        System.out.println("0. Exit");

			        System.out.print("Choose an option: ");
			        choice = scanner.nextInt();
			        scanner.nextLine();  // consume newline

			        switch (choice) {
			            case 1:
			                System.out.println(showInfo());
			                break;
			            case 2:
			                System.out.print("Enter new password: ");
			                String newPass = scanner.nextLine();
			                changePassword(newPass);
			                System.out.println("Password updated.");
			                break;
			            case 3:
			                System.out.print("Enter alternate email: ");
			                String alt = scanner.nextLine();
			                setAlternateEmail(alt);
			                System.out.println("Alternate email set.");
			                break;
			            case 4:
			                System.out.print("Enter new mailbox capacity (mb): ");
			                int cap = scanner.nextInt();
			                setMailboxCapacity(cap);
			                System.out.println("Mailbox capacity updated.");
			                break;
			            case 0:
			                System.out.println("Exiting profile settings.");
			                break;
			            default:
			                System.out.println("Invalid choice.");
			        }
			    } while (choice != 0);
			}
			public String getEmail() {
			    return email;
			}
			
			public boolean authenticate(String inputPassword) {
			    return this.password.equals(inputPassword);
			}


}
