package ua.nure.kn.stakhevych.domain.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;

public class DeleteServlet extends EditServlet {

	@Override
	protected void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("/delete.jsp").forward(req, resp);
	}

	@Override
	protected void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doCancel(req, resp);
	}

	@Override
	protected void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            DaoFactory.getInstance().getUserDao().delete((User) req.getSession().getAttribute("user"));
        } catch (DataBaseException e) {
           req.getRequestDispatcher("/delete.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/browse").forward(req, resp);
	}
}