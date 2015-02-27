package akihyro.shibain.main;

import static akihyro.shibain.util.FxmlLoadingUtils.loadFxml;
import javafx.scene.layout.VBox;

/**
 * メインペイン。
 */
public class MainPane extends VBox {

    /**
     * コンストラクタ。
     */
    public MainPane() {
        loadFxml(MainPane.class, this);
    }

}
