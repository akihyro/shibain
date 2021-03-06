package akihyro.shibain.config;

import java.util.prefs.Preferences;

import org.controlsfx.control.PopOver;

import akihyro.shibain.property.PreferencesStringProperty;
import static akihyro.shibain.util.FxmlLoadingUtils.loadFxml;
import javafx.beans.DefaultProperty;
import javafx.beans.property.StringProperty;

/**
 * 設定ポップオーバー。
 */
@DefaultProperty("contentNode")
public class ConfigPopOver extends PopOver {

    /**
     * プリファレンス。
     */
    private final Preferences prefs = Preferences.userNodeForPackage(ConfigPopOver.class);

    /**
     * ユーザID。
     */
    private final StringProperty userId = new PreferencesStringProperty(prefs, this, "userId", "");

    /**
     * パスワード。
     */
    private final StringProperty password = new PreferencesStringProperty(prefs, this, "password", "");

    /**
     * コンストラクタ。
     */
    public ConfigPopOver() {
        loadFxml(ConfigPopOver.class, this);
    }

    /**
     * ユーザIDにアクセスする。
     *
     * @return ユーザID。
     */
    public StringProperty userIdProperty() {
        return userId;
    }

    /**
     * ユーザIDを取得する。
     *
     * @return ユーザID。
     */
    public String getUserId() {
        return userId.get();
    }

    /**
     * ユーザIDをセットする。
     *
     * @param userId ユーザID。
     */
    public void setUserId(String userId) {
        this.userId.set(userId);
    }

    /**
     * パスワードにアクセスする。
     *
     * @return パスワード。
     */
    public StringProperty passwordProperty() {
        return password;
    }

    /**
     * パスワードを取得する。
     *
     * @return パスワード。
     */
    public String getPassword() {
        return password.get();
    }

    /**
     * パスワードをセットする。
     *
     * @param password パスワード。
     */
    public void setPassword(String password) {
        this.password.set(password);
    }

}
