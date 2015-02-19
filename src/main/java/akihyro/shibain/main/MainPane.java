package akihyro.shibain.main;

import java.net.URL;
import java.util.ResourceBundle;

import akihyro.shibain.TsubaisoController;
import akihyro.shibain.config.ConfigPopOver;
import static akihyro.shibain.util.FxmlLoadingUtils.loadControlledFxml;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;

/**
 * メインペイン。
 */
public class MainPane extends VBox implements Initializable {

    /**
     * 設定ポップオーバー。
     */
    @FXML
    private ConfigPopOver configPopOver;

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
     * コンストラクタ。
     */
    public MainPane() {
        loadControlledFxml(MainPane.class, this);
    }

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tsubaisoController = new TsubaisoController(webView.getEngine());
    }

    /**
     * 出勤する。
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    public void checkin(ActionEvent event) throws Exception {
        tsubaisoController.loadLoginPage()
                .thenCompose((v) -> tsubaisoController.login(configPopOver.getUserId(), configPopOver.getPassword()))
                .thenCompose((v) -> tsubaisoController.punchIn())
                .thenRun(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
                .thenCompose((v) -> tsubaisoController.login(configPopOver.getUserId(), configPopOver.getPassword()))
                .thenCompose((v) -> tsubaisoController.punchOut())
                .thenRun(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
            configPopOver.show(this,
                    bounds.getMinX() + bounds.getWidth() / 2,
                    bounds.getMinY() + bounds.getHeight() - 10);
        } else {
            configPopOver.hide();
        }
    }

}
