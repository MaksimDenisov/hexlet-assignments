package exercise.servlet;

import exercise.Users;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static exercise.App.getUsers;

public class SessionServlet extends HttpServlet {

    private Users users = getUsers();

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("flash");
        if (request.getRequestURI().equals("/login")) {
            showLoginPage(request, response);
            return;
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("flash");
        switch (request.getRequestURI()) {
            case "/login" -> login(request, response);
            case "/logout" -> logout(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showLoginPage(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private void login(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        String email = request.getParameter("email");
        String password  = request.getParameter("password");;
        HttpSession session = request.getSession();
        if(session.getAttribute("userId") == null) {
            Map<String, String> user = getUsers().findByEmail(email);
            if (user != null && password.equals(user.get("password"))) {
                session.setAttribute("flash", "Вы успешно вошли");
                session.setAttribute("userId", user.get("id"));
                response.sendRedirect("/");
            } else {
                session.setAttribute("flash", "Неверные логин или пароль");
                response.setStatus(422);
                Map<String, String> incorrectUser = new HashMap<>();
                incorrectUser.put("email", email);
                request.setAttribute("user", incorrectUser);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request, response);
            }
            return;
        }
        response.sendRedirect("/");
        // END
    }

    private void logout(HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException {
        // BEGIN
        HttpSession session = request.getSession();
        session.setAttribute("flash", "Вы успешно вышли");
        session.removeAttribute("userId");
        response.sendRedirect("/");

        // END
    }
}
