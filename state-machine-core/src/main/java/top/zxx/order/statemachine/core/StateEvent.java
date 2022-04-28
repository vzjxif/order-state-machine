package top.zxx.order.statemachine.core;

public interface StateEvent {

    String getEventType();

    String getOrderId();

    default String orderState() {
        return null;
    }
}
