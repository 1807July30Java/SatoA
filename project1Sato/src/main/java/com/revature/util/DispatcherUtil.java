package com.revature.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.revature.service.UltraService;

public class DispatcherUtil {

	private UltraService us = new UltraService();
	ObjectMapper om = new ObjectMapper();

	public String processGet(String entity, String get, int id) {
		if (entity.equals("employee")) {
			if (get.equals("all")) {
				return new Gson().toJson(us.allEmployees());
			} else if (get.equals("managed")) {
				return new Gson().toJson(us.employeesManaged(id));
			} else if (get.equals("name")) {
				return us.getEmployee(id).getFirstName() +" "+us.getEmployee(id).getLastName();
			}
		} else if (entity.equals("request")) {
			//System.out.println("Hello");
			try {
				if (get.equals("all")) {
					return om.writeValueAsString(us.allRequests());
				} else if (get.equals("by")) {
					return om.writeValueAsString(us.requestsby(id));
				} else if (get.equals("for")) {
					//System.out.println("getting requests for "+ Integer.toString(id));
					return om.writeValueAsString(us.requestsfor(id));
				}
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return "error";
	}

}
