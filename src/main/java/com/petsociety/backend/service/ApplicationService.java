	package com.petsociety.backend.service;

	import java.util.List;
	import java.util.NoSuchElementException;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.stereotype.Service;
	import org.springframework.web.server.ResponseStatusException;

	import com.petsociety.backend.entity.ApplicationEntity;
	import com.petsociety.backend.entity.PetEntity;
	import com.petsociety.backend.entity.UserEntity;
	import com.petsociety.backend.repository.ApplicationRepository;
	import com.petsociety.backend.repository.PetRepository;
	import com.petsociety.backend.repository.UserRepository;


	@Service
	public class ApplicationService {
		@Autowired
		ApplicationRepository srepo;
		
		@Autowired
		PetRepository petRepository; 	

		@Autowired
		UserRepository userRepository; 

		public ApplicationEntity insertApplication(ApplicationEntity application, int petId, int userId) {
			PetEntity pet = petRepository.findById(petId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));

			UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

			application.setPet(pet);
			application.setUser(user);

			return srepo.save(application);
		}
		
		// R - READ RECORD
		public List<ApplicationEntity> getAllApplication(){
			return srepo.findAll();
		}


		// U - UPDATE RECORD
		@SuppressWarnings("finally")
		public ApplicationEntity updateEntry(int applicationID, ApplicationEntity newEntryDetails) {
			ApplicationEntity entry = new ApplicationEntity();
			try {
				entry = srepo.findById(applicationID).get();
				
				entry.setStatus(newEntryDetails.getStatus());
				entry.setMessage(newEntryDetails.getMessage());
				entry.setIsDeleted(newEntryDetails.getIsDeleted());


			}catch(NoSuchElementException ex) {
				throw new NoSuchElementException("Entry " + applicationID + "does not exist!");
			}finally {
				return srepo.save(entry);
			}
		}
}
