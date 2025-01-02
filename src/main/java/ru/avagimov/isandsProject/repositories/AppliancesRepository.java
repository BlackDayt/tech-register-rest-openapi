package ru.avagimov.isandsProject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.avagimov.isandsProject.models.Appliance;

@Repository
public interface AppliancesRepository extends JpaRepository<Appliance, Integer> {

}
