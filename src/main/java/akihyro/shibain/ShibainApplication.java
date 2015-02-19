package akihyro.shibain;

import akihyro.shibain.main.MainPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * アプリケーション。
 */
public class ShibainApplication extends Application {

    /**
     * エントリポイント。
     *
     * @param args パラメータ。
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 開始する。
     *
     * @param stage ステージ。
     * @throws Exception エラーが発生した場合。
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new MainPane()));
        stage.setTitle("shibain");
        stage.show();
    }

}
