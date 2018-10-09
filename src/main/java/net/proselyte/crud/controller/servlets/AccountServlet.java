package net.proselyte.crud.controller.servlets;

import net.proselyte.crud.builders.AccountBuilder;
import net.proselyte.crud.model.Account;
import net.proselyte.crud.model.ConnectType;
import net.proselyte.crud.repository.AccountRepository;
import net.proselyte.crud.repository.hibernate.HibernateAccountRepository;
import net.proselyte.crud.repository.jdbc.JDBCAccountRepository;
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

@WebServlet(value = "/account", name = "account")
public class AccountServlet extends HttpServlet {
    private AccountRepository accountRepository;
    private Connection connection;
    private SessionFactory sessionFactory;

    public AccountServlet() {
        checkCreateRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        if (SelectConnection.getInstance().getConnectType() != null){

            checkCreateRepository();

            List<Account> list = accountRepository.getAll();
            req.setAttribute("accountList", list);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("www/account/listAccount.jsp");
            requestDispatcher.forward(req, resp);
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("www/404.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("Cp1251");

        Long id = null;
        String accountData = "";
        String method = "";
        if (req.getParameter("id") != null){
            id = Long.parseLong(req.getParameter("id"));
        }
        if (req.getParameter("accountData") != null){
            accountData =req.getParameter("accountData");
        }
        if (req.getParameter("method") != null){
            method = req.getParameter("method");
        }

        if (method.equals("POST") && accountData != ""){
            AccountBuilder skillBuilder = new AccountBuilder();
            skillBuilder.withAccount(accountData);
            accountRepository.save(skillBuilder.toAccount());

        }else if (method.equals("PUT") && accountData != "" && id != null){

            AccountBuilder skillBuilder = new AccountBuilder();
            skillBuilder.withId(id).withAccount(accountData);

            accountRepository.update(skillBuilder.toAccount());
        }else if (method.equals("DELETE") && accountData != "" && id != null){
            accountRepository.deleteById(id);
        }

        List<Account> list = accountRepository.getAll();
        req.setAttribute("accountList", list);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("www/account/listAccount.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry PUT";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String message = "Hello Dmitry DELETE ";
        PrintWriter messageWriter = response.getWriter();
        messageWriter.println("<h1>" + message + "<h1>");
    }

    private void checkCreateRepository(){
        if (SelectConnection.getInstance().getConnectType() == ConnectType.JDBC){
            this.connection = ConnectorMySQL.getInstance().getConnection();

            if (this.connection == null){
                System.out.println("Warning! You don't have [JDBC] connection with MySQL");
                return;
            }else {
                accountRepository = new JDBCAccountRepository(connection);
            }
        }else if (SelectConnection.getInstance().getConnectType() == ConnectType.HIBERNATE){
            this.sessionFactory = ConnectorHibernateMySQL.getInstance().getSessionFactory();
            if (this.sessionFactory == null){
                System.out.println("Warning! You don't have {Hibernate} SessionFactory with MySQL");
                return;
            }else {
                accountRepository = new HibernateAccountRepository(sessionFactory);
            }
        }
    }
}
