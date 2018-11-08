package net.proselyte.crud.controller.servlets;

import net.proselyte.crud.builders.DeveloperBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.model.Developer;
import net.proselyte.crud.model.Skill;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(value = "/developer", name = "developer")
public class DeveloperServlet extends HttpServlet {
    private SkillRepository skillRepository;
    private DeveloperRepository developerRepository;
    private AccountRepository accountRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public DeveloperServlet() {

        checkCreateRepository();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        if (SelectConnection.getInstance().getConnectType() != null){

            if (skillRepository == null){
                checkCreateRepository();
            }

            List<Developer> list = developerRepository.getAll();
            req.setAttribute("developerList", list);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("developer/listDeveloper.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("404.jsp");
            requestDispatcher.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        Long id = null;
        String firstName = "";
        String lastName = "";
        String specialty = "";
        Account account = null;
        Set<Skill> skillSet = new HashSet<>();
        Skill skill = null;
        String method = "";

        if (req.getParameter("id") != null){
            id = Long.parseLong(req.getParameter("id"));
        }
        if (req.getParameter("firstName") != null){
            firstName =req.getParameter("firstName");
        }
        if (req.getParameter("lastName") != null){
            lastName =req.getParameter("lastName");
        }
        if (req.getParameter("specialty") != null){
            specialty =req.getParameter("specialty");
        }
        if (req.getParameter("accountId") != null){
            account = accountRepository.getById(Long.parseLong(req.getParameter("accountId")));
        }
        if (req.getParameter("skills") != null){
            req.getParameter("skills");
        }
        if (req.getParameter("skillId") != null){
            skill = skillRepository.getById(Long.parseLong(req.getParameter("skillId")));
            skillSet.add(skill);
        }
        if (req.getParameter("method") != null){
            method = req.getParameter("method");
        }

        if(method.equals("POST")){
            DeveloperBuilder developerBuilder = new DeveloperBuilder();
            developerBuilder.withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSpecialty(specialty)
                    .withAccount(account)
                    .withSkill(skillSet);

            developerRepository.save(developerBuilder.toDeveloper());
        }else if (method.equals("PUT") && id != null){

            DeveloperBuilder developerBuilder = new DeveloperBuilder();
                developerBuilder.withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withSpecialty(specialty)
                    .withAccount(account);

            developerRepository.update(developerBuilder.toDeveloper());
        }else if (method.equals("DELETE") && id != null){
            developerRepository.deleteById(id);
        }

        List<Developer> list = developerRepository.getAll();
        req.setAttribute("developerList", list);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("developer/listDeveloper.jsp");
        requestDispatcher.forward(req, resp);
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

    private void checkCreateRepository(){
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
}

