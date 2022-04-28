package top.zxx.order.statemachine.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.zxx.order.statemachine.core.AbstractStateProcessor;
import top.zxx.order.statemachine.core.StateProcessor;
import top.zxx.order.statemachine.core.annotations.OrderProcessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
public class DefaultStateProcessRegistry implements BeanPostProcessor {

    private static Map<String, Map<String, Map<String, List<AbstractStateProcessor>>>> stateProcessorMap = new ConcurrentHashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof AbstractStateProcessor && bean.getClass().isAnnotationPresent(OrderProcessor.class)) {
            OrderProcessor processor = bean.getClass().getAnnotation(OrderProcessor.class);
            String[] states = processor.state();
            String event = processor.event();
            String[] bizCodes = processor.bizCode();
            String[] sceneIds = processor.sceneId();
            initProcessMap(states, event, bizCodes, sceneIds, stateProcessorMap, (AbstractStateProcessor) bean);
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    private <E extends StateProcessor> void initProcessMap(String[] states, String event, String[] bizCodes, String[] sceneIds,
                                                           Map<String, Map<String, Map<String, List<E>>>> map, E processor) {
        for (String bizCode : bizCodes) {
            for (String sceneId : sceneIds) {
                Arrays.asList(states).parallelStream().forEach(state -> {
                    registerStateHandler(state, event, bizCode, sceneId, map, processor);
                });
            }
        }
    }

    private <E extends StateProcessor> void registerStateHandler(String state, String event, String bizCode, String sceneId,
                                                                 Map<String, Map<String, Map<String, List<E>>>> map, E processor) {

        log.debug("register state processor, state = {}, event = {}, bizCode = {}, sceneId {}, processor = {}", state, event, bizCode, sceneId, processor.getClass().getName());
        if (!map.containsKey(state)) {
            map.put(state, new ConcurrentHashMap<>());
        }
        Map<String, Map<String, List<E>>> stateTransformEventMap = map.get(state);

        if (!stateTransformEventMap.containsKey(event)) {
            stateTransformEventMap.put(event, new ConcurrentHashMap<>());
        }
        Map<String, List<E>> processorMap = stateTransformEventMap.get(event);

        String bizId = bizCode + "@" + sceneId;
        if (!processorMap.containsKey(bizId)) {
            processorMap.put(bizId, new CopyOnWriteArrayList<>());
        }
        processorMap.get(bizId).add(processor);
    }

    public List<AbstractStateProcessor> acquireStateProcessor(String state, String event, String bizCode, String sceneId) {
        List<AbstractStateProcessor> emptyResult = new ArrayList<>();
        if (stateProcessorMap.containsKey(state)) {
            return emptyResult;
        }

        Map<String, Map<String, List<AbstractStateProcessor>>> stateTransformEventMap = stateProcessorMap.get(state);
        if (stateTransformEventMap.containsKey(event)) {
            return emptyResult;
        }

        Map<String, List<AbstractStateProcessor>> processorMap = stateTransformEventMap.get(event);

        String bizId = bizCode + "@" + sceneId;
        if (!processorMap.containsKey(bizId)) {
            return emptyResult;
        }
        return processorMap.get(bizId);
    }
}
