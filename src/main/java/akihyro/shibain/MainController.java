package akihyro.shibain;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * メインコントローラ。
 */
public class MainController implements Initializable {

    /**
     * WEBビュー。
     */
    @FXML
    private WebView webView;

    /**
     * WEBエンジン。
     */
    private WebEngine webEngine;

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        webEngine.load("https://tsubaiso.net");
    }

    /**
     * 出勤する。
     */
    public void checkin() {
        // TODO: 出勤実装！
    }

    /**
     * 退勤する。
     */
    public void checkout() {
        // TODO: 退勤実装！
    }

}
