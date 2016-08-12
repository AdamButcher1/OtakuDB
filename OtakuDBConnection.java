/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Integer;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;



public class OtakuDBConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException 
    {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=OtakuDB;userName=sa;password=hi";
        Connection con = DriverManager.getConnection(connectionURL);
        Statement stmt = con.createStatement();
        String sql;

        OtakuDBGUI otaku = new OtakuDBGUI();
        otaku.setVisible(true);
        System.out.println("Connected!");
        sql = "SELECT * from Anime;";

        printAnime(stmt);
        System.out.println();
        int userSelection = optionsSelect();

        while (userSelection != 0) 
        {
            switch (userSelection) {
                case 0:
                    break;
                case 1:
                    System.out.println();
                    insertAnime(stmt);
                    printAnime(stmt);
                    break;
                case 2:
                    System.out.println();
                    deleteAnime(stmt);
                    printAnime(stmt);
                    break;
                case 3:
                    System.out.println();
                    updateAnime(stmt);
                    printAnime(stmt);
                    break;

                default:

            }

            userSelection = optionsSelect();
        }

    }

    public static ArrayList<Anime> getAnimeList() 
    {
        //ArrayList to hold the anime from the DB 
        ArrayList<Anime> anime = new ArrayList<Anime>();

        return anime;

    }

    public static void printAnime(Statement stmt) throws ClassNotFoundException, SQLException 
    {
        String sql;
        sql = "SELECT * from Anime;";
        ResultSet result = stmt.executeQuery(sql);

        System.out.printf("%-40s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "Anime Name", "Director", "Studio", "Writer", "Start Date", "End Date", "Num of Episdes", "Related Manga");
        System.out.printf("%-40s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", "----------", "--------", "------", "------", "----------", "--------", "--------------", "-------------");

        while (result.next()) {
            String name = result.getString("AnimeName");
            String Director = result.getString("Director");
            String Studio = result.getString("Studio");
            String Writer = result.getString("Writer");
            Date StartDate = result.getDate("StartDate");
            Date EndDate = result.getDate("EndDate");
            Integer Episodes = result.getInt("Episodes");
            String RelatedManga = result.getString("RelatedManga");

            System.out.printf("%-40s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", name, Director, Studio, Writer, StartDate, EndDate, Episodes, RelatedManga);
        }
    }

    public static int optionsSelect() 
    {

        Scanner kb = new Scanner(System.in);
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Update");
        System.out.println("0. Exit");
        System.out.print("Selection: ");

        int selection = kb.nextInt();

        while (selection > 3 || selection < 0) {
            System.out.println();
            System.out.println("Try another selection");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Update");
            System.out.println("0. Exit");
            System.out.print("Selection: ");
            selection = kb.nextInt();
            System.out.println();
            System.out.println();
        }

        return selection;
    }

    //come back to fix the date on this yo
    public static void insertAnime(Statement stmt) throws ClassNotFoundException, SQLException, ParseException 
    {
        System.out.println("You have selected to insert an anime to the database!");

        // Variables that will hold values of attributes in database when updating
        String sql = null;
        String animeName = null;
        String director = null;
        String studio = null;
        String writer = null;
        Date startDate = null;
        String startDateString = null;
        Date endDate = null;
        String endDateString = null;
        Integer numOfEpisodes = null;
        String relatedManga = "null";

        Scanner kb = new Scanner(System.in);
        System.out.print("Insert Name of Anime: ");
        animeName = kb.nextLine();

        while (animeName == null || animeName == "") {
            System.out.println("You must enter an anime name");
            System.out.print("Insert Name of Anime: ");
            animeName = kb.nextLine();
        }

        System.out.print("Insert Name of Director: ");
        director = kb.nextLine();

        System.out.print("Insert Name of Studio: ");
        studio = kb.nextLine();

        System.out.print("Insert Name of Writer: ");
        writer = kb.nextLine();

        //FIX THE DATE PROBLEM!!!!!!!!!!!!
        System.out.print("Insert Start Date: ");
        startDateString = (kb.nextLine());



        System.out.print("Insert End Date: ");
        endDateString = kb.nextLine();


        //FIX THE DATE PROBLEM!!!!!!!!!!!!

        System.out.print("Insert Number of Episode: ");
        numOfEpisodes = kb.nextInt();
        kb.nextLine();
        //End Date problem. might need to change from date data type to string?????
        System.out.print("Insert Name of Related Manga: ");
        relatedManga = kb.nextLine();

        //Still need to figure out date problem and how to solve it.
        sql = "INSERT INTO ANIME VALUES ('" + animeName + "', '" + director + "', '" + studio + "', '" + writer + "' ,'" + startDateString + "' ,'" + endDateString + "' ," + numOfEpisodes + ", '" + relatedManga + "');";

        //Debug
        System.out.println(sql);

        stmt.executeUpdate(sql);
        System.out.println();
        printAnime(stmt);

    }

    public static void deleteAnime(Statement stmt) throws ClassNotFoundException, SQLException, ParseException 
    {

        Scanner kb = new Scanner(System.in);

        String animeToDelete;
        String sqlQuery;

        System.out.println("You have selected that you would like to delete an anime from the database");
        System.out.print("Anime To Delete: ");
        animeToDelete = kb.nextLine();

        sqlQuery = "Delete from Anime where AnimeName = '" + animeToDelete + "';";

        stmt.executeUpdate(sqlQuery);
        System.out.println();
        printAnime(stmt);

    }

    public static void updateAnime(Statement stmt) throws ClassNotFoundException, SQLException, ParseException 
    {
        while (true)
        {
            
            // I can try to wait and see if something is returned from the database. If I dont get a response, if I dont get anything then catch exception
            try 
            {// Being Main try

            Scanner kb = new Scanner(System.in);
            String animeToUpdate;
            String updateString;
            ResultSet result;
            int updateInt;
            String sqlQuery;
            int updateSelection;
            System.out.print("What anime would you like to update (0 Returns to Menu): ");
            animeToUpdate = kb.nextLine();
            System.out.println();
            
            
            
            // Used to see if the anime is actually in the database or not. Doesnt work right now
            sqlQuery = "UPDATE Anime SET AnimeName = '" + animeToUpdate + "' WHERE AnimeName = '" + animeToUpdate + "';";
            stmt.executeUpdate(sqlQuery);
            
            if (animeToUpdate.equals("0"))
            {
                System.out.println();
                return;
            }
            
            
            


            System.out.println("0.Exit");
            System.out.println("1.AnimeName");
            System.out.println("2.Director");
            System.out.println("3.Studio");
            System.out.println("4.Writer");
            System.out.println("5.StartDate");
            System.out.println("6.EndDate");
            System.out.println("7.NumOfEpisodes");
            System.out.println("8.RelatedManga");
            System.out.print("What would like to update: ");
            
            updateSelection = kb.nextInt();
            kb.nextLine();  // Buffer Clear
            System.out.println();
            
            

            while (updateSelection < 0) 
            {
                System.out.println("Incorrect selection. Try again.");
                System.out.println("0.Exit");
                System.out.println("1.AnimeName");
                System.out.println("2.Director");
                System.out.println("3.Studio");
                System.out.println("4.Writer");
                System.out.println("5.StartDate");
                System.out.println("6.EndDate");
                System.out.println("7.NumOfEpisodes");
                System.out.println("8.RelatedManga");
                System.out.print("What would like to update: ");
                updateSelection = kb.nextInt();
                System.out.println();

            }
            
            
            

            switch (updateSelection) 
            {//start Switch
                case 0:
                    return;
                case 1:                 
                    System.out.print("Enter New Anime Name: ");
                    updateString = kb.nextLine();

                    while (updateString == null || updateString == "") {
                        System.out.println("This field cannot be left blank!");
                        System.out.println("Enter New Anime Name");
                        updateString = kb.nextLine();
                    }

                    sqlQuery = "UPDATE Anime SET AnimeName = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Enter New Director Name: ");
                    updateString = kb.nextLine();
                    sqlQuery = "UPDATE Anime SET Director = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter New Studio Name: ");
                    updateString = kb.nextLine();
                    sqlQuery = "UPDATE Anime SET Studio = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter New Writer Name: ");
                    updateString = kb.nextLine();
                    sqlQuery = "UPDATE Anime SET Writer = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Enter New Start Date Name: ");
                    updateString = kb.nextLine();
                    sqlQuery = "UPDATE Anime SET StartDate = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 6:
                    System.out.print("Enter New End Date Name: ");
                    updateString = kb.nextLine();
                    sqlQuery = "UPDATE Anime SET EndDate = '" + updateString + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 7:
                    System.out.print("Enter New Number Of Episodes: ");
                    updateInt = kb.nextInt();
                    sqlQuery = "UPDATE Anime SET Episodes = " + updateInt + "' WHERE AnimeName = '" + animeToUpdate + "';";
                    stmt.executeUpdate(sqlQuery);
                    System.out.println();
                    break;
                case 8:
                    try {
                        System.out.print("Enter New Related Manga: ");
                        updateInt = kb.nextInt();
                        sqlQuery = "UPDATE Anime SET RelatedManga = '" + updateInt + "' WHERE AnimeName = '" + animeToUpdate + "';";
                        stmt.executeUpdate(sqlQuery);
                        System.out.println();
                        break;
                    } catch (Exception e) {
                        System.out.println("The entered manga is not in the Manga Table");
                        System.out.println();
                    }
                    break;

            }//End switch
            
            
            printAnime(stmt);
        
        }//End Main try
            
            catch (SQLException e) 
            {
                System.out.println();
                System.out.println("You have entered in a Anime that does not exist.");
                System.out.println();

            }
        
            

        }// End while true
        

    }//End update Anime

}
