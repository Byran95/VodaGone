package main.java.DomainApplication.MySQLDataAccess;

import main.java.DomainApplication.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Bryan van Elden on 12/10/2016.
 */
public class AbonnementDAOMySQL implements IAbonnementAccess {
    private String URL = "jdbc:mysql://localhost/vodagone";
    private String USER = "root";
    private String PASS = "";
    private Connection connection;
    private PreparedStatement ps;
    Logger logger = Logger.getLogger("logger");

    public AbonnementDAOMySQL(){
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
            ps = connection.prepareStatement("SELECT * FROM abonnement WHERE abonneeId=?");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getAbonnee(int id) throws SQLException {
        return getResults(id).getString("abonneeId");

    }

    public String getBedrijf(int id) throws SQLException {
        return getResults(id).getString("bedrijf");

    }

    public String getNaam(int id) throws SQLException {
        return getResults(id).getString("naam");

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

    public List<IAbonnement> findAbonnementenVanAbonnee(int id){
        prepareStatement();
        ResultSet rs = getResults(id);
        List<IAbonnement> mijnAbonnementen = new ArrayList<>();

        try {
            rs.beforeFirst();
            while (rs.next()){
                mijnAbonnementen.add(new Abonnement(
                        rs.getInt("abonneeId"),
                        rs.getString("startDatum"),
                        rs.getBoolean("verdubbeld"),
                        getEnumSoort(rs.getString("abonneeId")),
                        getEnumStatus(rs.getString("abonneeId")))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mijnAbonnementen;
    }

    private AbonnementSoort getEnumSoort(String soort) {
        switch (soort) {
            case "MAAND":
                return AbonnementSoort.MAAND;
            case "HALFJAAR":
                return AbonnementSoort.HALFJAAR;
            default:
                return AbonnementSoort.JAAR;
        }
    }

    private AbonnementStatus getEnumStatus(String status) {
        switch (status) {
            case "OPGEZEGD":
                return AbonnementStatus.OPGEZEGD;
            case "PROEF":
                return AbonnementStatus.PROEF;
            default:
                return AbonnementStatus.ACTIEF;
        }
    }

    public List<IAbonnement> makeAbonnementList(){
        List<IAbonnement> abonnementen = new ArrayList<>();

        int abonneeId;
        String startDatum;
        boolean verdubbeld;
        String soort;
        String status;

        int teller=0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs=statement.executeQuery("select * from abonnement");

            while (rs.next()){
                teller++;
                abonneeId = rs.getInt("abonneeId");
                startDatum = rs.getString("startDatum");
                verdubbeld = rs.getBoolean("verdubbeld");
                soort = rs.getString("abonneeId");
                status = rs.getString("abonneeId");
                abonnementen.add(new Abonnement(abonneeId, startDatum, verdubbeld, getEnumSoort(soort), getEnumStatus(status)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return abonnementen;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
