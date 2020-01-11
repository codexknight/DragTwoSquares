package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static class SquareData {
        double x, y;
        Color color;
    }

    private ArrayList<SquareData> squares;

    private Canvas canvas;
    
    
    

    @Override
    public void start(Stage stage) {

        squares = new ArrayList<SquareData>();

        canvas = new Canvas(640, 480);
        draw();

        canvas.setOnMousePressed(e -> mousePressed(e));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
        canvas.setOnMouseReleased(e -> mouseReleasead(e));

        Pane root = new Pane(canvas);

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Click to add a square. Right-click to drag.");
        stage.setResizable(false);
        stage.show();
    }

    private void mouseReleasead(MouseEvent e) {
    }

    private void mouseDragged(MouseEvent e) {
    }

    private void mousePressed(MouseEvent e) {
    }

    private void draw() {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.rgb(230, 255, 230));
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.setLineWidth(2);
        g.setStroke(Color.BLACK);
        for (SquareData squareData : squares) {
            g.setFill(squareData.color);
            g.fillRect(squareData.x - 50, squareData.y - 50, 100, 100);
            g.strokeRect(squareData.x - 50, squareData.y - 50, 100, 100);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}