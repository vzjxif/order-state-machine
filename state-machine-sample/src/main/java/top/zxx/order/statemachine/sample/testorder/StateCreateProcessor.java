package top.zxx.order.statemachine.sample.testorder;

import org.springframework.stereotype.Component;
import top.zxx.order.statemachine.core.AbstractStateProcessor;
import top.zxx.order.statemachine.core.StateContext;
import top.zxx.order.statemachine.core.annotations.OrderProcessor;

@Component
@OrderProcessor(state = "INIT", bizCode = {"test"}, sceneId = {"h5"}, event = "Create")
public class StateCreateProcessor extends AbstractStateProcessor<CreateResult, CreateContext> {

    @Override
    public CreateResult check(StateContext<CreateContext> context) {
        return null;
    }

    @Override
    public String getNextState(StateContext<CreateContext> context) {
        // 支付成功

        return "PAY_SUCCESS";
    }

    @Override
    public CreateResult action(String nextState, StateContext<CreateContext> context) {
        return null;
    }

    @Override
    public CreateResult save(String nextState, StateContext<CreateContext> context) {
        return null;
    }

    @Override
    public void after(StateContext<CreateContext> context) {

    }
}
