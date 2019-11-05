/**
 * (c) 2003-2019 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.exceptions;

import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.runtime.extension.api.error.ErrorTypeDefinition;



import java.util.Set;

public class ExecutionErrorTypeProvider extends BaseErrorTypeProvider {

	@Override
	public void addErrors(@SuppressWarnings("rawtypes") Set<ErrorTypeDefinition> errors) {
		errors.add(AlexaApiErrorType.EXECUTION);
	}
}