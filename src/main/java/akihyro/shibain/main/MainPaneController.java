package akihyro.shibain.main;

import java.net.URL;
import java.util.ResourceBundle;

import akihyro.shibain.config.ConfigPopOver;
import akihyro.shibain.tsubaiso.TsubaisoWalker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;

/**
 * メインペインコントローラ。
 */
public class MainPaneController implements Initializable {

    /**
     * ルート。
     */
    @FXML
    private MainPane root;

    /**
     * 設定ボタン。
     */
    @FXML
    private Button configButton;

    /**
     * WEBビュー。
     */
    @FXML
    private WebView webView;

    /**
     * 設定ポップオーバー。
     */
    private ConfigPopOver configPopOver;

    /**
     * ツバイソウォーカー。
     */
    private TsubaisoWalker tsubaisoWalker;

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configPopOver = new ConfigPopOver();
        tsubaisoWalker = new TsubaisoWalker(webView.getEngine());
    }

    /**
     * 出勤する。
     */
    public void checkin() {
        tsubaisoWalker.loadLoginPage()
                .thenCompose((v) -> tsubaisoWalker.login(configPopOver.getUserId(), configPopOver.getPassword()))
                .thenCompose((v) -> tsubaisoWalker.punchIn())
                .thenRun(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("出勤");
                    alert.setContentText("出勤しました！");
                    alert.show();
                });
    }

    /**
     * 退勤する。
     */
    public void checkout() {
        tsubaisoWalker.loadLoginPage()
                .thenCompose((v) -> tsubaisoWalker.login(configPopOver.getUserId(), configPopOver.getPassword()))
                .thenCompose((v) -> tsubaisoWalker.punchOut())
                .thenRun(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("退勤");
                    alert.setContentText("退勤しました！");
                    alert.show();
                });
    }

    /**
     * 設定の表示を切り替える。
     */
    public void toggleConfig() {
        if (!configPopOver.isShowing()) {
            Bounds bounds = configButton.localToScreen(configButton.getBoundsInLocal());
            configPopOver.show(root,
                    bounds.getMinX() + bounds.getWidth() / 2,
                    bounds.getMinY() + bounds.getHeight() - 10);
        } else {
            configPopOver.hide();
        }
    }

}
