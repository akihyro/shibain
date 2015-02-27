package akihyro.shibain.config;

import org.controlsfx.control.PopOver;

import static akihyro.shibain.util.FxmlLoadingUtils.loadFxml;
import javafx.beans.DefaultProperty;
import javafx.fxml.FXMLLoader;

/**
 * 設定ポップオーバー。
 */
@DefaultProperty("contentNode")
public class ConfigPopOver extends PopOver {

    /**
     * コントローラ。
     */
    private final ConfigPopOverController controller;

    /**
     * コンストラクタ。
     */
    public ConfigPopOver() {
        FXMLLoader loader = loadFxml(ConfigPopOver.class, this);
        controller = loader.getController();
    }

    /**
     * ユーザIDを取得する。
     *
     * @return ユーザID。
     */
    public String getUserId() {
        return controller.getUserId();
    }

    /**
     * パスワードを取得する。
     *
     * @return パスワード。
     */
    public String getPassword() {
        return controller.getPassword();
    }

}
