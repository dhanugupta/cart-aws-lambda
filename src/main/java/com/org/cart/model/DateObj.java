package com.org.cart.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DateObj implements Serializable {

	private static final long serialVersionUID = 1L;

	private long days;

	private long hours;

	private long minutes;

	private long seconds;

	/**
	 * @return the days
	 */
	public long getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
	public void setDays(long days) {
		this.days = days;
	}

	/**
	 * @return the hours
	 */
	public long getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(long hours) {
		this.hours = hours;
	}

	/**
	 * @return the minutes
	 */
	public long getMinutes() {
		return minutes;
	}

	/**
	 * @param minutes the minutes to set
	 */
	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	/**
	 * @return the seconds
	 */
	public long getSeconds() {
		return seconds;
	}

	/**
	 * @param seconds the seconds to set
	 */
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}



}
