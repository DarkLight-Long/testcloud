package org.testcloud.modules.provider.DemoProvider.config;

import lombok.Data;

/**
 * ProviderException
 */
@Data
public class ProviderException extends RuntimeException {

    public ProviderException() {
        super();
    }

    public ProviderException(String message) {
        super(message);
    }
}
