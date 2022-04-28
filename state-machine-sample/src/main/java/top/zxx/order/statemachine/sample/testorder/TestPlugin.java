package top.zxx.order.statemachine.sample.testorder;

import top.zxx.order.statemachine.core.StateContext;
import top.zxx.order.statemachine.core.annotations.ProcessorPlugin;
import top.zxx.order.statemachine.core.plugin.PluginHandler;
import top.zxx.order.statemachine.sample.testorder.CreateContext;

@ProcessorPlugin(state = "INIT", event = "CREATE", bizCode = "test")
public class TestPlugin implements PluginHandler<String, CreateContext> {
    @Override
    public String action(StateContext<CreateContext> stateContext) throws Exception {
        return null;
    }
}
