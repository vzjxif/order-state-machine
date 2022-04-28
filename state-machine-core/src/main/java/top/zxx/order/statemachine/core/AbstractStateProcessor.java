package top.zxx.order.statemachine.core;

import top.zxx.order.statemachine.core.plugin.PluginExecutor;

public abstract class AbstractStateProcessor<T, C> implements StateActionStep<T, C>, StateProcessor<T, C> {

    private PluginExecutor<T, C> pluginExecutor;

    @Override
    public T action(StateContext<C> stateContext) throws Exception {
        this.prepare(stateContext);
        T checkResult = this.check(stateContext);
        String nextState = this.getNextState(stateContext);
        T actionResult = this.action(nextState, stateContext);
        T pluginResult = this.pluginExecutor.parallelExecutor(stateContext);
        T saveResult = this.save(nextState, stateContext);
        this.after(stateContext);
        return saveResult;
    }

    public PluginExecutor<T, C> getPluginExecutor() {
        return pluginExecutor;
    }

    public void setPluginExecutor(PluginExecutor<T, C> pluginExecutor) {
        this.pluginExecutor = pluginExecutor;
    }
}
