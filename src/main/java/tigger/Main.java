package tigger;

import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Tigger tigger = new Tigger("data/tigger.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            try (InputStream is = Main.class.getResourceAsStream("/images/tigger.jpeg")) {
                if (is != null) {
                    Image icon = new Image(is);
                    stage.getIcons().add(icon);
                }
            } catch (Exception e) {
                // ignore
            }

            stage.setTitle("Tigger chatbot");
            fxmlLoader.<MainWindow>getController().setTigger(tigger); // inject the Tigger instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
