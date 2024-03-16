package com.wine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WineDAO {
    private static final Logger logger = LogManager.getLogger(WineDAO.class);
    public static class DatabaseException extends RuntimeException {
        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public List<Wine> findAll() {
        List<Wine> list = new ArrayList<>();
        String sql = "SELECT * FROM wine ORDER BY name";
        try (Connection c = ConnectionHelper.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            logger.error("Error accessing findAll()", e);
            throw new DatabaseException("Error accessing findAll()", e);
        }
        return list;
    }

    public Wine findById(int id) {
        String sql = "SELECT * FROM wine WHERE id = ?";
        Wine wine = null;

        try (
                Connection c = ConnectionHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
        ) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    wine = processRow(rs);
                }
            }
        } catch (Exception e) {
            logger.error("Error accessing findById()", e);
            throw new DatabaseException("Error accessing findById()", e);
        }

        return wine;
    }

    public List<Wine> findByName(String name){
        List<Wine> list = new ArrayList<>();
        String sql = "SELECT * FROM wine as e WHERE UPPER(name) LIKE ? ORDER BY name;";

        try(
                Connection c = ConnectionHelper.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {

            String parameter = "%" + name.toUpperCase() + "%";
            ps.setObject(1, parameter);


            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(processRow(rs));
            }

        } catch (SQLException e){
            logger.error("Error accessing findeByName()", e);
            throw new DatabaseException("Error accessing findeByName()", e);
        }
        return list;
    }

    public List<Wine> findByCountryAndGrapes(String country, String grapes){
        List<Wine> list = new ArrayList<>();
        String sql = "SELECT * FROM wine as e WHERE UPPER(country) LIKE ? AND UPPER(grapes) LIKE ? ORDER BY name;";
        try(  Connection c = ConnectionHelper.getConnection();
              PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setObject(1, "%" + country.toUpperCase() + "%");
            ps.setObject(2, "%" + grapes.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                list.add(processRow(rs));
            }

        }catch (SQLException e){
            logger.error("Error accessing findByCountryAndGrapes()", e);
            throw new DatabaseException("Error accessing findByCountryAndGrapes()", e);
        }
        return list;
    }

    public Wine create(Wine wine){
        String sql = "INSERT INTO wine (name, grapes, country, region, year, picture, description) VALUES (?,?,?,?,?,?,?);";
        try(Connection c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, new String[] {"ID"});) {

            ps.setObject(1, wine.getName());
            ps.setObject(2, wine.getGrapes());
            ps.setObject(3, wine.getCountry());
            ps.setObject(4, wine.getRegion());
            ps.setObject(5, wine.getYear());
            ps.setObject(6, wine.getPicture());
            ps.setObject(7, wine.getDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            wine.setId(id);
            return wine;
        }catch (SQLException e){
            logger.error("Error accessing create()", e);
            throw new DatabaseException("Error accessing create()", e);
        }
    }

    public Wine update(Wine wine){
        String sql = "UPDATE wine SET name=?, grapes=?, country=?, region=?, year=?, picture=?, description=? WHERE id=?";
        try(Connection c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setObject(1,wine.getName());
            ps.setObject(2,wine.getGrapes());
            ps.setObject(3,wine.getCountry());
            ps.setObject(4,wine.getRegion());
            ps.setObject(5,wine.getYear());
            ps.setObject(6,wine.getPicture());
            ps.setObject(7,wine.getDescription());
            ps.setObject(8,wine.getId());
        }catch (SQLException e){
            logger.error("Error accessing update()", e);
            throw new DatabaseException("Error accessing update()", e);
        }
        return wine;
    }

    public boolean remove(int id){
        String sql = "DELETE FROM wine WHERE id=?";
        try(Connection c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setObject(1,id);
            int count = ps.executeUpdate();
            return count == 1;

        } catch (Exception e){
            logger.error("Error accessing remove()", e);
            throw new DatabaseException("Error accessing remove()", e);
        }
    }

    protected Wine processRow(ResultSet rs) throws SQLException {
        Wine wine = new Wine();
        wine.setId(rs.getInt("id"));
        wine.setName(rs.getString("name"));
        wine.setGrapes(rs.getString("grapes"));
        wine.setCountry(rs.getString("country"));
        wine.setRegion(rs.getString("region"));
        wine.setYear(rs.getString("year"));
        wine.setPicture(rs.getString("picture"));
        wine.setDescription(rs.getString("description"));
        return wine;
    }
}
