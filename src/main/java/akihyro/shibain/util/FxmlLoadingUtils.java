package akihyro.shibain.util;

import java.io.IOException;
import java.net.URL;

import lombok.NonNull;

import javafx.fxml.FXMLLoader;

/**
 * FXMLローディングユーティリティ。
 */
public final class FxmlLoadingUtils {

    /**
     * コンストラクタ。
     *
     * インスタンス生成は禁止する。
     */
    private FxmlLoadingUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * FXMLをロードする。
     *
     * @param <T> ロード結果タイプ。
     * @param location ロケーション。
     * @param root ルートオブジェクト。
     * @return ロード結果。
     */
    public static <T> T loadControlledFxml(@NonNull Class<?> location, @NonNull Object root) {
        return loadControlledFxml(location.getResource(location.getSimpleName() + ".fxml"), root);
    }

    /**
     * FXMLをロードする。
     *
     * @param <T> ロード結果タイプ。
     * @param location ロケーション。
     * @param root ルートオブジェクト。
     * @return ロード結果。
     */
    public static <T> T loadControlledFxml(@NonNull URL location, @NonNull Object root) {
        FXMLLoader loader = new FXMLLoader(location);
        loader.setRoot(root);
        loader.setController(root);
        try {
            return loader.load();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

}
