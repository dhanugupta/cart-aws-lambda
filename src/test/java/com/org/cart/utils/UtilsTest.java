package com.org.cart.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.org.cart.utils.Utils;
public class UtilsTest {

	private Utils utils;

	private static final String url ="https://startcheckout.aol.com/";
	private static final String urlWith ="https://startcheckout.aol.com/?ncid=1234";
	private static final String urlWithMboxPc ="https://startcheckout.aol.com/?ncid=1234&mboxPC=3445";
	private static final String urlWithMboxSession ="https://startcheckout.aol.com/?ncid=1234&mboxSession=3445";

	@Before
	public void setUp() throws Exception {
		utils = new Utils();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		utils = new Utils();
	}


}
