package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDAOImpl implements FeedingScheduleDAO{

    @Override
    public void saveFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "INSERT INTO feeding_schedules VALUES (?, ?, ?, ?, ?)";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, feedingSchedule.getScheduleID());
            stmt.setString(2, feedingSchedule.getFeedingTime());
            stmt.setString(3, feedingSchedule.getRecurrence());
            stmt.setString(4, feedingSchedule.getFood());
            stmt.setString(5, feedingSchedule.getNotes());

            success = stmt.executeUpdate();            
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                stmt.close();
                if (connection != null)
                connection.close();                          
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            throw new Exception("Save feeding schedule failed: " + feedingSchedule);
        }
    }

    @Override
    public void deleteFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "DELETE FROM feeding_schedules WHERE schedule_id = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, feedingSchedule.getScheduleID());

            success = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                stmt.close();
                if(connection != null)
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0) {
            throw new Exception("Delete feeding schedule failed: +" + feedingSchedule);
        }
    }

    @Override
    public List<FeedingSchedule> getAllFeedingSchedules() {
        List<FeedingSchedule> feedingSchedules = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            stmt = connection.createStatement();

            String sql = "SELECT * FROM feeding_schedules";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                FeedingSchedule fs = new FeedingSchedule();

                fs.setScheduleID(rs.getInt("schedule_id"));
                fs.setFeedingTime(rs.getString("feeding_time"));
                fs.setRecurrence(rs.getString("recurrence"));
                fs.setFood(rs.getString("food"));
                fs.setNotes(rs.getString("notes"));

                feedingSchedules.add(fs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return feedingSchedules;
    }

    @Override
    public FeedingSchedule getFeedingSchedule(Animal animal) {
        FeedingSchedule feedingSchedule = new FeedingSchedule();
        Connection connection = null;
        PreparedStatement stmt = null;

        try {
            connection = DAOUtilities.getConnection();

            String sql = "SELECT * FROM feeding_schedules WHERE schedule_id = ?";
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, animal.getFeedingScheduleID());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                feedingSchedule.setScheduleID(rs.getInt("schedule_id"));
                feedingSchedule.setFeedingTime(rs.getString("feeding_time"));
                feedingSchedule.setRecurrence(rs.getString("recurrence"));
                feedingSchedule.setFood(rs.getString("food"));
                feedingSchedule.setNotes(rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return feedingSchedule;
    }

    @Override
    public void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE animals SET feeding_schedule = ? WHERE schedule_id = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, feedingSchedule.getScheduleID());
            stmt.setLong(2, animal.getAnimalID());

            success = stmt.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0)
        throw new Exception("Assign feeding schedule failed: " + animal);
    }

    @Override
    public void removeFeedingSchedule(Animal animal) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE animals SET feeding_schedule = null WHERE animalid = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, animal.getAnimalID());

            success = stmt.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0)
        throw new Exception("Remove feeding schedule from animal failed: " + animal);
    
    }

    @Override
    public void updateFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
        Connection connection = null;
        PreparedStatement stmt = null;
        int success = 0;

        try {
            connection = DAOUtilities.getConnection();
            String sql = "UPDATE feeding_schedules SET feeding_time = ?, recurrence = ?" +
            "food = ?, notes = ? WHERE schedule_id = ?";

            stmt = connection.prepareStatement(sql);

            stmt.setString(1, feedingSchedule.getFeedingTime());
            stmt.setString(2, feedingSchedule.getRecurrence());
            stmt.setString(3, feedingSchedule.getFood());
            stmt.setString(4, feedingSchedule.getNotes());
            stmt.setInt(5, feedingSchedule.getScheduleID());

            success = stmt.executeUpdate();

            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) 
                    stmt.close();
                
                if (connection != null) 
                    connection.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (success == 0)
        throw new Exception("Update feeding schedule failed: " + feedingSchedule);
    
    }

}
