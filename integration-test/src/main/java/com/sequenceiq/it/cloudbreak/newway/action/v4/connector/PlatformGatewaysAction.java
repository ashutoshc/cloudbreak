package com.sequenceiq.it.cloudbreak.newway.action.v4.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.connector.PlatformGatewaysTestDto;

public class PlatformGatewaysAction implements Action<PlatformGatewaysTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformGatewaysAction.class);

    @Override
    public PlatformGatewaysTestDto action(TestContext testContext, PlatformGatewaysTestDto entity, CloudbreakClient cloudbreakClient) throws Exception {
        String logInitMessage = "Obtaining gateways by credential";
        LOGGER.info("{}", logInitMessage);
        entity.setResponse(
                cloudbreakClient.getCloudbreakClient().connectorV4Endpoint().getGatewaysCredentialId(
                        cloudbreakClient.getWorkspaceId(),
                        entity.getCredentialName(),
                        entity.getRegion(),
                        entity.getPlatformVariant(),
                        entity.getAvailabilityZone())
        );
        LOGGER.info("{} was successful", logInitMessage);
        return entity;
    }
}