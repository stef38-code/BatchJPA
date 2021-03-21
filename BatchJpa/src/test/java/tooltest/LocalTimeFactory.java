/*
 * Auteur	:	betton
 * Date		:	2 oct. 2020
 */
package utils;

import org.meanbean.lang.Factory;

import java.time.LocalTime;

/**
 * A factory for creating LocalTime objects.
 */
public class LocalTimeFactory implements Factory<LocalTime> {
	
	/**
	 * Creates the.
	 *
	 * @return the local time
	 */
	@Override
	public LocalTime create() {
		return LocalTime.now();
	}
}