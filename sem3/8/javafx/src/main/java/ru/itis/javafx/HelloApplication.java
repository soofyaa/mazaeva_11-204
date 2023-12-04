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

        Stage stage = new Stage();
        stage.setTitle(cocktail.getDrinks().get(0).getStrDrink());
        stage.initModality(Modality.WINDOW_MODAL);

        VBox dialogVBox = new VBox(10);

        VBox nameLabelVBox = new VBox();
        nameLabelVBox.setAlignment(Pos.CENTER);
        Label nameLabel = new Label(cocktail.getDrinks().get(0).getStrDrink());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        nameLabelVBox.getChildren().add(nameLabel);

        Label instructionsLabel = new Label("Instructions: " + cocktail.getDrinks().get(0).getStrInstructions());
        Label alcoholicLabel = new Label("Alcoholic: " + cocktail.getDrinks().get(0).getStrAlcoholic());
        Label glassLabel = new Label("Glass: " + cocktail.getDrinks().get(0).getStrGlass());
        Label ingredientsLabel = new Label("Ingredients: " + cocktail.getDrinks().get(0).getStrIngredient1() + ", " +
                cocktail.getDrinks().get(0).getStrIngredient2() + ", " + cocktail.getDrinks().get(0).getStrIngredient3());
        ImageView imageView = new ImageView();

        if (cocktail.getDrinks().get(0).getStrDrinkThumb() != null) {
            Image image = new Image(cocktail.getDrinks().get(0).getStrDrinkThumb().replaceAll("\\\\", ""));
            imageView = new ImageView(image);
            imageView.setFitHeight(512);
            imageView.setPreserveRatio(true);
        }

        dialogVBox.getChildren().addAll(nameLabelVBox, instructionsLabel, alcoholicLabel, glassLabel, ingredientsLabel, imageView);

        Scene scene = new Scene(dialogVBox, 512, 720);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}