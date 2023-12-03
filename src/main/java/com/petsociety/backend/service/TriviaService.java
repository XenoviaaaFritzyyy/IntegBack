    package com.petsociety.backend.service;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.NoSuchElementException;
    import java.util.Optional;
    import java.util.Random;
    import java.util.stream.Collectors;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import com.petsociety.backend.entity.TriviaEntity;
    import com.petsociety.backend.repository.TriviaRepository;

    @Service
    public class TriviaService {
            @Autowired
        TriviaRepository srepo;
        
        // C - CREATE OR INSERT
        public TriviaEntity insertTrivia(TriviaEntity entry) {
            return srepo.save(entry);
        }
        
        // R - READ ALL RECORDS
        public List<TriviaEntity> getAllTrivia(){
            return srepo.findAll();
        }
        
        // Read a Single Entry
        public TriviaEntity getTriviaById(int triviaID) {
            return srepo.findById(triviaID).orElseThrow(() -> new NoSuchElementException("Entry " + triviaID + " does not exist!"));
        }
        
        // U - UPDATE A RECORD
        @SuppressWarnings("finally")
        public TriviaEntity updateTrivia(int triviaID, TriviaEntity newTriviaDetails) {
            TriviaEntity trivia = new TriviaEntity();
            try {
                trivia = srepo.findById(triviaID).get();

                trivia.setTitle(newTriviaDetails.getTitle());
                trivia.setContent(newTriviaDetails.getContent());
                trivia.setAuthor(newTriviaDetails.getAuthor());
                trivia.setCategory(newTriviaDetails.getCategory());

            } catch (NoSuchElementException ex) {
                throw new NoSuchElementException("Pet " + triviaID + " does not exist!");
            } finally {
                return srepo.save(trivia);
            }
        }
        
        // D - DELETE TRIVIA
        public String deleteTrivia(int triviaID) {
            String msg = "";

            Optional<TriviaEntity> optionalEntry = srepo.findById(triviaID);

            if (optionalEntry.isPresent()) {
                TriviaEntity entry = optionalEntry.get();
                
                entry.setIsDeleted(true);
                srepo.save(entry);
                msg = "Entry " + triviaID + " is successfully 'deleted'!";
            } else {
                msg = "Entry " + triviaID + " does not exist!";
            }
            return msg;
        }

        public Map<String, Object> getRandomTriviaDetails() throws NoSuchElementException {
            List<TriviaEntity> nonDeletedTrivia = getAllTrivia().stream()
                    .filter(trivia -> !trivia.getIsDeleted())
                    .collect(Collectors.toList());
        
            // If there are no non-deleted trivia entries, throw NoSuchElementException
            if (nonDeletedTrivia.isEmpty()) {
                throw new NoSuchElementException("No non-deleted trivia entries available");
            }
        
            // Generate a random index
            Random random = new Random();
            int randomIndex = random.nextInt(nonDeletedTrivia.size());
        
            // Get the random non-deleted trivia entry
            TriviaEntity randomTrivia = nonDeletedTrivia.get(randomIndex);
        
            // Create a map with the desired fields
            Map<String, Object> triviaDetails = new HashMap<>();
            triviaDetails.put("author", randomTrivia.getAuthor());
            triviaDetails.put("category", randomTrivia.getCategory());
            triviaDetails.put("title", randomTrivia.getTitle());
            triviaDetails.put("content", randomTrivia.getContent());
        
            return triviaDetails;
        }
    }
