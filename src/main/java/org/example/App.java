package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
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

    private boolean dragging;

    private SquareData draggedSquare;

    private double offsetX, offsetY;


    private void mouseReleasead(MouseEvent e) {
    }

    private void mouseDragged(MouseEvent e) {
    }

    private void mousePressed(MouseEvent e) {
        if (dragging) {
            return;
        }
        double x = e.getX();
        double y = e.getY();

        if (e.isShiftDown() || e.getButton() == MouseButton.SECONDARY) {
            for (int i = squares.size() - 1; i >= 0; i--) {
                SquareData squareData = squares.get(i);
                double cx = squareData.x;
                double cy = squareData.y;
                if (x >= cx - 50 && x <= cx + 50 && y >= cy - 50 && y <= cy + 50) {
                    dragging = true;
                    draggedSquare = squareData;
                    offsetX = x - cx;
                    offsetY = y - cy;
                    break;
                }
            }
        } else {
            SquareData squareData = new SquareData();
            squareData.x = x;
            squareData.y = y;
            squareData.color = Color.color(Math.random(), Math.random(), Math.random(), 0.5 + 0.5 * Math.random());
            squares.add(squareData);
            draw();
            
        }
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