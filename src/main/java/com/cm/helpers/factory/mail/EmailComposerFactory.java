package com.cm.helpers.factory.mail;

/**
 * Creates EmailComposed with the required type
 */
public interface EmailComposerFactory {

    /**
     * Returns EmailComposer for the current {@link java.lang.String userType}
     * @param userType
     * @return
     */
    EmailComposer getForType(String userType);

}
