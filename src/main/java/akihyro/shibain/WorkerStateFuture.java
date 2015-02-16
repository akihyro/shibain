package akihyro.shibain;

import java.util.concurrent.CompletableFuture;

import lombok.NonNull;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;

/**
 * ワーカーステートフューチャー。
 */
public class WorkerStateFuture extends CompletableFuture<Void> {

    /**
     * コンストラクタ。
     *
     * @param observable 監視可能なステート。
     */
    public WorkerStateFuture(@NonNull ObservableValue<State> observable) {
        observable.addListener(new StateChangeListener());
    }

    /**
     * ステート変更リスナー。
     */
    private class StateChangeListener implements ChangeListener<State> {

        /** {@inheritDoc} */
        @Override
        public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
            switch (newValue) {
                case SUCCEEDED:
                    complete(null);
                    observable.removeListener(this);
                    break;
                case FAILED:
                    completeExceptionally(new Exception());
                    observable.removeListener(this);
                    break;
                case CANCELLED:
                    cancel(true);
                    observable.removeListener(this);
                    break;
            }
        }

    }

}
