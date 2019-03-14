package com.sequenceiq.it.cloudbreak.newway.action.v4.imagecatalog;

import static com.sequenceiq.it.cloudbreak.newway.log.Log.log;
import static com.sequenceiq.it.cloudbreak.newway.log.Log.logJSON;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.imagecatalog.ImageCatalogTestDto;

public class ImageCatalogCreateWithoutNameLoggingAction implements Action<ImageCatalogTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageCatalogCreateWithoutNameLoggingAction.class);

    @Override
    public ImageCatalogTestDto action(TestContext testContext, ImageCatalogTestDto entity, CloudbreakClient client) throws Exception {
        logJSON(LOGGER, format(" Image catalog post request:%n"), entity.getRequest());
        entity.setResponse(
                client.getCloudbreakClient()
                        .imageCatalogV4Endpoint()
                        .create(client.getWorkspaceId(), entity.getRequest()));
        logJSON(LOGGER, format(" Image catalog created  successfully:%n"), entity.getResponse());
        log(LOGGER, format(" ID: %s", entity.getResponse().getId()));

        return entity;
    }

}