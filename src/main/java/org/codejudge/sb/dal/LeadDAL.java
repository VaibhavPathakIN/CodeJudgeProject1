package org.codejudge.sb.dal;

import org.codejudge.sb.model.Lead;

public interface LeadDAL {

	Lead getLeadById(Integer id) throws Exception;

	void saveOrUpdateLead(Lead lead) throws Exception;

	long countLeads() throws Exception;

}
