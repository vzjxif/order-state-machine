package top.zxx.order.statemachine.sample;

import org.springframework.stereotype.Component;
import top.zxx.order.statemachine.core.*;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DefaultOrderFsmEngine implements OrderFsmEngine {

    @Resource
    private DefaultStateProcessRegistry stateProcessRegistry;

    @Override
    public <T> T sendEvent(StateEvent stateEvent) {
        return null;
    }

    private <T> StateProcessor<T, ?> getStateProcessor(StateContext<?> context) {
        StateEvent stateEvent = context.getStateEvent();
        FsmOrder order = context.getOrder();
        List<AbstractStateProcessor> processors = stateProcessRegistry.acquireStateProcessor(order.getOrderState(),
                stateEvent.getEventType(), order.getBizCode(), order.getSceneId());

        //todo 异常处理
        return processors.get(0);
    }
}
