package akihyro.shibain;

import java.net.CookieHandler;
import java.net.CookieManager;
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

    /**
     * Cookie マネージャ。
     */
    private CookieManager cookieManager;

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        webEngine = webView.getEngine();

        cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

    }

    /**
     * 出勤する。
     */
    public void checkin() throws Exception {
        loadLoginPage()
                .thenCompose((v) -> login())
                .thenCompose((v) -> punchIn());
    }

    /**
     * 退勤する。
     */
    public void checkout() throws Exception {
        loadLoginPage()
                .thenCompose((v) -> login())
                .thenCompose((v) -> punchOut());
    }

    /**
     * ロードステートフューチャーを生成する。
     *
     * @return ロードステートフューチャー。
     */
    private WorkerStateFuture newLoadStateFuture() {
        return new WorkerStateFuture(webEngine.getLoadWorker().stateProperty());
    }

    /**
     * ログインページをロードする。
     * Cookie（セッション）はクリアしてからロードする。
     *
     * @return ロードステートフューチャー。
     */
    private WorkerStateFuture loadLoginPage() {
        WorkerStateFuture future = newLoadStateFuture();
        cookieManager.getCookieStore().removeAll();
        webEngine.load("https://tsubaiso.net");
        return future;
    }

    /**
     * ログインする。
     *
     * @return ロードステートフューチャー。
     */
    private WorkerStateFuture login() {
        WorkerStateFuture future = newLoadStateFuture();
        webEngine.executeScript("document.getElementById('post_login').value = 'mailaddress'");
        webEngine.executeScript("document.getElementById('post_password').value = 'password'");
        webEngine.executeScript("document.getElementsByName('commit')[0].click()");
        return future;
    }

    /**
     * 出勤する。
     *
     * @return ロードステートフューチャー。
     */
    private WorkerStateFuture punchIn() {
        WorkerStateFuture future = newLoadStateFuture();
        webEngine.executeScript("document.getElementById('punch_in_button').click()");
        return future;
    }

    /**
     * 退勤する。
     *
     * @return ロードステートフューチャー。
     */
    private WorkerStateFuture punchOut() {
        WorkerStateFuture future = newLoadStateFuture();
        webEngine.executeScript("document.getElementById('punch_out_button').click()");
        return future;
    }

}
