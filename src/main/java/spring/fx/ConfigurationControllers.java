package spring.fx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.fx.controllers.*;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rage on 18.05.2017.
 */
@Configuration
public class ConfigurationControllers {


    @Bean(name = "main_view")
    public View getMainView() throws IOException {
        return loadView("fxml/main.fxml");
    }

    @Bean(name = "admin_view")
    public View getAdminView() throws IOException {
        return loadView("fxml/admin.fxml");
    }

    @Bean(name = "login_view")
    public View getLoginView() throws IOException {
        return loadView("fxml/login.fxml");
    }

    @Bean(name = "registration_view")
    public View getRegistrationView() throws IOException {
        return loadView("fxml/registration.fxml");
    }

    @Bean(name = "reservation_view")
    public View getReservationView() throws IOException {
        return loadView("fxml/add_reserv.fxml");
    }

    public ReservationController getReservationController() throws IOException {
        return (ReservationController) getReservationView().getController();
    }

    @Bean
    public MainController getMainController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public AdminController getAdminController() throws IOException {
        return (AdminController) getAdminView().getController();
    }

    @Bean
    public LoginController getLoginController() throws IOException {
        return (LoginController) getLoginView().getController();
    }

    @Bean
    public RegistrationController getRegistrationController() throws IOException {
        return (RegistrationController) getRegistrationView().getController();
    }


    protected View loadView(String url) throws IOException {
        InputStream fxmlStream = null;
        try {
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } finally {
            if (fxmlStream != null) {
                fxmlStream.close();
            }
        }
    }

    public class View {
        private Parent view;
        private Object controller;
        private Scene scene;

        public View(Parent view, Object controller) {
            this.view = view;
            this.controller = controller;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Scene getScene() {
            return scene;
        }

        public void setScene(Scene scene) {
            this.scene = scene;
        }
    }

}
