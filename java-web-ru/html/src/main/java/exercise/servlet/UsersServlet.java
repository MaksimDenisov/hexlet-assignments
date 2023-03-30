package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(Paths.get("src/main/resources/users.json").toFile(), ArrayList.class);
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {
        // BEGIN
        StringBuilder body = new StringBuilder();
        body.append("""
            <!DOCTYPE html>
            <html lang=\"ru\">
                <head>
                    <meta charset=\"UTF-8\">
                    <title>Example application | Users</title>
                    <link rel=\"stylesheet\" href=\"mysite.css\">
                </head>
                <body>
            """);
        body.append("<table>");
        for(Object o:   getUsers()) {
            Map<String,String> map = (Map<String, String>) o;
            body.append("<tr>");
            body.append(String.format("<td>%s</td>",map.get("id")));
            body.append(String.format("<td><a href=\"/users/%s\">%s %s</a></td>",map.get("id"),map.get("firstName"),map.get("lastName")));
            body.append("</tr>");
        }
        body.append("</table>");
        body.append("""
                </body>
            </html>
            """);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        Map<String,String> map = (Map<String, String>) getUsers().stream()
                .filter(v->{
                    Map<String,String> value = (Map<String, String>) v;
                    return value.get("id").equals(id);
                })
                .findFirst().orElse(null);
        if (map == null) {
            PrintWriter out = response.getWriter();
            out.println("Not found");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        StringBuilder body = new StringBuilder();
        body.append("<table>");
        body.append("<tr>");
        body.append(String.format("<td>%s</td>",map.get("id")));
        body.append(String.format("<td><a href=\"/users/%s\">%s %s</a></td>",map.get("id"),map.get("firstName"),map.get("lastName")));
        body.append("</tr>");
        body.append("</table>");
        response.getWriter().println(body.toString());
        // END
    }
}
