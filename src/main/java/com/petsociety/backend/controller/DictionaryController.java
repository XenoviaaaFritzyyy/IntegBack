package com.petsociety.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.DictionaryEntity;
import com.petsociety.backend.service.DictionaryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	DictionaryService sserv;
	
	// C - Create a Student Record
	@PostMapping("/insertEntry")
	public DictionaryEntity insertEntry(@RequestBody DictionaryEntity entry) {
		return sserv.insertEntry(entry);
	}
	
	// R - Read a Student Record
	@GetMapping("/getAllEntry")
	public List<DictionaryEntity> getAllEntry(){
		return sserv.getAllEntry();
	}
	
	 @GetMapping("/getEntry/{dicID}")
	 public ResponseEntity<DictionaryEntity> getEntryById(@PathVariable int dicID) {
	   try {
	     DictionaryEntity entry = sserv.getEntryById(dicID);
	     return ResponseEntity.ok().body(entry);
	    } catch (NoSuchElementException e) {
	     return ResponseEntity.notFound().build();
	    }
	  }
	
	// U - Update a Student Record
	 @PutMapping("/updateEntry/{dicID}")
	 public DictionaryEntity updateEntry(@PathVariable int dicID, @RequestBody DictionaryEntity newEntryDetails) {
	     return sserv.updateEntry(dicID, newEntryDetails);
	 }
	
	 @PutMapping("/deleteEntry/{dicID}")
	 public String deleteEntry(@PathVariable int dicID) {
	     return sserv.deleteEntry(dicID);
	 }
	
}
