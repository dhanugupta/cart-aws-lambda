package com.org.cart.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.org.cart.utils.JsonUtils;

public class JsonUtilsTest {


	private final Object obj = "{\"test\":\"tt\"}";

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(JsonUtils.toJsonString(obj));
	}

	@Test
	public void testNotNull() {
		assertNotNull(JsonUtils.toJsonStringNonNull(obj));
	}

	@Test
	public void testNull() {
		assertNull(JsonUtils.toJsonString(null));
	}

	@Test
	public void testNullMapObj() {
		assertNull(JsonUtils.getMapObject(null));
	}

	@Test
	public void testMapObj() {
		Map<String,String> mapObj = new HashMap<String,String>();
		mapObj.put("ff", "ff");
		assertNotNull(JsonUtils.getMapObject(mapObj ));
	}

	@Test
	public void testMapObjFromString() {
		String test = "{\"complete\":\"test\"}";
		assertNotNull(JsonUtils.getMapObjectFromString(test ));
	}
	@Test
	public void testMapObjFromStringNull() {
		String test = "test";
		assertNull(JsonUtils.getMapObjectFromString(test ));
	}

}
