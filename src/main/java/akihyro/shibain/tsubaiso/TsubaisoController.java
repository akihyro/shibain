package akihyro.shibain.tsubaiso;

import java.util.concurrent.CompletableFuture;

import lombok.NonNull;

import javafx.scene.web.WebEngine;

/**
 * ツバイソコントローラ。
 */
public class TsubaisoController {

    /**
     * WEBエンジン。
     */
    private final WebEngine engine;

    /**
     * コンストラクタ。
     *
     * @param engine WEBエンジン。
     */
    public TsubaisoController(@NonNull WebEngine engine) {
        this.engine = engine;
    }

    /**
     * ログインページのロードを開始する。
     *
     * @return フューチャー。
     */
    public CompletableFuture<Void> loadLoginPage() {
        CompletableFuture<Void> future = createLoadStateFuture();
        engine.load(createUrl("/top/login"));
        return future;
    }

    /**
     * ログインを開始する。
     *
     * @param id ユーザID。
     * @param password パスワード。
     * @return フューチャー。
     */
    public CompletableFuture<Void> login(@NonNull String id, @NonNull String password) {
        CompletableFuture<Void> future = createLoadStateFuture();
        engine.executeScript("document.getElementById('post_login').value = '" + id + "'");
        engine.executeScript("document.getElementById('post_password').value = '" + password + "'");
        engine.executeScript("document.getElementsByName('commit')[0].click()");
        return future;
    }

    /**
     * 出勤ボタンを押す。
     *
     * @return フューチャー。
     */
    public CompletableFuture<Void> punchIn() {
        engine.executeScript("document.getElementById('punch_in_button').click()");
        return WorkerStateFuture.completedFuture(null);
    }

    /**
     * 退勤ボタンを押す。
     *
     * @return フューチャー。
     */
    public CompletableFuture<Void> punchOut() {
        engine.executeScript("document.getElementById('punch_out_button').click()");
        return WorkerStateFuture.completedFuture(null);
    }

    /**
     * ロードステートフューチャーを生成する。
     *
     * @return フューチャー。
     */
    private CompletableFuture<Void> createLoadStateFuture() {
        return new WorkerStateFuture(engine.getLoadWorker().stateProperty());
    }

    /**
     * URLを生成する。
     *
     * @param path パス。
     * @return URL。
     */
    private String createUrl(String path) {
        return "https://tsubaiso.net" + path;
    }

}
