package com.sequenceiq.it.cloudbreak.newway.action.v4.kerberos;

import static com.sequenceiq.it.cloudbreak.newway.log.Log.log;
import static com.sequenceiq.it.cloudbreak.newway.log.Log.logJSON;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.kerberos.KerberosTestDto;

public class KerberosGetAction implements Action<KerberosTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(KerberosGetAction.class);

    public KerberosTestDto action(TestContext testContext, KerberosTestDto entity, CloudbreakClient client) throws Exception {
        log(LOGGER, format(" Name: %s", entity.getRequest().getName()));
        logJSON(LOGGER, format(" Kerberos get request:%n"), entity.getRequest());
        entity.setResponse(
                client.getCloudbreakClient()
                        .kerberosConfigV4Endpoint()
                        .get(client.getWorkspaceId(), entity.getName()));
        logJSON(LOGGER, format(" Kerberos get successfully:%n"), entity.getResponse());
        log(LOGGER, format(" ID: %s", entity.getResponse().getId()));
        return entity;
    }
}