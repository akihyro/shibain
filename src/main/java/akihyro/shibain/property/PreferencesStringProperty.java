package akihyro.shibain.property;

import java.util.prefs.Preferences;

import lombok.NonNull;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

/**
 * プリファレンス文字列プロパティ。
 *
 * {@link Preferences} と同期することで、プロパティの値を永続化する。
 */
public class PreferencesStringProperty extends SimpleStringProperty {

    /**
     * プリファレンス。
     */
    private final Preferences prefs;

    /**
     * コンストラクタ。
     *
     * @param prefs プリファレンス。
     * @param bean Bean。
     * @param name 名前。
     * @param def デフォルト値。
     */
    public PreferencesStringProperty(@NonNull Preferences prefs, Object bean, String name, String def) {
        super(bean, name, prefs.get(name, def));
        this.prefs = prefs;
        addListener(this::handleChanged);
    }

    /**
     * 値変更時の処理を行う。
     *
     * @param observable 監視対象。
     * @param oldValue 変更前の値。
     * @param newValue 変更後の値。
     */
    private void handleChanged(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        // プリファレンスに反映する
        prefs.put(getName(), getValue());
    }

}
