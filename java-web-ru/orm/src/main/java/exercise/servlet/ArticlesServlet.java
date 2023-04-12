package exercise.servlet;

import exercise.TemplateEngineUtil;
import exercise.domain.Article;
import exercise.domain.Category;
import exercise.domain.query.QArticle;
import exercise.domain.query.QCategory;
import io.ebean.PagedList;
import org.apache.commons.lang3.ArrayUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
            case "new":
                newArticle(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                createArticle(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {

        int articlesPerPage = 10;
        String page = request.getParameter("page");
        int normalizedPage = page == null ? 1 : Integer.parseInt(page);
        int offset = (normalizedPage - 1) * articlesPerPage;

        // BEGIN
        PagedList<Article> pagedList = new QArticle()
                .select()
                .orderBy()
                .id.asc()
                .setFirstRow(offset)
                .setMaxRows(articlesPerPage)
                .findPagedList();
        // END
        request.setAttribute("page", normalizedPage);
        request.setAttribute("articles", pagedList.getList());
        TemplateEngineUtil.render("articles/index.html", request, response);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        long id = Long.parseLong(getId(request));

        // BEGIN
        Article article = new QArticle()
                .select()
                .id.eq(id)
                .findOne();
        // END
        request.setAttribute("article", article);
        TemplateEngineUtil.render("articles/show.html", request, response);
    }

    private void newArticle(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        List<Category> pagedList = new QCategory()
                .select()
                .findList();
        request.setAttribute("categories", pagedList);
        // END
        TemplateEngineUtil.render("articles/new.html", request, response);
    }

    private void createArticle(HttpServletRequest request,
                               HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        String title = request.getParameter("title");
        String body = request.getParameter("body");
        String categoryId = request.getParameter("categoryId");

        // BEGIN
        Category category = new QCategory()
                .select()
                .id.eq(Long.valueOf(categoryId))
                .findOne();
        new Article(title,body,category).save();

        session.setAttribute("flash", "Статья успешно создана");
        response.sendRedirect("/articles");
    }
}
