/*
 * Auteur	:	betton
 * Date		:	2 oct. 2020
 */
package utils;

import org.meanbean.lang.Factory;

import java.sql.Date;
import java.util.Calendar;

/**
 * A factory for creating SqlDate objects.
 */
public class SqlDateFactory implements Factory<Date> {

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