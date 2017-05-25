package spring.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Rage on 18.05.2017.
 */
public abstract class AbstractFxApplication extends Application {

    private static String[] mSavedArgs;

    protected ConfigurableApplicationContext mContext;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void init() throws Exception {
        mContext = SpringApplication.run(getClass(), mSavedArgs);
        mContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        mContext.close();
    }

    protected static void launchApp(Class<? extends AbstractFxApplication> clazz, String[] args) {
        AbstractFxApplication.mSavedArgs = args;
        Application.launch(clazz, args);
    }

}
