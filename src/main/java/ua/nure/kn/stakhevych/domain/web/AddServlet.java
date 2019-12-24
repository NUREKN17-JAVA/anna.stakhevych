package ua.nure.kn.stakhevych.domain.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.stakhevych.domain.User;
import ua.nure.kn.stakhevych.domain.db.DaoFactory;
import ua.nure.kn.stakhevych.domain.db.DataBaseException;

public class AddServlet extends EditServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {	// TODO Auto-generated method stub
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	protected void processUser(User user) throws DataBaseException  {
		// TODO Auto-generated method stub
		DaoFactory.getInstance().getUserDao().create(user);
	}
}

