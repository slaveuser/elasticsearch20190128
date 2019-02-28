/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.core.security.action.rolemapping;

import java.io.IOException;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.xpack.core.security.authc.support.mapper.ExpressionRoleMapping;

import static org.elasticsearch.action.ValidateActions.addValidationError;

/**
 * Request to retrieve role-mappings from X-Pack security
 *
 * see org.elasticsearch.xpack.security.authc.support.mapper.NativeRoleMappingStore
 */
public class GetRoleMappingsRequest extends ActionRequest {

    private String[] names = Strings.EMPTY_ARRAY;

    public GetRoleMappingsRequest() {
    }

    @Override
    public ActionRequestValidationException validate() {
        ActionRequestValidationException validationException = null;
        if (names == null) {
            validationException = addValidationError("role-mapping names are missing",
                    validationException);
        }
        return validationException;
    }

    /**
     * Specify (by name) which role-mappings to delete.
     * @see ExpressionRoleMapping#getName()
     */
    public void setNames(String... names) {
        this.names = names;
    }

    /**
     * @see #setNames(String...)
     */
    public String[] getNames() {
        return names;
    }

    @Override
    public void readFrom(StreamInput in) throws IOException {
        super.readFrom(in);
        names = in.readStringArray();
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        super.writeTo(out);
        out.writeStringArray(names);
    }
}
