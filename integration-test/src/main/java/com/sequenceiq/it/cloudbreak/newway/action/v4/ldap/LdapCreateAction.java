package com.sequenceiq.it.cloudbreak.newway.action.v4.ldap;

import static com.sequenceiq.it.cloudbreak.newway.log.Log.log;
import static com.sequenceiq.it.cloudbreak.newway.log.Log.logJSON;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.ldap.LdapTestDto;

public class LdapCreateAction implements Action<LdapTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapCreateAction.class);

    @Override
    public LdapTestDto action(TestContext testContext, LdapTestDto entity, CloudbreakClient client) throws Exception {
        log(LOGGER, format(" Name: %s", entity.getRequest().getName()));
        logJSON(LOGGER, " Ldap post request:\n", entity.getRequest());
        entity.setResponse(
                client.getCloudbreakClient()
                        .ldapConfigV4Endpoint()
                        .post(client.getWorkspaceId(), entity.getRequest()));
        logJSON(LOGGER, " Ldap was created successfully:\n", entity.getResponse());
        log(LOGGER, format(" ID: %s", entity.getResponse().getId()));
        return entity;
    }

}