package tigger;

import java.io.IOException;
import java.util.Collections;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableNumberValue;
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

        // If image failed to load (null), hide the ImageView and remove it from layout calculations
        displayPicture.setImage(img);
        if (img == null) {
            displayPicture.setVisible(false);
            displayPicture.setManaged(false);
        } else {
            // Apply a circular clip so the image appears rounded
            double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
            Circle clip = new Circle(displayPicture.getFitWidth() / 2.0, displayPicture.getFitHeight() / 2.0, radius);
            displayPicture.setClip(clip);
            displayPicture.setVisible(true);
            displayPicture.setManaged(true);
        }
    }

    /**
     * Bind the bubble's maximum width relative to a parent width (e.g., the dialog container).
     * This keeps bubbles from stretching across the entire window and allows them to scale when
     * the window is resized/fullscreened.
     */
    public void bindToParentWidth(ObservableNumberValue parentWidth) {
        // Limit bubble width to 70% of the parent width using a binding
        bubble.maxWidthProperty().bind(Bindings.multiply(parentWidth, 0.7));
        // Let the label wrap within the bubble (subtract padding)
        dialog.maxWidthProperty().bind(bubble.maxWidthProperty().subtract(12));
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
        // User bubble: apply CSS class so styling comes from tigger.css
        db.bubble.getStyleClass().add("user-bubble");
        db.dialog.getStyleClass().add("label");
        // For user, leave image on the right (default FXML order: label then image)
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    public static DialogBox getTiggerDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // Tigger bubble: apply CSS class so styling comes from tigger.css
        db.bubble.getStyleClass().add("tigger-bubble");
        db.dialog.getStyleClass().add("label");
        db.flip();
        return db;
    }
}
