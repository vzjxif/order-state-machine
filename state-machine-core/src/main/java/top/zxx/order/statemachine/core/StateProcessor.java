package top.zxx.order.statemachine.core;

public interface StateProcessor<T,C> {

    T action(StateContext<C> stateContext) throws Exception;
}
