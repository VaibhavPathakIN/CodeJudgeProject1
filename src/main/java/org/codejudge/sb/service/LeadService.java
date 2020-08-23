package org.codejudge.sb.service;

import java.util.Map;

import org.codejudge.sb.dal.LeadDAL;
import org.codejudge.sb.model.Lead;
import org.codejudge.sb.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LeadService {

	private final LeadDAL leadDAL;
	private final ObjectMapper mapper;
	private Lead lead;

	public LeadService(@Autowired LeadDAL leadDAL) {
		this.leadDAL = leadDAL;
		this.mapper = new ObjectMapper();
	}

	/**
	 * Method used to fetch the lead info against the input leadId
	 * 
	 * @param leadId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getLeadInfo(Integer leadId) throws Exception {
		return mapper.convertValue(getLead(leadId), Map.class);
	}

	/**
	 * Method is used to create a new lead in the system
	 * 
	 * @param leadDetails
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> createLead(Map<String, Object> leadDetails) throws Exception {
		lead = mapper.convertValue(leadDetails, Lead.class);
		lead.setStatus(Status.Created);
		lead.set_id(leadDAL.countLeads() + 1);
		saveLead(lead);

		leadDetails.put("id", lead.get_id());
		leadDetails.put("status", lead.getStatus());
		return leadDetails;
	}

	/**
	 * Method to update an existing given lead into the system
	 * 
	 * @param leadId
	 * @param leadDetails
	 * @throws Exception
	 */
	public void updateLead(Integer leadId, Map<String, Object> leadDetails) throws Exception {

		if (leadDetails == null)
			throw new Exception("Request object is emtpy");

		Map<String, Object> existing = getLeadInfo(leadId);
		leadDetails.forEach((k, v) -> {
			existing.put(k, v);
		});
		lead = mapper.convertValue(existing, Lead.class);
		saveLead(lead);
	}

	/**
	 * Method used to mark the lead contacted
	 * 
	 * @param leadId
	 * @param leadDetails
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> markLead(Integer leadId, Map<String, Object> leadDetails) throws Exception {

		String communication = "";

		if (leadDetails == null
				|| (communication = (String) leadDetails.getOrDefault("communication", "")).equals("")) {
			throw new Exception("communication is not present in request");
		}
		lead = getLead(leadId);
		lead.setStatus(Status.Contacted);
		lead.setCommunication(communication);
		saveLead(lead);

		leadDetails.put("status", Status.Contacted);
		return leadDetails;
	}

	/**
	 * Method used to delete the lead from the system
	 * 
	 * @param leadId
	 * @throws Exception
	 */
	public void deleteLead(Integer leadId) throws Exception {
		lead = getLead(leadId);
		lead.setIsDeleted(true);
		saveLead(lead);
	}

	/**
	 * Method used to actually interact with DAL and gets the data from DB
	 * 
	 * @param leadId
	 * @return
	 * @throws Exception
	 */
	private Lead getLead(Integer leadId) throws Exception {
		return leadDAL.getLeadById(leadId);
	}

	/**
	 * Method used to actually interact with DAL and saves the data into DB
	 * 
	 * @param lead
	 * @throws Exception
	 */
	private void saveLead(Lead lead) throws Exception {
		leadDAL.saveOrUpdateLead(lead);
	}
}
