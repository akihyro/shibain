package akihyro.shibain;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * アプリケーション。
 */
public class ShibainApplication extends Application {

    /**
     * 開始する。
     *
     * @param stage ステージ。
     * @throws Exception エラーが発生した場合。
     */
    @Override
    public void start(Stage stage) throws Exception {

        Button button = new Button(">> Click <<");
        button.setOnAction(e -> System.out.println("Hello JavaFX 8"));

        StackPane root = new StackPane();
        root.getChildren().add(button);

        stage.setScene(new Scene(root));
        stage.setWidth(300);
        stage.setHeight(300);
        stage.setTitle("JavaFX 8 app");
        stage.show();

    }

    /**
    * エントリポイント。
     *
     * @param args パラメータ。
     */
    public static void main(String[] args) {
        launch(args);
    }

}
