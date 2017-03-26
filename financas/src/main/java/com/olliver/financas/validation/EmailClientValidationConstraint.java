/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.validation;

import java.util.HashMap;
import java.util.Map;
import javax.validation.metadata.ConstraintDescriptor;

/**
 *
 * @author olliver
 */
public class EmailClientValidationConstraint {

    public static final String MESSAGE_METADATA = "data-p-email-msg";

    public Map<String, Object> getMetada(ConstraintDescriptor constraintDescriptor) {
        Map<String, Object> metadata = new HashMap<String, Object>();
        Map attrs = constraintDescriptor.getAttributes();
        Object message = attrs.get("message");
        if (message != null) {
            metadata.put(MESSAGE_METADATA, message);
        }
        return metadata;
    }

    public String getValidatorId() {
        return Email.class.getSimpleName();
    }
}
