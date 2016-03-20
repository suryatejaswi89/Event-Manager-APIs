package com.events.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.events.model.Hall;
import com.events.repository.HallRepository;

@RestController
@RequestMapping("/hall")
public class HallController {

	@Autowired
	private HallRepository hallRepository;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public Map<String, Object> createHall(@RequestBody Map<String, Object> hallMap){
		Hall hall=new Hall(hallMap.get("name").toString(),
						hallMap.get("address").toString(),
						Integer.parseInt(hallMap.get("capacity").toString()));
		
		hallRepository.save(hall);
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Hall is successfully created");
		response.put("hall", hall);
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{hallId}")
	public Hall getHallDeatails(@PathVariable("hallId") String hallId){
		return hallRepository.findOne(hallId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{hallID}")
	public Map<String, String> deleteHall(@PathVariable("hallID") String hallID){
		hallRepository.delete(hallID);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Hall has been deleted successfully");
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public Map<String, Object> getAllHalls(){
		List<Hall> halls = hallRepository.findAll();
		Map<String, Object> response=new LinkedHashMap<String, Object>();
		response.put("total halls", halls.size());
		response.put("Halls", halls);
		return response;
	}
	
	/*@RequestMapping(method=RequestMethod.POST, value="/{hallID}")
	public Map<String, Object> addEvent(@RequestBody Map<String, Object> hallMap, @PathVariable("hallID") String hallID){
		//public Map<String,String> createEvent(@ResponseBody Map<String, String> eventMap);
		String dateString = hallMap.get("date").toString();
		SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yy");
		Date dDate;
		try {
			dDate = sdfmt1.parse( dateString );
			
			Event event = new Event(hallMap.get("custerName").toString(),dDate,hallMap.get("hallID").toString());
			
			Hall hall= hallRepository.findOne(hallID);
			hall.addEvent(event);
			
			hallRepository.save(hall);
			Map<String, Object> response = new LinkedHashMap<String, Object>();
			response.put("message", "Hall is successfully created");
			response.put("hall", hall);
			return response;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}*/
	
}
