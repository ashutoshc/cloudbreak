package com.sequenceiq.periscope.controller.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.cloudbreak.common.exception.ExceptionResponse;

abstract class BaseExceptionMapper<E extends Throwable> implements ExceptionMapper<E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseExceptionMapper.class);

    @Override
    public Response toResponse(E exception) {
        LOGGER.info(exception.getMessage(), exception);
        return Response.status(getResponseStatus()).entity(getEntity(exception)).build();
    }

    protected Object getEntity(E exception) {
        return new ExceptionResponse(exception.getMessage());
    }

    abstract Status getResponseStatus();
}
