package com.sample.hex;




import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;



import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Circle CrT;

    @FXML
    private Circle CrTh;

    @FXML
    private Circle crOn;

    @FXML
    private Text krutouText;

    @FXML
    private ImageView losePic;

    @FXML
    private Circle bullet;

    @FXML
    private Rectangle playerOne;

    private boolean lose = false;
    private double MoveSpeedY = Math.random()*6-3;
    private double MoveSpeedX = Math.random()*6-3;
    private final double speed = 0.2;
    private AnimationTimer timer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dance();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }

        };


    }

    @FXML
    void pressed(KeyEvent event) {

        switch (event.getCode()) {
            case LEFT -> player1MoveLeft();
            case RIGHT -> player1MoveRight();
            case ENTER -> {
                timer.start();
                krutouText.setVisible(false);
                playerOne.setVisible(true);
                bullet.setVisible(true);
                crOn.setVisible(false);
                CrT.setVisible(false);
                CrTh.setVisible(false);
            }
        }
    }

    private void update() {
        if(!lose) {
            moveY();
            moveX();
        }
    }
    private void moveY(){
        double pos1x = playerOne.getX() + playerOne.getLayoutX();
        double pos1y = playerOne.getLayoutY();
        if(bullet.getCenterY() >= 340){
            losePic.setVisible(true);
            lose = true;
        }
        if (((bullet.getCenterY() + bullet.getLayoutY()) >= pos1y -5 && pos1y <=(bullet.getCenterY() + bullet.getLayoutY())) && ((pos1x <= (bullet.getCenterX() + bullet.getLayoutX())) && ((bullet.getCenterX() + bullet.getLayoutX()) <= pos1x +100)) ){
            MoveSpeedY = (speed * -1) + MoveSpeedY * -1;
        }
        if (bullet.getCenterY() <= -352){
            MoveSpeedY = speed + MoveSpeedY * -1;
        }
        bullet.setCenterY(bullet.getCenterY() + MoveSpeedY );
    }

    private void moveX(){
        if(bullet.getCenterX() >= 302){
            MoveSpeedX = ( speed * -1 )+ MoveSpeedX * -1;
        }
        if (bullet.getCenterX() <= -302){
            MoveSpeedX =  speed + MoveSpeedX * -1;
        }

        bullet.setCenterX(bullet.getCenterX() + MoveSpeedX );
    }

    private void player1MoveLeft(){
        if( !(playerOne.getX() + playerOne.getLayoutX() <= 5)) {
            playerOne.setX(playerOne.getX() - 5);
        }

    }
    private void player1MoveRight(){
        if( !(playerOne.getX() + playerOne.getLayoutX() >= 500)) {
            playerOne.setX(playerOne.getX() + 5);
        }

    }
    private void Dance(){
        RotateTransition tr1 = new RotateTransition();
        tr1.setNode(crOn);
        RotateTransition tr2 = new RotateTransition();
        tr2.setNode(CrT);
        RotateTransition tr3 = new RotateTransition();
        tr3.setNode(CrTh);
        tr1.setAutoReverse(true);
        tr2.setAutoReverse(true);
        tr3.setAutoReverse(true);

        tr1.setByAngle(360);
        tr2.setByAngle(-360);
        tr3.setByAngle(360);

        tr1.setDuration(Duration.millis(3000));
        tr2.setDuration(Duration.millis(3000));
        tr3.setDuration(Duration.millis(3000));

        tr1.setRate(0.5);
        tr2.setRate(1);

        tr1.setCycleCount(RotateTransition.INDEFINITE);
        tr2.setCycleCount(RotateTransition.INDEFINITE);
        tr3.setCycleCount(RotateTransition.INDEFINITE);

        tr1.play();
        tr2.play();
        tr3.play();
    }

}


