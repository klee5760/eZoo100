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

@WebServlet("/createFeedingSchedule")
public class CreateFeedingScheduleServlet extends HttpServlet {

    private static final long serialVersionUTD = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleID = Integer.parseInt(request.getParameter("scheduleID"));

        String feedingTime = request.getParameter("feedingTime");
        String recurrence = request.getParameter("recurrence");
        String food = request.getParameter("food");
        String notes = request.getParameter("notes");

        FeedingSchedule scheduleToSave = new FeedingSchedule(scheduleID,feedingTime,recurrence,food,notes);

        FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDAO();
        try {
            dao.saveFeedingSchedule(scheduleToSave);
            request.getSession().setAttribute("message", "Feeding schedule successfully created");
            request.getSession().setAttribute("messageClass", "alert-success");
            response.sendRedirect("feedingSchedules");

        } catch(SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            request.getSession().setAttribute("message",  "Id of " + scheduleToSave.getScheduleID() + " is already in use");
			request.getSession().setAttribute("messageClass",  "alert-danger");
			request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// change the message
			request.getSession().setAttribute("message",  "There was a problem creating the feeding schedule at this time");
			request.getSession().setAttribute("messageClass",  "alert-danger");
            request.getRequestDispatcher("createFeedingSchedule.jsp").forward(request, response);
        }
    }
}