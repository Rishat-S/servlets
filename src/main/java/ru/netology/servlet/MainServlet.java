package ru.netology.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_DELETE = "DELETE";
    private PostController controller;

    @Override
    public void init() {
        final var context = new AnnotationConfigApplicationContext("ru.netology");
        final var repository = context.getBean("postRepository", PostRepository.class);
        final var service = context.getBean("postService", PostService.class);
        controller = context.getBean("postController", PostController.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        final var method = req.getMethod();

        if (method.equals(METHOD_GET) && path.equals("/api/posts")) {
            controller.all(resp);
            return;
        }

        if (method.equals(METHOD_GET) && path.matches("/api/posts/\\d+")) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            controller.getById(id, resp);
            return;
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var path = req.getRequestURI();
        final var method = req.getMethod();

        if (method.equals(METHOD_POST) && path.equals("/api/posts")) {
            controller.save(req.getReader(), resp);
            return;
        }

        super.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final var path = req.getRequestURI();
        final var method = req.getMethod();

        if (method.equals(METHOD_DELETE) && path.matches("/api/posts/\\d+")) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            controller.removeById(id, resp);
            return;
        }

        super.doDelete(req, resp);
    }

}

