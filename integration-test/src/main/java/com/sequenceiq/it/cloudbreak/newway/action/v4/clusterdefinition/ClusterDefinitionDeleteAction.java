package com.sequenceiq.it.cloudbreak.newway.action.v4.clusterdefinition;

import static com.sequenceiq.it.cloudbreak.newway.log.Log.log;
import static com.sequenceiq.it.cloudbreak.newway.log.Log.logJSON;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.entity.clusterdefinition.ClusterDefinitionTestDto;
import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;

public class ClusterDefinitionDeleteAction implements Action<ClusterDefinitionTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterDefinitionDeleteAction.class);

    @Override
    public ClusterDefinitionTestDto action(TestContext testContext, ClusterDefinitionTestDto entity, CloudbreakClient client) throws Exception {
        log(LOGGER, format(" Name: %s", entity.getRequest().getName()));
        logJSON(LOGGER, format(" Cluster definition delete request:%n"), entity.getRequest());
        entity.setResponse(
                client.getCloudbreakClient()
                        .clusterDefinitionV4Endpoint()
                        .delete(client.getWorkspaceId(), entity.getName()));
        logJSON(LOGGER, format(" Cluster definition deleted successfully:%n"), entity.getResponse());
        log(LOGGER, format(" ID: %s", entity.getResponse().getId()));

        return entity;
    }

}