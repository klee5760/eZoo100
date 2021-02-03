package com.examples.ezoo.dao;

import java.util.Collections;
import java.util.List;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class TestFeedingScheduleDAO {

    public static void main(String[] args) {
        
    	FeedingScheduleDAO dao = new FeedingScheduleDAOImpl();
		AnimalDAO animalDAO = new AnimalDAOImpl();

        FeedingSchedule feedingSchedule1 = new FeedingSchedule(401, "0800", "daily", "oatmeal", "breakfast");
        FeedingSchedule feedingSchedule2 = new FeedingSchedule(402, "1300", "daily", "penguin", "lunch");

        try {
            dao.saveFeedingSchedule(feedingSchedule1);
            dao.saveFeedingSchedule(feedingSchedule2);

        } catch (Exception e) {
            System.out.println(e);
        }

        feedingSchedule1.setRecurrence("weekly");
        try {
            dao.updateFeedingSchedule(feedingSchedule1);
        } catch (Exception e) {
            System.out.println(e);
        }

        List<FeedingSchedule> allFeedingSchedules = dao.getAllFeedingSchedules();
        Collections.sort(allFeedingSchedules);
        for (int i = 0; i < allFeedingSchedules.size(); i++) {
            FeedingSchedule f = allFeedingSchedules.get(i);
            System.out.println(f);
        }

        List<Animal> animals = animalDAO.getAllAnimals();
        Collections.sort(animals);
        for (Animal a : animals) {
            System.out.println(a.getName() + " has feeding schedule: ");
            System.out.println(dao.getFeedingSchedule(a));
        }

        try {
            for (FeedingSchedule schedule : allFeedingSchedules) {
            if (schedule.getScheduleID() == 1) {
                for (Animal animal : animals) {
                    if (animal.getAnimalID() == 4) {
                        dao.assignFeedingSchedule(schedule, animal);
                    }
                }
            }
        }

        dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(4-1));

    } catch (Exception e) {
        System.out.println(e);
    }

    try {
        for (Animal animal : animals) {
            if (animal.getAnimalID() == 1) {
                dao.removeFeedingSchedule(animal);
            }
        }

        dao.removeFeedingSchedule(animals.get(1-1));
     } catch (Exception e) {
         System.out.println(e);
     }

    try {
        dao.deleteFeedingSchedule(feedingSchedule1);
        dao.deleteFeedingSchedule(feedingSchedule2);
    } catch (Exception e) {
        System.out.println(e);
    }

    try {
        for(FeedingSchedule schedule : allFeedingSchedules) {
            if (schedule.getScheduleID() ==1) {
                for(Animal animal : animals) {
                    if (animal.getAnimalID() ==1) {
                        dao.assignFeedingSchedule(schedule, animal);
                    }
                }
            }
        }

        dao.assignFeedingSchedule(allFeedingSchedules.get(1-1), animals.get(1-1));
    } catch (Exception e1) {
        e1.printStackTrace();
    }

    try{
        for (Animal animal : animals) {
            if (animal.getAnimalID() ==4) {
                dao.removeFeedingSchedule(animal);
            }
        }

        dao.removeFeedingSchedule(animals.get(4-1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
}
