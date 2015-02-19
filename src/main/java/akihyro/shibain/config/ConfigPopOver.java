package akihyro.shibain.config;

import org.controlsfx.control.PopOver;

import static akihyro.shibain.util.FxmlLoadingUtils.loadControlledFxml;
import javafx.beans.DefaultProperty;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * 設定ポップオーバー。
 */
@DefaultProperty("contentNode")
public class ConfigPopOver extends PopOver {

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
     * コンストラクタ。
     */
    public ConfigPopOver() {
        loadControlledFxml(ConfigPopOver.class, this);
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

}
