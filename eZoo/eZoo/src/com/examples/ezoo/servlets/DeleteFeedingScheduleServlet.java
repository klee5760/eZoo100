package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
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

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {

    private static final long serialVersionUTD = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));

        String feedingTime = request.getParameter("feedingTime");
        String recurrence = request.getParameter("recurrence");
        String food = request.getParameter("food");
        String notes = request.getParameter("notes");

        FeedingSchedule scheduleToDelete = new FeedingSchedule(scheduleID, feedingTime, recurrence, food, notes);

        FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
        AnimalDAO animalDAO = DAOUtilities.getAnimalDAO();

        try{
            List<Animal> animals = animalDAO.getAllAnimals();
            for (Animal animal : animals) {
                if (animal.getFeedingScheduleID() == scheduleToDelete.getScheduleID()) {
                    dao.removeFeedingSchedule(animal);
                }
            }

            dao.deleteFeedingSchedule(scheduleToDelete);

            request.getSession().setAttribute("message",  "Feeding schedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");		// need this
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message",  "Id of " + scheduleToDelete.getScheduleID() + " does not exist");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message",  "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
        }

    }
}