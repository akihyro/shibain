package akihyro.shibain.config;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * 設定ポップオーバーコントローラ。
 */
public class ConfigPopOverController {

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
