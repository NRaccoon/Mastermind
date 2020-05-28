package main;

import com.google.inject.AbstractModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mastermind.Color;
import results.GameResultDao;
import util.guice.PersistenceModule;
import com.gluonhq.ignite.guice.GuiceContext;
import javax.inject.Inject;

import java.util.List;

public class MyApplication extends Application {

    private GuiceContext context = new GuiceContext(this, () -> List.of(
            new AbstractModule() {
                @Override
                protected void configure() {
                    install(new PersistenceModule("mastermind"));
                    bind(GameResultDao.class);
                }
            }
    ));

    @Inject
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        context.init();
        Color.initColors();
        fxmlLoader.setLocation(getClass().getResource("/fxml/launch.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Mastermind");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
