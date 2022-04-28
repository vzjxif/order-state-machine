package top.zxx.order.statemachine.core;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StateContext<C> {

    private StateEvent stateEvent;

    private FsmOrder order;

    private C context;

}
