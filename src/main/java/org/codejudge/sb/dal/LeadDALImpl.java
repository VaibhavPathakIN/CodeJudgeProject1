package org.codejudge.sb.dal;

import org.codejudge.sb.model.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class LeadDALImpl implements LeadDAL {

	private final MongoTemplate mongoTemplate;
	private Query query;

	public LeadDALImpl(@Autowired MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	/**
	 * Method used to find the lead by provided id
	 */
	@Override
	public Lead getLeadById(Integer id) throws Exception {
		query = new Query();
		query.addCriteria(Criteria.where("_id").is(id).andOperator(Criteria.where("isDeleted").ne(true)));
		Lead lead = mongoTemplate.findOne(query, Lead.class);
		if (lead == null)
			throw new EmptyResultDataAccessException("No Lead Found", 1);
		return lead;
	}

	/**
	 * Method to do the upsert the lead data into DB
	 */
	@Override
	public void saveOrUpdateLead(Lead lead) throws Exception {
		mongoTemplate.save(lead);
	}

	/**
	 * Method used to count the no. of lead's document present. It is used to
	 * generate the unique Id
	 */
	@Override
	public long countLeads() throws Exception {
		return mongoTemplate.count(new Query(), Lead.class);
	}

}
