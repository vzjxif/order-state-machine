package top.zxx.order.statemachine.core;

import lombok.Data;

@Data
public class FsmOrder {

    private String orderState;

    private String bizCode;

    private String sceneId;
}
