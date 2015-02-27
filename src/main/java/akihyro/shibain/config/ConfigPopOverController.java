package akihyro.shibain.config;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * 設定ポップオーバーコントローラ。
 */
public class ConfigPopOverController implements Initializable {

    /**
     * ルート。
     */
    @FXML
    private ConfigPopOver root;

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

    /** {@inheritDoc} */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // プロパティを同期させる
        Bindings.bindBidirectional(userIdField.textProperty(), root.userIdProperty());
        Bindings.bindBidirectional(passwordField.textProperty(), root.passwordProperty());

    }

}
