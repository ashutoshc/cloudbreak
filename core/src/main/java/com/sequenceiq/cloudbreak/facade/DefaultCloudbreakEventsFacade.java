package com.sequenceiq.cloudbreak.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sequenceiq.cloudbreak.api.model.CloudbreakEventsJson;
import com.sequenceiq.cloudbreak.domain.organization.Organization;
import com.sequenceiq.cloudbreak.service.events.CloudbreakEventService;
import com.sequenceiq.cloudbreak.structuredevent.event.StructuredNotificationEvent;
import com.sequenceiq.cloudbreak.util.ConverterUtil;

@Service
public class DefaultCloudbreakEventsFacade implements CloudbreakEventsFacade {

    @Inject
    private CloudbreakEventService cloudbreakEventService;

    @Inject
    private ConverterUtil converterUtil;

    @Override
    public List<CloudbreakEventsJson> retrieveEventsForOrganiztion(Organization organization, Long since) {
        List<StructuredNotificationEvent> cloudbreakEvents = cloudbreakEventService.cloudbreakEvents(organization, since);
        return converterUtil.toList(cloudbreakEvents, CloudbreakEventsJson.class);
    }

    @Override
    public List<CloudbreakEventsJson> retrieveEventsByStack(Long stackId) {
        List<StructuredNotificationEvent> cloudbreakEvents = cloudbreakEventService.cloudbreakEventsForStack(stackId);
        return converterUtil.toList(cloudbreakEvents, CloudbreakEventsJson.class);
    }
}
