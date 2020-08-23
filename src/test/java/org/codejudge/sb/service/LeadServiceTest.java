package org.codejudge.sb.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.codejudge.sb.dal.LeadDAL;
import org.codejudge.sb.model.Lead;
import org.codejudge.sb.model.LocationType;
import org.codejudge.sb.model.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LeadServiceTest {

	private LeadDAL leadDAL;
	private Map<String, Object> expected;
	private Lead lead;
	private LeadService leadService;

	@Before
	public void initialize() throws Exception {
		initData();
		leadDAL = Mockito.mock(LeadDAL.class);
		Mockito.doNothing().when(leadDAL).saveOrUpdateLead(Mockito.any());
		Mockito.when(leadDAL.getLeadById(Mockito.anyInt())).thenReturn(lead);
		leadService = new LeadService(leadDAL);
	}

	/**
	 * Method to initialize mock data
	 */
	public void initData() {

		lead = new Lead();
		lead.set_id(1);
		lead.setFirstName("ABC");
		lead.setLastName("XYZ");
		lead.setMobile("9090909090");
		lead.setEmail("abc@xyz.com");
		lead.setLocationType(LocationType.City);
		lead.setLocationString("demo");
		lead.setStatus(Status.Created);

		expected = new HashMap<String, Object>();
		expected.put("id", 1l);
		expected.put("first_name", "ABC");
		expected.put("last_name", "XYZ");
		expected.put("mobile", "9090909090");
		expected.put("email", "abc@xyz.com");
		expected.put("location_type", LocationType.City);
		expected.put("location_string", "demo");
		expected.put("status", Status.Created);
		expected.put("communication", null);
	}

	@After
	public void destroy() {
		leadDAL = null;
		lead = null;
		expected = null;
	}

	/**
	 * Test to fetch lead
	 * 
	 * @throws Exception
	 */
	@Test
	public void getLeadInfoTest() throws Exception {
		Map<String, Object> result = leadService.getLeadInfo(1);
		expected.forEach((k, v) -> {
			if (k.equals("location_type") || k.equals("status")) {
				assertEquals(v.toString(), result.get(k).toString());
				return;
			}
			assertEquals(v, result.get(k));
		});
	}

	/**
	 * Test to create a lead
	 * 
	 * @throws Exception
	 */
	@Test
	public void createLeadTest() throws Exception {
		Map<String, Object> input = expected;
		input.remove("id");
		input.remove("communication");
		Map<String, Object> result = leadService.createLead(input);
		assertEquals(expected, result);
	}

	/**
	 * Test to update a lead
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateLeadTest() throws Exception {
		Map<String, Object> leadDetails = expected;
		leadDetails.put("location_type", LocationType.Country);
		leadDetails.put("email", "xyz@abc.com");
		leadService.updateLead(1, leadDetails);
	}

	/**
	 * Test to mark a lead contacted
	 * 
	 * @throws Exception
	 */
	@Test
	public void markLeadTest() throws Exception {
		Map<String, Object> input = new HashMap<>();
		input.put("communication", "Lead contacted");
		expected.put("communication", "Lead contacted");
		expected.put("status", Status.Contacted);

		Map<String, Object> result = leadService.markLead(1, input);
		assertEquals(expected.get("status"), result.get("status"));
		assertEquals(expected.get("communication"), result.get("communication"));
	}

	/**
	 * Test to delete a lead
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteLeadTest() throws Exception {
		leadService.deleteLead(1);
	}

}
