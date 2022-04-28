package top.zxx.order.statemachine.core;

public interface StateActionStep<T, C> {

    /**
     * 数据准备
     * @param context
     */
    default void prepare(StateContext<C> context) {
    }

    /**
     * 前置检查
     * @param context
     * @return
     */
    T check(StateContext<C> context);

    /**
     * 获取改变状态
     * @param context
     * @return
     */
    String getNextState(StateContext<C> context);

    /**
     * 执行
     * @param nextState
     * @param context
     * @return
     */
    T action(String nextState, StateContext<C> context);

    /**
     * 落库
     * @param nextState
     * @param context
     * @return
     */
    T save(String nextState, StateContext<C> context);

    /**
     * 后续处理
     * @param context
     */
    void after(StateContext<C> context);
}
