package com.petsociety.backend.controller;

import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.petsociety.backend.entity.TriviaEntity;
import com.petsociety.backend.service.TriviaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trivia")
public class TriviaController {
	@Autowired
	TriviaService sserv;
	
	// C - Create
	@PostMapping("/insertTrivia")
	public TriviaEntity insertTrivia(@RequestBody TriviaEntity entry) {
		return sserv.insertTrivia(entry);
	}
	
	// R - Read
	@GetMapping("/getAllTrivia")
	public List<TriviaEntity> getAllTrivia(){
		return sserv.getAllTrivia();
	}
	
	 @GetMapping("/getTrivia/{triviaID}")
	 public ResponseEntity<TriviaEntity> getTriviaById(@PathVariable int triviaID) {
	   try {
	     TriviaEntity trivia = sserv.getTriviaById(triviaID);
	     return ResponseEntity.ok().body(trivia);
	    } catch (NoSuchElementException e) {
	     return ResponseEntity.notFound().build();
	    }
	  }
	
	// U - Update 
	@PutMapping("/updateTrivia")
	public TriviaEntity updateTrivia(@RequestParam int triviaID, @RequestBody TriviaEntity newTriviaDetails) {
		return sserv.updateTrivia(triviaID, newTriviaDetails);
	}

	// D - Delete
	 @PutMapping("/deleteTrivia/{triviaID}")
	 public String deleteEntry(@PathVariable int triviaID) {
	     return sserv.deleteTrivia(triviaID);
	 }
	
	 // Get Random Trivia
     @GetMapping("/getRandomTriviaDetails")
     public ResponseEntity<Map<String, Object>> getRandomTriviaDetails() {
         try {
             Map<String, Object> triviaDetails = sserv.getRandomTriviaDetails();
             return ResponseEntity.ok().body(triviaDetails);
         } catch (Exception e) {
             return ResponseEntity.status(500).build();
         }
     }
}
