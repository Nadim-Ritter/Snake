package snake;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class GuiController implements Initializable {

    @FXML
    AnchorPane pane;

    @FXML
    Rectangle snakeHead;

    @FXML
    Pane playField;

    Timeline timeline = new Timeline();
    ArrayList<Rectangle> snakeBody = new ArrayList();
    
    String direction;
    Rectangle food;
    double size = 15;
    
    @FXML
    public void start() {
        spawnFood();

        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {
            snakeHead.setTranslateX(snakeHead.getTranslateX() + size);
            if (snakeHead.getTranslateX() + size >= 570 - (snakeHead.getLayoutX())) {
                timeline.stop();
            }

        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        pane.getScene().getRoot().requestFocus();

    }

    @FXML
    public void move(KeyEvent key) {

        timeline.stop();

        if (null != key.getCode()) {
            if (key.getCode() == KeyCode.DOWN) {
                direction = "down";
            } else if (key.getCode() == KeyCode.UP) {
                direction = "up";
            } else if (key.getCode() == KeyCode.RIGHT) {
                direction = "right";
            } else if (key.getCode() == KeyCode.LEFT) {
                direction = "left";
            }
        }

        timeline.getKeyFrames().remove(0);
        KeyFrame frame = new KeyFrame(Duration.seconds(0.2), event -> {

            if (direction.equals("right")) {
                snakeHead.setTranslateX(snakeHead.getTranslateX() + size);
                if (snakeHead.getTranslateX() + size >= 570 - (snakeHead.getLayoutX())) {
                    timeline.stop();
                }

            } else if (direction.equals("left")) {
                snakeHead.setTranslateX(snakeHead.getTranslateX() - size);
                if (snakeHead.getTranslateX() <= -(snakeHead.getLayoutX())) {
                    timeline.stop();
                }

            } else if (direction.equals("up")) {
                snakeHead.setTranslateY(snakeHead.getTranslateY() - size);
                if (snakeHead.getTranslateY() <= -(snakeHead.getLayoutY())) {
                    timeline.stop();
                }

            } else if (direction.equals("down")) {
                snakeHead.setTranslateY(snakeHead.getTranslateY() + size);
                if (snakeHead.getTranslateY() + size >= 330 - (snakeHead.getLayoutY())) {
                    timeline.stop();
                }
            }
            
            if(detectFood(snakeHead.getTranslateX(), snakeHead.getTranslateY())){
                System.out.println("COLLISION");
            }
            

        });

        
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    
    @FXML
    public void spawnFood(){
        Random r = new Random();
        
        int x = r.nextInt(585) + 15;
        int y = r.nextInt(386) + 56;
        
        food = new Rectangle(210, 300, size, size);
        food.setFill(Paint.valueOf("#ffcc1f"));
        food.setStroke(Paint.valueOf("000000"));
        food.setStrokeWidth(1);
        food.setStrokeType(StrokeType.INSIDE);
        
        pane.getChildren().add(food);

    }
    
    @FXML
    public boolean detectFood(double snakeX, double snakeY){
        boolean detection = false;
        System.out.println("snakeX: " + snakeX);
        System.out.println("snakeY: " + snakeY);
        
        System.out.println("foodX: " + food.getX());
        System.out.println("foodY: " + food.getY());
        
            if(snakeX >= food.getX()+size && snakeX+size <= food.getX() && snakeY >= food.getY()+size && snakeY+size <= food.getY()){
                detection = true;
            
            
//            if(snakeX > food.getX() && (snakeY == food.getY() || (snakeY >= food.getY()-(size-1) && snakeY <= food.getY()+(size*2)-1))){
//                detection = true;
//            }  
        }
        
        
        
        
        
        
        
        
        
        return detection;       
    }
    

    @FXML
    public void registerKeys(KeyEvent key) {

//        if (null != key.getCode()) {
//            if (key.getCode() == KeyCode.DOWN) {
//                timeline.stop();
//                timeline.getKeyFrames().remove(0);
//                timeline.getKeyFrames().add(new KeyFrame(
//                        Duration.seconds(3),
//                        new KeyValue(rectangle.yProperty(), 334 - (rectangle.getLayoutY() + rectangle.getHeight()))
//                ));
//                timeline.play();
//                snake.setX(rectangle.xProperty().get());
//                snake.setY(rectangle.getLayoutY());
//
//            } else if (key.getCode() == KeyCode.LEFT) {
//                timeline.stop();
//                timeline.getKeyFrames().remove(0);
//                timeline.getKeyFrames().add(new KeyFrame(
//                        Duration.seconds(3),
//                        new KeyValue(rectangle.xProperty(), -(rectangle.getLayoutX()))
//                ));
//                timeline.play();
//                snake.setX(rectangle.getLayoutX());
//                snake.setY(rectangle.yProperty().get());
//
//            } else if (key.getCode() == KeyCode.UP) {
//                timeline.stop();
//                timeline.getKeyFrames().remove(0);
//                timeline.getKeyFrames().add(new KeyFrame(
//                        Duration.seconds(3),
//                        new KeyValue(rectangle.yProperty(), -(rectangle.getLayoutY()))
//                ));
//                timeline.play();
//                snake.setX(rectangle.xProperty().get());
//                snake.setY(rectangle.getLayoutY());
//
//            } else if (key.getCode() == KeyCode.RIGHT) {
//                timeline.stop();
//                timeline.getKeyFrames().remove(0);
//                timeline.getKeyFrames().add(new KeyFrame(
//                        Duration.seconds(3),
//                        new KeyValue(rectangle.xProperty(), 566 - (rectangle.getLayoutX() + rectangle.getWidth()))
//                ));
//                timeline.play();
//
//                if ((rectangle.getLayoutX() + rectangle.getX()) == test.getLayoutX()) {
//                    System.out.println("test");
//                }
//
//                snake.setX(rectangle.getLayoutX());
//                snake.setY(rectangle.yProperty().get());
//
//            }
//
//        }
    }

    public void checkIfHitObject() {

        System.out.println("test");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
