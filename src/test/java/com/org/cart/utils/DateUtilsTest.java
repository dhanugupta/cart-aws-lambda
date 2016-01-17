package com.org.cart.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.cart.model.DateObj;
import com.org.cart.utils.DateUtils;





public class DateUtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilsTest.class);

	private static final String strDate = "02-01-2015 00:00:00";
	private static final String strDate1 = "02-01-2015 06:66:12";
	private static final String endDate = "02-10-2015 00:00:00";
	private static final String strDateException = "02-01/2015 00:00:00";

	private DateUtils dateUtils;
	@Before
	public void setUp() throws Exception {

		dateUtils = new DateUtils();
	}

	@Test
	public void testCreateDate() {

		assertNull(DateUtils.createDate(null));
		assertNotNull(DateUtils.createDate(new Date()));
		assertNotNull(DateUtils.createDate(new Date(1879829718)));
	}

	@Test
	public void testParseDate(){
		String s = "1426705867000";
		LOGGER.info("{}",DateUtils.parseDate(s));
		assertNotNull(DateUtils.parseDate(s));
		assertNotNull(DateUtils.printDate(DateUtils.createDate(new Date(1879829718))));
	}
	@Test
	public void testToXmlDate(){
		assertNotNull(DateUtils.toXmlDate(DateUtils.createDate(new Date(1879829718))));
		assertNull(DateUtils.toXmlDate(null));
	}
	@Test
	public void testToDate(){
		assertNotNull(DateUtils.toDate(DateUtils.toXmlDate(DateUtils.createDate(new Date(1879829718)))));
		assertNull(DateUtils.toDate(null));
	}

	@Test
	public void testDateToCalendar(){
		assertNotNull(DateUtils.dateToCalendar(DateUtils.stringToDate(strDate)));
	}

	@Test
	public void testStringtoDate(){
		assertNotNull(DateUtils.stringToDate(strDate));
	}

	@Test
	public void testStringtoDateException(){
		assertNull(DateUtils.stringToDate(strDateException));
	}

	@Test
	public void testDaysBetween(){
		assertEquals(9,DateUtils.daysBetween(DateUtils.dateToCalendar(DateUtils.stringToDate(strDate)), DateUtils.dateToCalendar(DateUtils.stringToDate(endDate))));
		assertEquals(0,DateUtils.daysBetween(DateUtils.dateToCalendar(DateUtils.stringToDate(endDate)), DateUtils.dateToCalendar(DateUtils.stringToDate(strDate))));
	}

	@Test
	public void testSameDate(){
		assertTrue(DateUtils.isSameDate(strDate, strDate1));
		assertFalse(DateUtils.isSameDate(strDate, endDate));
		assertTrue(DateUtils.isSameDate(strDate, DateUtils.stringToDate(strDate)));
		assertFalse(DateUtils.isSameDate(strDate, DateUtils.stringToDate(endDate)));
	}

	@Test
	public void testNumberofDays(){
		LOGGER.info(" {}",DateUtils.addNumberDays(DateUtils.dateToCalendar(DateUtils.stringToDate(strDate)), 0));
	}

	@Test
	public void testgetStringDate(){

		LOGGER.info(" {}",DateUtils.getStringDate(DateUtils.stringToDate(strDate)));
	}

	@Test
	public void testdateDifference(){
		DateObj dtObj = DateUtils.dateDifference(DateUtils.getStringDate(DateUtils.stringToDate(strDate)), DateUtils.getStringDate(DateUtils.stringToDate(strDate1)));
		assertEquals("7",Long.toString(dtObj.getHours()));
	}
}
