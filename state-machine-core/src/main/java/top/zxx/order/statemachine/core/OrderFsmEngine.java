package top.zxx.order.statemachine.core;

public interface OrderFsmEngine {

    <T> T sendEvent(StateEvent stateEvent);
}
