package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/FSAssignment")
public class FSAssignmentServlet extends HttpServlet{

    private static final long serialVersionUTD = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int animalID = Integer.parseInt(request.getParameter("animalID"));

        FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
        List<FeedingSchedule> feedingSchedule = dao.getAllFeedingSchedules();

        AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();
        List<Animal> animals = animalDAO.getAllAnimals();
        Collections.sort(animals);
        for (FeedingSchedule schedule : feedingSchedule) {
            String animalsWithSchedule = "";
            int count = 0;
            for (Animal animal :  animals) {
                if (schedule.getScheduleID() == animal.getFeedingScheduleID()) {
                    count++;
                    String comma = "";
                    if (count > 1) {
                        comma = ", ";
                    }
                    animalsWithSchedule += comma + animal.getName() + "[" + animal.getAnimalID() + "]";
                }
            }
            schedule.setAnimals(animalsWithSchedule);
        }

        request.getSession().setAttribute("feedingSchedules", feedingSchedule);
        request.getSession().setAttribute("animalID", animalID);

        request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int animalID = Integer.parseInt(request.getParameter("animalID"));

        AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();
        FeedingScheduleDAO FSDAO = DAOUtilities.getFeedingScheduleDAO();

        try {
            List<Animal> animals = animalDAO.getAllAnimals();
            Collections.sort(animals);
            Animal animal = new Animal();
            for (Animal a : animals) {
                if (a.getAnimalID() == animalID)
                animal = a;
            }

            if (animal.getFeedingScheduleID() > 0) {
                FSDAO.removeFeedingSchedule(animal);
                request.getSession().setAttribute("message",  "Feeding schedule successfully removed");
            }

            else{
                int id = Integer.parseInt(request.getParameter("scheduleID"));
                String time = request.getParameter("feedingTime");
                String recurrence = request.getParameter("recurrence");
                String food = request.getParameter("food");
                String notes = request.getParameter("notes");
                FeedingSchedule fs = new FeedingSchedule (id, time, recurrence, food, notes);

                FSDAO.assignFeedingSchedule(fs, animal);

                request.getSession().setAttribute("message", "Feeding scheule successfully assigned");
            }

            request.getSession().setAttribute("messageClass", "alert-success");
            response.sendRedirect("animalCare");
        
        } catch (Exception e) {
            e.printStackTrace();

            request.getSession().setAttribute("message",  "There was a problem assigning or unassigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("animalCareHome.jsp").forward(request, response);

        }
    }
    
}
