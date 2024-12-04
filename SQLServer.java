//Group name:21
//class name:SQLServer
//This Java class, named SQLServer, facilitates interaction with a SQL Server database.
// It includes methods for dropping and creating tables,
// executing various SQL queries related to crime data analysis,
// and reading SQL scripts to populate the database.The connection to the
//uranium server is done in the constructor

import java.io.*;
import java.sql.*;
import java.util.Properties;




public class SQLServer {


    private Connection connection;//connection to the database




    //query1
    public void arrests() {
        try {



            String sql = "SELECT * FROM drivers";


            PreparedStatement stmt = connection.prepareStatement(sql);

            // Bind the user input to the placeholder in the query


            // Execute the query
            ResultSet set = stmt.executeQuery();


//            // Print table header
//            System.out.println("+----------+-------------+");
//            System.out.printf("| %-8s | %-11s |\n", "AreaCode", "Num Arrests");
//            System.out.println("+----------+-------------+");
//
//            // Print table rows
            while (set.next()) {
                int areaCode = set.getInt("statusId");
                String desc=set.getString("status");
                System.out.println(areaCode + "    :   " +desc );
            }
//            System.out.println("+----------+-------------+");

            // Close resources
            set.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void readTable() {



        String[] sqlFiles = {
//                "SQLFILES/create_and_drop_tables.sql",
//                "SQLFILES/circuits.sql",
//                "SQLFILES/constructors.sql",
//                "SQLFILES/drivers.sql",
//                "SQLFILES/status.sql",
//                "SQLFILES/seasons.sql",
//                "SQLFILES/races.sql",
//                "SQLFILES/constructor_results.sql",
//                "SQLFILES/constructor_standings.sql",
//                "SQLFILES/driver_standings.sql",
//                "SQLFILES/results.sql",            //che
//                "SQLFILES/lap_times.sql",
//                "SQLFILES/pit_stops.sql",
//                "SQLFILES/qualifying.sql",
                //"SQLFILES/sprint_results.sql"
        };

        for (String file : sqlFiles) {
            readsql( file); // Call non-static method on the instance
        }



    }
    //function to read sql statements from the sql files
    private void readsql(String st){ try {
        BufferedReader reader1 = new BufferedReader(new FileReader(st));
        String line = reader1.readLine();
        // assumes each query is its own lin
        System.out.println("Loading database:"+st+" /n");
        int count = 0;
        while (line != null) {

            System.out.println(line);
            this.connection.createStatement().execute(line);
            line = reader1.readLine();
            count++;
        }
        System.out.println("Database successfully loaded!");
        reader1.close();
    } catch (IOException var7) {
        var7.printStackTrace();
    } catch (SQLException var8) {
        var8.printStackTrace(System.out);
        System.out.println();
    }
    }


    // class constructor
    //connects to the university of manitoba uranium server using the cfg file given
    public SQLServer() {
        Properties var1 = new Properties();
        String var2 = "auth.cfg";

        try {
            FileInputStream var3 = new FileInputStream(var2);
            var1.load(var3);
            var3.close();
        } catch (FileNotFoundException var9) {
            System.out.println("Could not find config file.");
            System.exit(1);
        } catch (IOException var10) {
            System.out.println("Error reading config file.");
            System.exit(1);
        }

        String username = (var1.getProperty("username"));
        String password = (var1.getProperty("password"));
        if (username == null || password == null) {
            System.out.println("Username or password not provided.");
            System.exit(1);
        }

        String var5 = "jdbc:sqlserver://uranium.cs.umanitoba.ca:1433;database=cs3380;user=" +  username + ";password=" + password + ";encrypt=false;trustServerCertificate=false;loginTimeout=30;";
        Object var6 = null;

        try {
            this.connection = DriverManager.getConnection(var5);
            Statement var7 = this.connection.createStatement();
            System.out.println("check");


            readTable();
            arrests();

            System.out.println("Database loading");

            System.out.println("Database succesfully loaded");
        } catch (Exception var8) {
            System.out.println("Issue connecting to uranium");
            var8.printStackTrace();
        }

    }
}