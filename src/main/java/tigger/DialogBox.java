package tigger;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * Used ChatGPT to help with the implementation of GUI choices and styling.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private StackPane bubble;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        displayPicture.setImage(img);
        if (img == null) {
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);
        } else {
            double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
            Circle clip = new Circle(displayPicture.getFitWidth() / 2.0, displayPicture.getFitHeight() / 2.0, radius);
            displayPicture.setClip(clip);
            displayPicture.setVisible(true);
            displayPicture.setManaged(true);
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.bubble.getStyleClass().add("user-bubble");
        db.dialog.getStyleClass().add("label");
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    public static DialogBox getTiggerDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.bubble.getStyleClass().add("tigger-bubble");
        db.dialog.getStyleClass().add("label");
        db.flip();
        return db;
    }
}
