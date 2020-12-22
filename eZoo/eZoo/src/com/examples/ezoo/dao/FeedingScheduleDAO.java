package com.examples.ezoo.dao;

import java.util.List;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {

    void saveFeedingSchedule(FeedingSchedule schedule) throws Exception;
    void deleteFeedingSchedule(FeedingSchedule schedule) throws Exception;
    List<FeedingSchedule> getAllFeedingSchedules();
    FeedingSchedule getAllFeedingSchedule(Animal animal);
    void assignFeedingSchedule(FeedingSchedule feedingSchedule, Animal animal) throws exception;
    void removeFeedingSchedule(Animal animal) throws Exception;
    void updateFeedingSchedule(FeedingSchedule schedule) throws Exception;
    
}
