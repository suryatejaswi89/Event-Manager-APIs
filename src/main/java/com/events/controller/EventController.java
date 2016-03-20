package com.events.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.events.model.Event;
import com.events.repository.EventRepository;

/*
 * This controller deals with event repository and event model 
 * and performs various operations on them 
 * like creating an event, deleting an event, updating an event **/


@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventRepository eventRepository;
	private List<Event> eventsinthishall;
	
	/*
	 * This method creates an event, taking the information
	 * provided by the user in the form as a json object
	 * and converts into a map, saves it in the event repository**/
	
	@RequestMapping(method=RequestMethod.POST)
	public Map<String, Object> createEvent(@RequestBody Map<String, Object> eventMap){
		Event event = new Event(eventMap.get("customerName").toString(),
							eventMap.get("hallID").toString());
		
		eventRepository.save(event);
		Map<String,Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Event is successfully created");
		response.put("event", event);
		return response;
	}
	
	/*
	 * The following method displays all the events that 
	 * are supposed to be held in a hall. There is no input for this funcion and
	 * the output will be all the events displayed as json objects with information
	 */
	
	@RequestMapping(method=RequestMethod.GET)
	public Map<String,Object> getAllEvents(){
		List<Event> events = eventRepository.findAll();
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("total events", events.size());
		response.put("Events", events);
		return response;
	}
	
	/*
	 * The following method, geteventdetails, asks in for the input 
	 * from the user and the input is eventID. Then searches for the particular 
	 * event and displays its information.
	 */
	
	@RequestMapping(method=RequestMethod.GET, value="/{events/eventID}")
	public Event getEventDetails(@PathVariable("events/eventID") String eventID){
		 return eventRepository.findOne(eventID);
	}
	
	/*
	 * The following method, geteventsbyhallID, asks in for the input 
	 * from the user and the input is hallID. Then searches for all events
	 * registered under that particular hall and displays the list. 
	 */
	
	@RequestMapping(method=RequestMethod.GET, value="/{hallID}")
	public Map<String,Object> geteventsbyHallID(@PathVariable("hallID") String hallID){
		List<Event> events = eventRepository.findAll();
		//Iterator<Event> itr = events.iterator();
		Map<String, Object> response = new LinkedHashMap<String,Object>();
		
		List<Event> eventsinthishall = new ArrayList<Event>();
		
		for(int i=0; i<events.size(); i++){
			 Event event = events.get(i);
			 
			 if(event.getHallID().equalsIgnoreCase(hallID)){
				 eventsinthishall.add(event);
				 
				 
			 }
		}
		response.put("total events in this hall", eventsinthishall.size());
		response.put("Events in this hall", eventsinthishall);	
		return response;
	}
	
	
	
	/*
	 * The delete event method, takes in the eventID from the user as
	 * input and searches the events map for that ID and then deletes the
	 * matching result from the event repository
	 */
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{eventID}")
	public Map<String, String> deleteevent(@PathVariable("eventID") String eventID){
		eventRepository.delete(eventID);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "event has been deleted successfully");
		return response;
	}
	

}
