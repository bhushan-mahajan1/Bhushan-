import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class assignment {

	public assignment() {
		
	}

	public static void main(String[] args) {
		
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/assignment","root","soham123");
			if(conn==null)
			{
				System.out.println("unable to connect...");
				System.exit(0);
			}
			else {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				while(true)
				{
					System.out.print("1.Insert Data\n2.Update\n3.Delete Data\n4.Get list\n5.Get info by id\n\nEnter your choice: ");
					int ch = Integer.parseInt(br.readLine());
					
					switch(ch)
					{
						case 1: System.out.print("Enter Student number");
								int no=Integer.parseInt(br.readLine());
								System.out.print("Enter Student Name");
								String name=br.readLine();
								System.out.print("Enter DOB");
								String dob=br.readLine();
								Date fd = formatter.parse(dob);
								java.sql.Date d1 = new java.sql.Date(fd.getTime());


								System.out.print("Enter DOJ");
								String doj=br.readLine();
								fd = formatter.parse(doj);
								java.sql.Date d2 = new java.sql.Date(fd.getTime());
								
								String qry="insert into STUDENT values(?,?,?,?)";
								PreparedStatement ps = conn.prepareStatement(qry);
								ps.setInt(1, no);
								ps.setString(2, name);
								ps.setDate(3,  d1);
								ps.setDate(4,  d2);
								
								ps.execute();
								System.out.println("Record Inserted Successfully");
								break;
								
						case 3: System.out.print("Enter Student number to DELETE data");
								int no5=Integer.parseInt(br.readLine());
								String qry1 = "delete from STUDENT where STUDENT_NO =(?)";
								PreparedStatement ps1 = conn.prepareStatement(qry1);
								ps1.setInt(1, no5);
								ps1.executeUpdate();
								System.out.print("Deleted Successfully..!!");
								break;
						
						case 4: System.out.print("Student data\n");
								Statement stmt=conn.createStatement();;
								ResultSet rs = stmt.executeQuery("select * from STUDENT");
								while(rs.next()) {
									System.out.println(rs.getInt(1));    //First Column
									System.out.println(rs.getString(2));    //Second Column
									System.out.println(rs.getDate(3));    //Third Column
									System.out.println(rs.getDate(4));
								
								}	
								break;
						
						case 5: System.out.print("Enter Student number to display data");
								int no55=Integer.parseInt(br.readLine());
								String qry11 = "select * from STUDENT where STUDENT_NO =(?)";
								PreparedStatement ps11 = conn.prepareStatement(qry11);
								ps11.setInt(1, no55);
								ResultSet rs55 = ps11.executeQuery();
								while(rs55.next()) {
									System.out.println(rs55.getInt(1));    
									System.out.println(rs55.getString(2));    
									System.out.println(rs55.getDate(3));    
									System.out.println(rs55.getDate(4));
								break;
								
								}	
								
					}
				}
			}

		
		}
		catch(Exception e)
		{
			System.out.print(e);
		}
		
	}
}

