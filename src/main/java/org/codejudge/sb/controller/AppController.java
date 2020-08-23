package org.codejudge.sb.controller;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.Map;

import org.codejudge.sb.service.LeadService;
import org.codejudge.sb.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AppController {

	private final LeadService leadService;

	public AppController(@Autowired LeadService leadService) {
		this.leadService = leadService;
	}

	@ApiOperation("This handler fetches a lead profile")
	@GetMapping(value = "/leads/{lead_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> fetchLead(@PathVariable("lead_id") String leadId) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			response = leadService.getLeadInfo(Integer.parseInt(leadId));
		} catch (Exception e) {
			status = Utils.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler creates a lead")
	@PostMapping("/leads")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> createLead(@RequestBody Map<String, Object> leadDetails) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.CREATED;

		try {
			response = leadService.createLead(leadDetails);
		} catch (Exception e) {
			response = Utils.gotFailure(e);
			status = Utils.getStatus(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler updates a lead")
	@PutMapping(value = "/leads/{lead_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateLead(@PathVariable("lead_id") String leadId,
			@RequestBody Map<String, Object> leadDetails) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			leadService.updateLead(Integer.parseInt(leadId), leadDetails);
			response.put("status", "success");
		} catch (Exception e) {
			status = Utils.getStatus(e);
			response = Utils.gotFailure(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler mark a lead contacted")
	@PutMapping(value = "/mark_lead/{lead_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> markLead(@PathVariable("lead_id") String leadId,
			@RequestBody Map<String, Object> leadDetails) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			response = leadService.markLead(Integer.parseInt(leadId), leadDetails);
		} catch (Exception e) {
			status = Utils.getStatus(e);
			response = Utils.gotFailure(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@ApiOperation("This handler deletes a lead profile")
	@DeleteMapping(value = "/leads/{lead_id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteLead(@PathVariable("lead_id") String leadId) {

		Map<String, Object> response = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		try {
			leadService.deleteLead(Integer.parseInt(leadId));
			response.put("status", "success");
		} catch (Exception e) {
			status = Utils.getStatus(e);
			response = Utils.gotFailure(e);
		}
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

}
