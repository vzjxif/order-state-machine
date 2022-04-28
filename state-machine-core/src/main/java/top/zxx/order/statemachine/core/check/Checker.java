package top.zxx.order.statemachine.core.check;

import top.zxx.order.statemachine.core.StateContext;

public interface Checker<T, C> {

    T check(StateContext<C> stateContext);

    default int order() {
        return 0;
    }
}
