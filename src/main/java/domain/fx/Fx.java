package domain.fx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
*
* @author Uthpala Bandara
*/
public class Fx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ScrollPane scrollPane = new ScrollPane();
        VBox vb = new VBox();
        for (int i = 0; i < 30; i++) {
            HBox hb = new HBox();

            Label label = new Label("Number" + i);
            TextField textField = new TextField();
            textField.setDisable(false);
            hb.getChildren().addAll(label, textField);
           hb.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
               public void handle(MouseEvent e) {
                   hb.requestFocus();
                   e.consume();
               }
           });

            hb.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(final KeyEvent keyEvent) {
                    if (keyEvent.getCode() == KeyCode.DOWN) {
                        vb.getChildren().get(vb.getChildren().indexOf(hb) + 1).requestFocus();
                        double heightScrollPane = scrollPane.getContent().getBoundsInLocal().getHeight();
                        double heightViewPort = scrollPane.getViewportBounds().getHeight();
                        double y = vb.getChildren().get(vb.getChildren().indexOf(hb) + 1).getBoundsInParent().getMaxY();

                        scrollPane.setVvalue((y - heightViewPort) / (heightScrollPane - heightViewPort));

                    } else if (keyEvent.getCode() == KeyCode.UP) {
                        vb.getChildren().get(vb.getChildren().indexOf(hb) - 1).requestFocus();

                    }
                }
            });

            hb.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    System.out.println(newValue ? vb.getChildren().indexOf(hb) + "Focused"
                            : vb.getChildren().indexOf(hb) + " Unfocused");
                    if (newValue) {
                        hb.setStyle("-fx-background-color: red");
                        hb.getChildren().get(0).setStyle("-fx-text-fill: green;");
                    } else {
                        hb.setStyle(null);
                        hb.getChildren().get(0).setStyle(null);
                    }
                }
            });

            vb.getChildren().add(hb);
        }
        scrollPane.setContent(vb);

        Stage stage = new Stage();

        Scene sc = new Scene(scrollPane);
        sc.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                if (!vb.getChildren().isEmpty()) {
                    vb.getChildren().get(0).requestFocus();
                }
            }
        });

        stage.setScene(sc);
        stage.setWidth(100);
        stage.setHeight(300);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}