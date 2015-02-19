package akihyro.shibain.main;

import java.net.URL;
import java.util.ResourceBundle;

import akihyro.shibain.tsubaiso.TsubaisoWalker;
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

    /**
     * コンストラクタ。
     */
    public MainPane() {
        loadControlledFxml(MainPane.class, this);
    }

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configPopOver = new ConfigPopOver();
        tsubaisoWalker = new TsubaisoWalker(webView.getEngine());
    }

    /**
     * 出勤する。
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    @FXML
    protected void checkin(ActionEvent event) throws Exception {
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
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    @FXML
    protected void checkout(ActionEvent event) throws Exception {
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
     *
     * @param event イベント。
     * @throws Exception エラーが発生した場合。
     */
    @FXML
    protected void toggleConfig(ActionEvent event) throws Exception {
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
