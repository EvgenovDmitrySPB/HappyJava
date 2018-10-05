package net.proselyte.crud.controller.servlets;

import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.DeveloperRepository;
import net.proselyte.crud.repository.SkillRepository;
import net.proselyte.crud.repository.hibernate.HibernateAccountRepository;
import net.proselyte.crud.repository.hibernate.HibernateDeveloperRepository;
import net.proselyte.crud.repository.hibernate.HibernateSkillRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCDeveloperRepository;
import net.proselyte.crud.repository.jdbc.JDBCSkillRepositoryImpl;
import net.proselyte.crud.util.ConnectorHibernateMySQL;
import net.proselyte.crud.util.ConnectorMySQL;
import net.proselyte.crud.util.SelectConnection;
import org.hibernate.SessionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(value = "/developer", name = "developer")
public class DeveloperServlet extends HttpServlet {
    private SkillRepository skillRepository;
    private DeveloperRepository developerRepository;
    private AccountRepository accountRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public DeveloperServlet() {
        SelectConnection.getInstance().setConnectType(ConnectType.JDBC);

        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                skillRepository = new JDBCSkillRepositoryImpl(connection);
                developerRepository = new JDBCDeveloperRepository(connection);
                accountRepository = new JDBCAccountRepository(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                accountRepository = new HibernateAccountRepository(sessionFactory);
                skillRepository = new HibernateSkillRepository(sessionFactory);
                developerRepository = new HibernateDeveloperRepository(sessionFactory);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        List<Developer> list = developerRepository.getAll();
        req.setAttribute("developerList", list);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("www/developer/listDeveloper.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        String message = "Hello Dmitry POST";
        PrintWriter messageWriter = resp.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String message = "Hello Dmitry PUT";
        PrintWriter messageWriter = resp.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String message = "Hello Dmitry DELETE ";
        PrintWriter messageWriter = resp.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }
}
