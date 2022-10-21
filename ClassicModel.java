import java.sql.*;
import java.util.Scanner;


public class ClassicModel {
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        Scanner scan = new Scanner(System.in);

        System.out.println("Which product line would you like to see: ships, classic cars, or motorcycles?");

        String answer = scan.nextLine();


        //try catch block to catch database exceptions
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");

        //creates new sql statement using connection
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();

            // Produces 3 Different Queries based on the specific product line
            ResultSet classic = stmt.executeQuery("SELECT productName FROM products WHERE productLine = \"Classic Cars\"");
            ResultSet ships = stmt2.executeQuery("SELECT productName FROM products WHERE productLine = \"Ships\"");
            ResultSet mcycle = stmt3.executeQuery("SELECT productName FROM products WHERE productLine = \"Motorcycles\"");


           // stmt2.executeUpdate("INSERT INTO PRODUCTS \n" + "(productCode, productName, productLine, productScale, productVendor, productDescription, quantityInStock, buyPrice, MSRP)\n" + "VALUES \n" + "(\"S72_3213\",\"USS Monitor\", \"Ships\", \"1:72\", \"Second Gear Diecast\", \"Ironclad warship with her steam engine and revolving turret\", 780, 35.00, 55.00);\n");
           // stmt2.executeUpdate("DELETE FROM products WHERE productName = \"USS Monitor\"");


            //IF STATEMENTS FOR CHOOSING WHICH QUERY TO PRINT BASED ON USER INPUT (STRING)
            if(answer.equals("classic cars")) {

                while (classic.next()) {
                    System.out.println(classic.getString(1));
                }
            }

            else if(answer.equals("ships")){
                while(ships.next()){
                    System.out.println(ships.getString(1));
                }

            }

            else if(answer.equals("motorcycles")){
                while(mcycle.next()){
                    System.out.println(mcycle.getString(1));
                }
            }

            con.close();
        } catch (Exception a){
            System.out.println(a);
        }



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "admin");


            System.out.println("Enter a product code!");
            String userCodeInput = scan.nextLine();

            //creates new sql statement using connection
            Statement stmt = con.createStatement();


            // Produces 3 Different Queries based on the specific product line
            ResultSet all = stmt.executeQuery("SELECT * FROM products WHERE productCode = '" + userCodeInput + "'");

            while(all.next()){
                System.out.println(all.getString(1) + " " + all.getString(2) + " " + all.getString(3) + " " + all.getString(4) );
            }



            con.close();
        } catch (Exception a){
            System.out.println(a);
        }
    }

}
