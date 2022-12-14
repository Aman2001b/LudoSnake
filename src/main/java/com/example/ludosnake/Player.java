package com.example.ludosnake;


import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle gamePiece;
    int xPosition;
    int yPosition;
    int currentPiecePosition;

    static GameBoard gameBoard;



    Player(int tileSize, Color pieceColor) {
        gameBoard = new GameBoard();
        this.currentPiecePosition = 1;
        this.xPosition = gameBoard.getXValue(this.currentPiecePosition);
        this.yPosition = gameBoard.getYValue(this.currentPiecePosition);

        gamePiece = new Circle(tileSize / 2);
        gamePiece.setFill(pieceColor);
        gamePiece.setTranslateX(this.xPosition);
        gamePiece.setTranslateY(this.yPosition);


    }

    public void movePlayer(int diceValue) {
        if (this.currentPiecePosition + diceValue <= 100) {
            this.currentPiecePosition += diceValue;
        }
        translatePlayer();
    }


    private void translatePlayer() {
        this.xPosition = gameBoard.getXValue(this.currentPiecePosition);
        this.yPosition = gameBoard.getYValue(this.currentPiecePosition);
         //to slow down the movement of gamepiece
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000), this.gamePiece);
        animate.setToX(this.xPosition);
        animate.setToY(this.yPosition);
        animate.play();



    }


    public void playerAtSnakeOrLadder() {
        int newPosition = gameBoard.playerPositionAtSnakeOrLadder(this.currentPiecePosition);
        if (newPosition != -1) {
            this.currentPiecePosition = newPosition;
            translatePlayer();
        }
    }



    public Circle getGamePiece() {
        return this.gamePiece;
    }
}