package main.java.DomainApplication.MySQLDataAccess;

import main.java.DomainApplication.Abonnee;
import main.java.DomainApplication.IAbonnee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michel Koolwaaij on 07-10-16.
 */
public class AbonneeDAOMySQL {
    private String URL = "jdbc:mysql://localhost/vodagone";
    private String USER = "root";
    private String PASS = "";
    private Connection connection;
    private PreparedStatement ps;
    Logger logger = Logger.getLogger("logger");

    public AbonneeDAOMySQL(){
        try {
            connection = DriverManager.getConnection(URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection==null) {
            logger.log(Level.WARNING,"Biebel");
        }
    }

    public void prepareStatement(){
        try {
            ps = connection.prepareStatement("SELECT * FROM abonnee WHERE abonneeId=?");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getNaam(int id) throws SQLException {
        return getResults(id).getString("naam");

    }

    public String getAchternaam(int id) throws SQLException {
        return getResults(id).getString("achternaam");

    }

    public String getEmailadres(int id) throws SQLException {
        return getResults(id).getString("emailadres");

    }

    private ResultSet getResults(int id){
        ResultSet rs = null;
        try {
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.first();
        } catch (SQLException e){
            System.out.println("Fout hierzo");
            e.printStackTrace();

        }

        return rs;

    }

    public IAbonnee findAbonnee(int id){
        prepareStatement();
        try {
            IAbonnee abonnee = new Abonnee(getNaam(id),getAchternaam(id),getEmailadres(id),id);
            return abonnee;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<IAbonnee> makeAbonneeList(){
        List<IAbonnee> abonnees = new ArrayList<>();
        int abonneeId;
        String voornaam;
        String mail;
        String achternaam;
        int teller=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from abonnee");

            while (rs.next()){
                teller++;
                abonneeId = rs.getInt("abonneeId");
                voornaam = rs.getString("naam");
                achternaam = rs.getString("achternaam");
                mail = rs.getString("emailadres");
                abonnees.add(new Abonnee(voornaam,achternaam,mail,abonneeId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonnees;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
