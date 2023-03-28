package exercise.servlet;

import exercise.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        String search = request.getParameter("search");
        List<String> companies = Data.getCompanies();
        if (!(search == null) && !search.isEmpty()) {
            companies = companies.stream()
                    .filter(s->s.contains(search))
                    .collect(Collectors.toList());
        }
        for (String company : companies) {
            response.getWriter().println(company);
        }
    }
}