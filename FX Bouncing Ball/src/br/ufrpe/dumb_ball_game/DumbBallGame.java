package br.ufrpe.dumb_ball_game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DumbBallGame extends Application implements EventHandler<ActionEvent> {
    
    private double dx = 7; //Componente x da velocidade
    private double dy = 3; //Componente y da velocidade
    private Circle ball;
    private Pane canvas;

    @Override
    public void start(Stage stage) {

        this.canvas = new Pane();
        Scene scene = new Scene(canvas, 300, 300, Color.TRANSPARENT);
        
        this.ball = new Circle(10, Color.DARKSLATEBLUE);
        this.ball.relocate(5, 5);

        canvas.getChildren().add(ball);

        // Descomentar se quiser deixar o canvas transparente
//        stage.initStyle(StageStyle.TRANSPARENT);
        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.setTitle("Animated Ball");
        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), this));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void handle(ActionEvent event) {
        //Mover a bola
        ball.setLayoutX(ball.getLayoutX() + dx);
        ball.setLayoutY(ball.getLayoutY() + dy);

        Bounds bounds = canvas.getBoundsInLocal();

        //Se a bola atingir a bordar esquerda ou direita, inverta a velocidade
        if(ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius()) ||
                ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()) ){
            dx = -dx;
        }

        //If the ball reaches the bottom or top border make the step negative
        if((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius())) ||
                (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius()))){
            dy = -dy;

        }
    }
}