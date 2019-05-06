package com.sequenceiq.freeipa.flow.stack.termination.action;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.sequenceiq.cloudbreak.cloud.event.resource.TerminateStackResult;
import com.sequenceiq.cloudbreak.common.event.Selectable;
import com.sequenceiq.freeipa.flow.stack.StackEvent;
import com.sequenceiq.freeipa.flow.stack.termination.StackTerminationContext;
import com.sequenceiq.freeipa.flow.stack.termination.StackTerminationEvent;

@Component("StackTerminationFinishedAction")
public class StackTerminationFinishedAction extends AbstractStackTerminationAction<TerminateStackResult> {

    @Inject
    private StackTerminationService stackTerminationService;

    public StackTerminationFinishedAction() {
        super(TerminateStackResult.class);
    }

    @Override
    protected void doExecute(StackTerminationContext context, TerminateStackResult payload, Map<Object, Object> variables) {
        stackTerminationService.finishStackTermination(context, payload);
        sendEvent(context);
    }

    @Override
    protected Selectable createRequest(StackTerminationContext context) {
        return new StackEvent(StackTerminationEvent.TERMINATION_FINALIZED_EVENT.event(), context.getStack().getId());
    }
}
