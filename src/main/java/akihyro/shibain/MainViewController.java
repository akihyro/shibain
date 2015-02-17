package akihyro.shibain;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebView;

/**
 * メインビューコントローラ。
 */
public class MainViewController implements Initializable {

    /**
     * WEBビュー。
     */
    @FXML
    private WebView webView;

    /**
     * WEBエンジン。
     */
    private TsubaisoController tsubaisoController;

    /**
     * ユーザID。
     */
    private final String id = "user-id";

    /**
     * パスワード。
     */
    private final String password = "password";

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tsubaisoController = new TsubaisoController(webView.getEngine());
    }

    /**
     * 出勤する。
     *
     * @throws Exception エラーが発生した場合。
     */
    public void checkin() throws Exception {
        tsubaisoController.loadLoginPage()
                .thenCompose((v) -> tsubaisoController.login(id, password))
                .thenCompose((v) -> tsubaisoController.punchIn())
                .thenRun(() -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("出勤");
                    alert.setContentText("出勤しました！");
                    alert.show();
                });
    }

    /**
     * 退勤する。
     *
     * @throws Exception エラーが発生した場合。
     */
    public void checkout() throws Exception {
        tsubaisoController.loadLoginPage()
                .thenCompose((v) -> tsubaisoController.login(id, password))
                .thenCompose((v) -> tsubaisoController.punchOut())
                .thenRun(() -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("退勤");
                    alert.setContentText("退勤しました！");
                    alert.show();
                });
    }

    /**
     * 設定する。
     *
     * @throws Exception エラーが発生した場合。
     */
    public void config() throws Exception {
        // TODO: 後で実装する
    }

}
