package tigger;

import java.io.InputStream;
import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tigger tigger;

    // Load images lazily and defensively so missing resources don't crash the app
    private Image userImage;
    private Image tiggerImage;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    private Image loadImage(String path) {
        try (InputStream is = this.getClass().getResourceAsStream(path)) {
            if (is != null) {
                return new Image(is);
            }
        } catch (Exception e) {
            // ignore and return null so no image is displayed
        }
        return null;
    }

    /** Injects the Tigger instance */
    public void setTigger(Tigger t) {
        tigger = t;
        userImage = loadImage("/images/ferb.jpg");
        tiggerImage = loadImage("/images/tigger.jpeg");

        String welcomeMessage = tigger.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getTiggerDialog(welcomeMessage, tiggerImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        TaskList tasks = tigger.getTaskList();
        Storage storage = tigger.getStorage();
        ArrayList<Task> list = tasks.getTaskList();

        String response;
        try {
            response = Parser.getResponse(input, list);
        } catch (TiggerException e) {
            response = e + "\n";
            assert e.toString().equals("Give me something I can understand!!") : "Unexpected TiggerException message";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTiggerDialog(response, tiggerImage)
        );

        storage.saveTasks(list);

        if (input.trim().equals("bye")) {
            String goodbye = """
                    Bye. Hope to see you again soon!
                    """;
            dialogContainer.getChildren().add(DialogBox.getTiggerDialog(goodbye, tiggerImage));
            Platform.exit();
        }

        // Used ChatGPT to generate the bounce animation code
        TranslateTransition bounce = new TranslateTransition(Duration.millis(150), sendButton);
        bounce.setByY(-5);
        bounce.setAutoReverse(true);
        bounce.setCycleCount(2);
        bounce.play();
        userInput.clear();
    }
}
