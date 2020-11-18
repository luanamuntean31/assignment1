import controller.LoginController;
import view.LoginView;
import view.EmployeeView;
import controller.EmployeeController;
import controller.AdministratorController;
import view.AdministratorView;
import view.LoginView;


public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(new LoginView(), componentFactory.getAuthenticationService(),componentFactory.getRightsRolesRepository());
        // new EmployeeController(new EmployeeView(), componentFactory.getAuthenticationService());
        //new AdministratorController(new AdministratorView(), componentFactory.getAuthenticationService());

    }

}