package com.example.ludosnake;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class LudoSnake extends Application {
    Group tileGroup = new Group();

    public static final int tileSize = 40;
    int height= 10;
    int width = 10;

    int yLine=430;
    int xLine=40;
    
    int diceValue=1;
    
    Label randResult;
    
    Player playerOne,playerTwo;
    
    boolean gameStart= true,playerOneTurn = true,playerTwoTurn = false;

    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+80);
        root.getChildren().addAll(tileGroup);


        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++) {
                Tile tile= new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                tileGroup.getChildren().addAll(tile);
            }
        }

        playerOne = new Player(tileSize, Color.WHITE);
        playerTwo =  new Player(tileSize-10,Color.BLACK);
        
        randResult = new Label("Game not started");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);



        Button player1Button = new Button("Player 1");
        player1Button.setTranslateX(10);
        player1Button.setTranslateY(yLine);
        
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerOneTurn){
                        getDiceValue();
                        randResult.setText("PlayerOne -"+ String.valueOf(diceValue));
                        //move the player
                        playerOne.movePlayer(diceValue);
                        playerOneTurn = false;
                        playerTwoTurn = true;
                        playerOne.playerAtSnakeOrLadder();
                    }
                }
                
            }
        });
        
        
        
        

        Button player2Button = new Button("Player 2");
        player2Button.setTranslateX(300);
        player2Button.setTranslateY(yLine);

        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerTwoTurn){
                        getDiceValue();
                        randResult.setText("PlayerTwo -"+ String.valueOf(diceValue));
                        //move the player
                        playerTwo.movePlayer(diceValue);
                        playerOneTurn = true;
                        playerTwoTurn = false;
                        playerTwo.playerAtSnakeOrLadder();
                    }
                }

            }
        });


        Button gameButton= new Button("Start Game");
        gameButton.setTranslateX(150);
        gameButton.setTranslateY(yLine);


        Image img;
        img = new Image("C:\\Users\\Raman\\IdeaProjects\\LudoSnake\\src\\snakenew.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitHeight(tileSize*height);
        boardImage.setFitWidth(tileSize*width);


        tileGroup.getChildren().addAll(boardImage,randResult,playerOne.getGamePiece(),playerTwo.getGamePiece(),player1Button,player2Button,gameButton);



        return root;

    }
    public void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);
    }

    @Override
    public void start(Stage stage) throws IOException {
      //  FXMLLoader fxmlLoader = new FXMLLoader(LudoSnake.class.getResource("hello-view.fxml"));
        Scene scene;
        scene = new Scene(createContent());
        stage.setTitle("LudoSnake");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}