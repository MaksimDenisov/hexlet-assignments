package exercise.servlet;

import exercise.TemplateEngineUtil;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ArticlesServlet extends HttpServlet {

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        Statement statement = null;
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        try {
            statement = connection.createStatement();

            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT * FROM articles LIMIT 10 OFFSET " + String.valueOf((Integer.valueOf(page) - 1) * 10));

            List<Map<String, String>> articles = new ArrayList<>();

            while (rs.next()) {
                articles.add(Map.of(
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }
            request.setAttribute("articles", articles);
            request.setAttribute("prev", String.valueOf(Integer.valueOf(page) - 1));
            request.setAttribute("next", String.valueOf(Integer.valueOf(page) + 1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // END
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        // BEGIN
        String id = getId(request);
        String title = null;
        String body = null;
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("SELECT * FROM articles WHERE id = " + id + " ORDER BY id ");

            while (rs.next()) {
                title = rs.getString("title");
                body = rs.getString("body");
            }
        } catch (SQLException e) {

        }
        if (title == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("title", title);
        request.setAttribute("body", body);
        // END
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
