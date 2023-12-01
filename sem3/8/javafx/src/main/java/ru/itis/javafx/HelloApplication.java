package ru.itis.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.itis.utils.Cocktail;
import ru.itis.utils.RandomCocktailReceiver;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Cocktail App");

        Button getCocktailButton = new Button("Get a cocktail");
        getCocktailButton.setOnAction(e -> showCocktailDialog());

        BorderPane root = new BorderPane();
        root.setCenter(getCocktailButton);

        Scene scene = new Scene(root, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void showCocktailDialog() {
        try {
            Cocktail randomCocktail = RandomCocktailReceiver.getRandomCocktail();
            showCocktailDetails(randomCocktail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCocktailDetails(Cocktail cocktail) {
        Stage dialogStage = new Stage();
        dialogStage.setTitle(cocktail.getStrDrink());
        dialogStage.initModality(Modality.WINDOW_MODAL);

        VBox dialogVBox = new VBox(10);

        VBox nameLabelVBox = new VBox();
        nameLabelVBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label(cocktail.getStrDrink());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameLabelVBox.getChildren().add(nameLabel);

        Label instructionsLabel = new Label("Instructions: " + cocktail.getStrInstructions());
        Label alcoholicLabel = new Label("Alcoholic: " + cocktail.getStrAlcoholic());
        Label glassLabel = new Label("Glass: " + cocktail.getStrGlass());
        Label ingredientsLabel = new Label("Ingredients: " + cocktail.getStrIngredients());

        Image image = new Image(cocktail.getStrDrinkThumb().replaceAll("\\\\", ""));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(512);
        imageView.setPreserveRatio(true);

        dialogVBox.getChildren().addAll(nameLabelVBox, instructionsLabel, alcoholicLabel, glassLabel, ingredientsLabel, imageView);

        Scene dialogScene = new Scene(dialogVBox, 512, 720);
        dialogStage.setScene(dialogScene);
        dialogStage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}