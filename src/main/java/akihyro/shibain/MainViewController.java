package akihyro.shibain;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

/**
 * メインビューコントローラ。
 */
public class MainViewController implements Initializable {

    /**
     * ルート。
     */
    @FXML
    private Pane root;

    /**
     * 設定ポップオーバー。
     */
    @FXML
    private PopOver configPopOver;

    /**
     * WEBビュー。
     */
    @FXML
    private WebView webView;

    /**
     * ユーザIDフィールド。
     */
    @FXML
    private TextField userIdField;

    /**
     * パスワードフィールド。
     */
    @FXML
    private PasswordField passwordField;

    /**
     * WEBエンジン。
     */
    private TsubaisoController tsubaisoController;

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tsubaisoController = new TsubaisoController(webView.getEngine());
    }

    /**
     * ユーザIDを取得する。
     *
     * @return ユーザID。
     */
    public String getUserId() {
        return userIdField.getText();
    }

    /**
     * パスワードを取得する。
     *
     * @return パスワード。
     */
    public String getPassword() {
        return passwordField.getText();
    }

    /**
     * 出勤する。
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    public void checkin(ActionEvent event) throws Exception {
        tsubaisoController.loadLoginPage()
                .thenCompose((v) -> tsubaisoController.login(getUserId(), getPassword()))
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
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    public void checkout(ActionEvent event) throws Exception {
        tsubaisoController.loadLoginPage()
                .thenCompose((v) -> tsubaisoController.login(getUserId(), getPassword()))
                .thenCompose((v) -> tsubaisoController.punchOut())
                .thenRun(() -> {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("退勤");
                    alert.setContentText("退勤しました！");
                    alert.show();
                });
    }

    /**
     * 設定の表示を切り替える。
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    public void toggleConfig(ActionEvent event) throws Exception {
        if (!configPopOver.isShowing()) {
            Node node = Node.class.cast(event.getSource());
            Bounds bounds = node.localToScreen(node.getBoundsInLocal());
            configPopOver.show(root,
                    bounds.getMinX() + bounds.getWidth() / 2,
                    bounds.getMinY() + bounds.getHeight() - 10);
        } else {
            configPopOver.hide();
        }
    }

}
