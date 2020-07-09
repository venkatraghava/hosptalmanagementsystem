package hospitalmanagmentsystem;

import java.util.Scanner;

public class Index {
	public static void main(String args[])
	{
		int opt;
		String username,password;
	System.out.println("choose a option from the following");
	Scanner sc=new Scanner(System.in);
	System.out.println("1.Admin");
	opt=sc.nextInt();
	if(opt==1)
	{
		System.out.println("enter username");
		username=sc.next();
		System.out.println("enter password");
		password=sc.next();
		if(username.equals("venu") && password.equals("venu123@"))
		{
			System.out.println("correct password");
			Admin a= new Admin();
			a.adminoperations();			
		}
		else
		{
			System.out.println("incorrect password");
			System.out.println("please re enter the username and passwprd");
		}
	}
	}

}
