package mainClasses;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Serv")
public class Serv extends HttpServlet {

    @EJB
    private Facade facade = new Facade();

    public Serv(){
        super();
    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String action = request.getParameter("action");
            System.out.println("Test1");
            if (action.equals("connexion")){
                System.out.println("Test2");
                String pseudo = request.getParameter("pseudo");
                System.out.println(pseudo);
                String password = request.getParameter("password");
                System.out.println(password);
                User user = facade.findUser(pseudo,password);
                System.out.println("Test6");
                if (user == null) {
                    System.out.println("Test3");
                    request.setAttribute("error", "notfound");
                }
                System.out.println("Test4");
                request.getRequestDispatcher("connection.jsp").forward(request, response);
                System.out.println("Test5");
                return;

            } else if (action.equals("inscription")) {
                System.out.println("Test22");
                String pseudo = request.getParameter("pseudo");
                System.out.println(pseudo);
                String password = request.getParameter("password");
                System.out.println(password);
                String email = request.getParameter("email");
                System.out.println(email);
                facade.addUser(pseudo,email,password);
                System.out.println("TEST23");
                request.getRequestDispatcher("connection.jsp").forward(request, response);
                return;

            } else if (action.equals("c")){

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
