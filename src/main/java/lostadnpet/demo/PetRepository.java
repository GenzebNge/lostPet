package lostadnpet.demo;

import org.springframework.data.repository.CrudRepository;


public interface PetRepository extends CrudRepository<Pet, Long> {
    Pet findAllByStatusContainingIgnoreCase(String status);

}
