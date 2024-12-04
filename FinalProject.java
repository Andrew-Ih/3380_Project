//Group name:21
//class name:FinalProject
//This file contains the main class and it runs the interface by creating a connction to the uranium datbase using
//our SQLServer class and run the inteface run the quries using taht class



import java.util.Scanner;

public class FinalProject {
    //main class
    public static void main(String[] args) {
        SQLServer db = new SQLServer();//creating the database Sever

        runPrompt(db);

        System.out.println("Thank you for using our database!");
    }

    //interface
    public static void runPrompt(SQLServer db) {
        Scanner console = new Scanner(System.in);
        System.out.println("/n Helloooooooooooooo ");
    }
    //functioin to check if the string has integer
    private static boolean checkifint(String check){
        try{
            Integer.parseInt(check);
            return true;
        }catch (Exception e){
            System.out.println("The number entered is not an integer returning to main menu");
            return false;
        }
    }
    //function to print all the menu for the user
    private static void printHelp(boolean admin) {
        System.out.println("Crime Statistics database");
        System.out.println("Commands:");
        System.out.println("help - Get help.");
        System.out.println("quit - Exit the program.");
        System.out.println("admin - Will be prompted to enter admin password.");
        System.out.println("victimage - For all areas, print the average victim age.");
        System.out.println("crimeagainst - Total crimes commited against males, females, or sex not provided represented as 'X'.");
        System.out.println("crimesbetween - Crimes commited on any day commited inbetween the provided times. Will be promted to enter time.");
        System.out.println("weaponuses - Display whatever weapon is used the most.");
        System.out.println("victimsperdescentinarea - Displays area code input and the total number of victims for each ethnicity. Will be prompted to enter Input.");
        System.out.println("adultarrests - Displays the number of crimes commited with the status code provided.");
        System.out.println("areasmostreported - Total crimes in an area with victims age greater then the age provided. Will be prompted to enter the age.");
        System.out.println("printcrossstreets - Shows the top 5 cross streets with the most crimes occurring.");

        if(admin){
            printAdmin();
        }
        System.out.println("");
        System.out.println("---- end help ----- ");

    }
    //function to print all the menu for the admin
    private static void printAdmin() {
        System.out.println();
        System.out.println();
        System.out.println("Admin detected! Extra commands available below:");
        System.out.println("printvictims - Print 50 of the victims info.");
        System.out.println("deletecrimerecord - Delete the crime record and all info with the provided DR_NO. Will be prompted to enter DR_NO");
        System.out.println("droptables - drop all existing tables.");
        System.out.println("Repopulate -Repopulate the table again");
    }



}