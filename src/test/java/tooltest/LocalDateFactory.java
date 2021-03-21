/*
 * Auteur	:	betton
 * Date		:	2 oct. 2020
 */
package utils;

import org.meanbean.lang.Factory;

import java.time.LocalDate;

/**
 * A factory for creating LocalDate objects.
 */
public class LocalDateFactory implements Factory<LocalDate> {

	/**
	 * Creates the.
	 *
	 * @return the local date
	 */
	@Override
	public LocalDate create() {
		return LocalDate.now();
	}

}
