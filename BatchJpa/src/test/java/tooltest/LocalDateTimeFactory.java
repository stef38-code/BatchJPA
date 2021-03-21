/*
 * Auteur	:	betton
 * Date		:	2 oct. 2020
 */
package utils;

import org.meanbean.lang.Factory;

import java.time.LocalDateTime;

/**
 * A factory for creating LocalDateTime objects.
 */
public class LocalDateTimeFactory implements Factory<LocalDateTime>{
    
    /**
     * Creates the.
     *
     * @return the local date time
     */
    @Override
    public LocalDateTime create()
    {
        return LocalDateTime.now();
    }
}

