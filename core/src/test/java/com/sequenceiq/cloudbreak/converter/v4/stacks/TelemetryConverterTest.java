package com.sequenceiq.cloudbreak.converter.v4.stacks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.sequenceiq.common.api.cloudstorage.S3CloudStorageV1Parameters;
import com.sequenceiq.common.api.telemetry.model.Logging;
import com.sequenceiq.common.api.telemetry.model.Telemetry;
import com.sequenceiq.common.api.telemetry.response.LoggingResponse;
import com.sequenceiq.common.api.telemetry.response.TelemetryResponse;

public class TelemetryConverterTest {

    private static final String INSTANCE_PROFILE_VALUE = "myInstanceProfile";

    private final TelemetryConverter underTest = new TelemetryConverter();

    @Test
    public void testConvertToResponse() {
        // GIVEN
        Logging logging = new Logging();
        S3CloudStorageV1Parameters s3Params = new S3CloudStorageV1Parameters();
        s3Params.setInstanceProfile(INSTANCE_PROFILE_VALUE);
        logging.setS3(s3Params);
        Telemetry telemetry = new Telemetry(logging, null);
        // WHEN
        TelemetryResponse result = underTest.convert(telemetry);
        // THEN
        assertEquals(INSTANCE_PROFILE_VALUE, result.getLogging().getS3().getInstanceProfile());
        assertNull(result.getWorkloadAnalytics());
    }

    @Test
    public void testConvertFromResponse() {
        // GIVEN
        TelemetryResponse response = new TelemetryResponse();
        LoggingResponse loggingResponse = new LoggingResponse();
        S3CloudStorageV1Parameters s3Params = new S3CloudStorageV1Parameters();
        s3Params.setInstanceProfile(INSTANCE_PROFILE_VALUE);
        loggingResponse.setS3(s3Params);
        response.setLogging(loggingResponse);
        // WHEN
        Telemetry result = underTest.convert(response);
        // THEN
        assertEquals(INSTANCE_PROFILE_VALUE, result.getLogging().getS3().getInstanceProfile());
        assertNull(result.getWorkloadAnalytics());
    }
}