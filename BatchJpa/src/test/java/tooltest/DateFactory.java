/*
 * Auteur	:	betton
 * Date		:	2 oct. 2020
 */
package utils;

import org.meanbean.lang.Factory;

import java.util.Calendar;
import java.util.Date;

/**
 * A factory for creating Date objects.
 */
public class DateFactory implements Factory<Date> {

	/**
	 * Creates the.
	 *
	 * @return the date
	 */
	@Override
	public Date create() {
		return new Date(Calendar.getInstance().getTimeInMillis());
	}

}

