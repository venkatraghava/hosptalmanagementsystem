package hospitalmanagmentsystem;

import java.util.Scanner;
import java.sql.*;
public class Admin {
	
	
	public void adminoperations()
	{
		int option;
		Scanner sc=new Scanner(System.in);
		while(true)
		{
		System.out.println("choose one of the options below");
		System.out.println("1.show doctors list");
		System.out.println("2.show patients list");
		System.out.println("3.add doctors");
		System.out.println("4.add new patient");
		System.out.println("5.show the details of doctors who treats the particular patient");
		System.out.println("6.add labdetails of the patient");
		System.out.println("7.add inpatients");
		System.out.println("8.add outpatients details");
       option=sc.nextInt();
		if(option==1)
       {
    	   showdoctors();
       }
		else if(option==2)
		{
			showpatients();
			
		}
		else if(option==3)
		{
			adddoctors();
		}
		else if(option==4)
		{
			addpatients();
		}
		else if(option==5)
		{
			detailsofpatientsdoctor();
		}
		else if(option==6)
		{
			labdetails();
		}
		else
		{
			System.out.println("please enter the correct option ");
			continue;
		}
		System.out.println("do you want to continue [Y OR N]");
		char yn=sc.next().charAt(0);
		if(yn=='n'||yn=='N')
		{
			break;
		}
		}
	}

	 static void labdetails() {
		 Connection con=null;
			PreparedStatement pstmt=null;
		    Scanner sc=new Scanner(System.in);
		    System.out.println("enter labreportno");
		    String labno=sc.nextLine();
		    System.out.println("enter patient id");
		    String pid=sc.nextLine();
           System.out.println("enter doctor id");
		    String doctorid=sc.next();
		    System.out.println("enter date of report taken");
		    String date=sc.next();
		    System.out.println("enter the bloodtype of person");
		    String bloodtype=sc.next();
		   System.out.println("enter pateint is inpatient or outpatient");
		    String patienttype=sc.next();
		   System.out.println("enter the amount to be paid for lab");
		   int amount=Integer.parseInt(sc.next());
		     try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
				     pstmt=con.prepareStatement("insert into lab values(?,?,?,?,?,?,?)");
				     pstmt.setString(1,labno);
				     pstmt.setString(2,pid);
				     pstmt.setString(3,doctorid);
				     pstmt.setString(4,date);
				     pstmt.setString(5,bloodtype);
				     pstmt.setString(6,patienttype);
				     pstmt.setInt(7, amount);
				    int res=pstmt.executeUpdate();
				    if(res>0)
				    {
				    	System.out.println("record successfully inserted");
				    }
	        	} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
	        	{
					if(con!=null)
					{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(pstmt!=null)
					{
						try {
							pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
	        	}
			
		
	}

	static void detailsofpatientsdoctor() {
		 Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
	        	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
				     stmt=con.createStatement();
				    rs=stmt.executeQuery("select doctor.doctorid,doctor.doctorname,doctor.dept,patient.pid,patient.name,patient.age,patient.weight,patient.gender,patient.address,patient.phoneno,patient.disease,lab.labno,lab.date,lab.bloodtype,lab.patienttype from doctor inner join patient on doctor.doctorid=patient.doctorid inner join lab on patient.pid=lab.pid");
				    System.out.println("DoctorId"+"\t"+"doctorname"+"\t"+"department"+"\t"+"patientid"+"\t"+"patientname\tpatientage\tweight\tgender\taddress\t\tphno\t\tdisease\t\tlabno\t\tdate\t\tblood type\t\tpatienttype");
				    while(rs.next())
				    {
				    	System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getString(4)+"\t\t"+rs.getString(5)+"\t\t"+rs.getString(6)+"\t\t"+rs.getString(7)+"\t"+rs.getString(8)+"\t"+rs.getString(9)+"\t"+rs.getString(10)+"\t"+rs.getString(11)+"\t\t"+rs.getString(12)+"\t\t"+rs.getString(13)+"\t\t"+rs.getString(14)+"\t\t"+rs.getString(15));
				    }
	        	} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
	        	{
					if(con!=null)
					{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(stmt!=null)
					{
						try {
							stmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(rs!=null)
					{
						try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
	        	}
	       
	}

	private void addpatients() {
		 Connection con=null;
			PreparedStatement pstmt=null;
		    Scanner sc=new Scanner(System.in);
		    System.out.println("enter patient id");
		    String pid=sc.nextLine();
		    System.out.println("enter patient name");
		    String pname=sc.nextLine();
		    System.out.println("enter patient age");
		    int age=sc.nextInt();
		    System.out.println("enter patient weight");
		    int weight=sc.nextInt();
		    System.out.println("enter the patient gender");
		    String gender=sc.next();
		    System.out.println("enter the address of patient");
		    String address=sc.next();
		    System.out.println("enter the phone number");
		    long phno=sc.nextLong();
		    System.out.println("enter the name of the disease patient is sufferijng with");
		    String disease=sc.next();
		    System.out.println("enter the id fo doctor who is treating thge patient");
		    String doctorid=sc.next();
		    
	        	try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
				     pstmt=con.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?)");
				     pstmt.setString(1,pid);
				     pstmt.setString(2,pname);
				     pstmt.setInt(3,age);
				     pstmt.setInt(4, weight);
				     pstmt.setString(5, gender);
				     pstmt.setString(6,address);
				     pstmt.setLong(7, phno);
				     pstmt.setString(8,disease);
				     pstmt.setString(9,doctorid);
				    int res=pstmt.executeUpdate();
				    if(res>0)
				    {
				    	System.out.println("record successfully inserted");
				    }
	        	} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally
	        	{
					if(con!=null)
					{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(pstmt!=null)
					{
						try {
							pstmt.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
	        	}
			
	}

	static void adddoctors() {
		Connection con=null;
		PreparedStatement pstmt=null;
	    Scanner sc=new Scanner(System.in);
	    System.out.println("enter doctor id");
	    String doctorid=sc.nextLine();
	    System.out.println("enter doctor name");
	    String doctorname=sc.nextLine();
	    System.out.println("enter department or specialization");
	    String dept=sc.nextLine();
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
			     pstmt=con.prepareStatement("insert into doctor values(?,?,?)");
			     pstmt.setString(1,doctorid);
			     pstmt.setString(2,doctorname);
			     pstmt.setString(3,dept);
			    int res=pstmt.executeUpdate();
			    if(res>0)
			    {
			    	System.out.println("record successfully inserted");
			    }
        	} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
        	{
				if(con!=null)
				{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(pstmt!=null)
				{
					try {
						pstmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
        	}
		
	}

	static void showpatients() {
 
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
			     stmt=con.createStatement();
			    rs=stmt.executeQuery("select * from patient");
			    System.out.println("patientid\tname\tage\tweight\tgender\taddress\tphone\tdisease\tdoctorid");
			    while(rs.next())
			    {
			    	System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4)+"\t"+rs.getString(5)+"\t "+rs.getString(6)+"\t"+rs.getLong(7)+"\t"+rs.getString(8)+"\t"+rs.getString(9));
			    }
        	} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
        	{
				if(con!=null)
				{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(stmt!=null)
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rs!=null)
				{
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
        	}
       
		
	}

	static void showdoctors() {
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
        	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "raghava143@");
			     stmt=con.createStatement();
			    rs=stmt.executeQuery("select * from doctor");
			    System.out.println("doctorid\tddoctorname\tdepartment");
			    while(rs.next())
			    {
			    	System.out.println(rs.getString(1)+"\t\t"+rs.getString(2)+"\t\t\t"+rs.getString(3));			 
			    	
			    }
        	} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
        	{
				if(con!=null)
				{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(stmt!=null)
				{
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rs!=null)
				{
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
        	}
		
	}

}
