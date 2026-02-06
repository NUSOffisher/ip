package Tigger;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/ferb.jpg"));
    private Image tiggerImage = new Image(this.getClass().getResourceAsStream("/images/tigger.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tigger instance */
    public void setTigger(Tigger t) {
        tigger = t;
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
            response = "    ____________________________________________________________\n"
                     + "    " + e + "\n"
                     + "    ____________________________________________________________\n";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTiggerDialog(response, tiggerImage)
        );

        storage.saveTasks(list);

        if (input.trim().equals("bye")) {
            String goodbye = "    ____________________________________________________________\n"
                           + "    Bye. Hope to see you again soon!\n"
                           + "    ____________________________________________________________";
            dialogContainer.getChildren().add(DialogBox.getTiggerDialog(goodbye, tiggerImage));
            Platform.exit();
        }

        userInput.clear();
    }
}
