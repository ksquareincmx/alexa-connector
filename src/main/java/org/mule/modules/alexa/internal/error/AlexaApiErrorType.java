/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.error;

import static java.util.Optional.ofNullable;
import static org.mule.runtime.extension.api.error.MuleErrors.*;

import java.util.Optional;

import org.mule.runtime.extension.api.error.ErrorTypeDefinition;


public enum AlexaApiErrorType implements ErrorTypeDefinition<AlexaApiErrorType> {

	
	EXECUTION,
	JSON_PARSER_EXCEPTION(EXECUTION),
	OPERATION_NOT_APPLIED(EXECUTION),
	INVALID_QUERY(EXECUTION),
	NO_HOST_AVAILABLE(EXECUTION),
	OPERATION_TIMED_OUT(EXECUTION),
	OPERATION_FAILED(EXECUTION),
	SYNTAX_ERROR(EXECUTION),
	SERVERE_RROR(EXECUTION),
	QUERY_ERROR(EXECUTION),
	ACCOUNT_NOT_FOUND(EXECUTION),
	PROFILE_ID_NOT_FOUND(EXECUTION),
	NO_PROFILES_FOUND(EXECUTION),
	READ_FAILURE(EXECUTION),
	READ_TIMEOUT(EXECUTION),
    TRIGGER_EXPIRED(EXECUTION),
    NOT_AUTHED(CONNECTIVITY),
    CONNECTION_EXCEPTION(CONNECTIVITY),
    INCORRECT_CREDENTIALS(CONNECTIVITY),
    INVALID_AUTH(CONNECTIVITY),
    NO_PERMISSION(CONNECTIVITY),
    ACCOUNT_INACTIVE(CONNECTIVITY),
	VALIDATIONS(VALIDATION);

	AlexaApiErrorType(){
		
	}
	 
	
    private ErrorTypeDefinition<?> parent;

    AlexaApiErrorType(final ErrorTypeDefinition<?> parent) {
        this.parent = parent;
    }

   
    @Override
    public Optional<ErrorTypeDefinition<? extends Enum<?>>> getParent() {
        return ofNullable(parent);
    }
	
	
	 
	
}
