package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

@WebServlet("/updateFeedingSchedule")
public class UpdateFeedingScheduleServlet extends HttpServlet{

    private static final long serialVersionUTD = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int oldScheduleID = Integer.parseInt(request.getParameter("scheduleID"));

        String oldFeedingTime = request.getParameter("feedingTime");
        String oldRecurrence = request.getParameter("recurrence");
        String oldFood = request.getParameter("food");
        String oldNotes = request.getParameter("notes");

        FeedingSchdeule oldFeedingSchedule = new FeedingSchedule(oldScheduleID, oldFeedingTime, oldRecurrence, oldFood, oldNotes);

        request.getSession().setAttribute("oldFeedingSchedule", (Object) oldFeedingSchedule);

        request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FeedingSchedule oldFeedingSchedule = (FeedingSchedule) request.getSession().getAttribute("oldFeedingSchedule");

        int scheduleID = oldFeedingSchedule.getScheduleID();
        
        String feedingTime = request.getParameter("feedingTime");
        String recurrence = request.getParameter("recurrence");
        String food = request.getParameter("food");
        String notes = request.getParameter("notes");

        FeedingSchdeule scheduleToUpdate = new FeedingSchedule(scheduleID, feedingTime, recurrence, food, notes);

        FeedingSchdeuleDAO dao = DAOUtiliteis.getFeedingScheduleDAO();
        try{
            dao.updateFeedingSchedule(scheduleToUpdate);
			request.getSession().setAttribute("message",  "Feeding schedule successfully updated");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingSchedules");
        } catch (Exception e) {
            e.printStackTrace();
			request.getSession().setAttribute("message",  "There was a problem updating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("updateFeedingSchedule.jsp").forward(request, response);
        }

    }
    
}
