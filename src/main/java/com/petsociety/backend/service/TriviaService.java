    package com.petsociety.backend.service;

    import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
    import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.petsociety.backend.entity.TriviaEntity;
    import com.petsociety.backend.repository.TriviaRepository;

    @Service
    public class TriviaService {
            @Autowired
        TriviaRepository srepo;
        
        // C - CREATE OR INSERT STUDENT RECORD IN tblStudent
        public TriviaEntity insertTrivia(TriviaEntity entry) {
            return srepo.save(entry);
        }
        
        // R - READ ALL RECORDS IN tlbStudebt
        public List<TriviaEntity> getAllTrivia(){
            return srepo.findAll();
        }
        
        // Read a Single Entry by DictionaryID
        public TriviaEntity getTriviaById(int triviaID) {
            return srepo.findById(triviaID).orElseThrow(() -> new NoSuchElementException("Entry " + triviaID + " does not exist!"));
        }
        
        // U - UPDATE A RECORD IN tlbStudent
        @SuppressWarnings("finally")
        public TriviaEntity updateTrivia(int triviaID, TriviaEntity newEntryDetails) {
            TriviaEntity trivia = new TriviaEntity();
            try {
                // Search the id number of the student will be updated
                trivia = srepo.findById(triviaID).get();
                
                trivia.setTitle(newEntryDetails.getTitle());
                trivia.setContent(newEntryDetails.getContent());
                trivia.setCategory(newEntryDetails.getCategory());
                trivia.setAuthor(newEntryDetails.getAuthor());
                trivia.setIsDeleted(newEntryDetails.getIsDeleted());

            }catch(NoSuchElementException ex) {
                throw new NoSuchElementException("Entry " + triviaID + "does not exist!");
            }finally {
                return srepo.save(trivia);
            }
        }
        
        // D - DELETE A RECORD IN tlbStudent
        public String deleteTrivia(int dicID) {
            String msg = "";
            
            Optional<TriviaEntity> optionalEntry = srepo.findById(dicID);
            
            if (optionalEntry.isPresent()) {
                TriviaEntity entry = optionalEntry.get();
                entry.setIsDeleted(true); // Update the isDeleted field
                srepo.save(entry); // Save the updated entity back to the database
                msg = "Entry " + dicID + " is successfully 'deleted'!";
            } else {
                msg = "Entry " + dicID + " does not exist!";
            }
            
            return msg;
        }

    public Map<String, Object> getRandomTriviaDetails() {
        try {
            // Fetch all trivia entries
            List<TriviaEntity> allTrivia = getAllTrivia();

            // If there are no trivia entries, return an empty map or handle as needed
            if (allTrivia.isEmpty()) {
                return new HashMap<>();
            }

            // Generate a random index
            Random random = new Random();
            int randomIndex = random.nextInt(allTrivia.size());

            // Get the random trivia entry
            TriviaEntity randomTrivia = allTrivia.get(randomIndex);

            // Create a map with the desired fields
            Map<String, Object> triviaDetails = new HashMap<>();
            triviaDetails.put("author", randomTrivia.getAuthor());
            triviaDetails.put("category", randomTrivia.getCategory());
            triviaDetails.put("title", randomTrivia.getTitle());
            triviaDetails.put("content", randomTrivia.getContent());

            return triviaDetails;
        } catch (NoSuchElementException e) {
            // Handle exceptions as needed
            throw e;
        }
    }
}
