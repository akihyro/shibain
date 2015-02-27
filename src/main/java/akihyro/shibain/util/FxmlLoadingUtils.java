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
     * @return ローダ。
     */
    public static FXMLLoader loadFxml(@NonNull Class<?> location, @NonNull Object root) {
        return loadFxml(location.getResource(location.getSimpleName() + ".fxml"), root);
    }

    /**
     * FXMLをロードする。
     *
     * @param <T> ロード結果タイプ。
     * @param location ロケーション。
     * @param root ルートオブジェクト。
     * @return ローダ。
     */
    public static FXMLLoader loadFxml(@NonNull URL location, @NonNull Object root) {
        FXMLLoader loader = new FXMLLoader(location);
        loader.setRoot(root);
        try {
            loader.load();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
        return loader;
    }

}
