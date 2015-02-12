package akihyro.shibain;


import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        URL fxmlLocation = ShibainApplication.class.getResource("main.fxml");
        Parent root = FXMLLoader.load(fxmlLocation);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Shibain!!");
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
