package top.zxx.order.statemachine.core.plugin;

import top.zxx.order.statemachine.core.StateContext;

public interface PluginExecutor<T, C> {
    T parallelExecutor(StateContext<C> stateContext);
}
