package spring.fx;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

/**
 * Created by Rage on 18.05.2017.
 */
@Lazy
@SpringBootApplication
public class FxApplication extends AbstractFxApplication {

    @Value("Бронирование коттеджей")
    private String windowTitle;


    @Qualifier("main_view")
    @Autowired
    private ConfigurationControllers.View view;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle(windowTitle);
        stage.setScene(new Scene(view.getView()));
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launchApp(FxApplication.class, args);
    }

}
