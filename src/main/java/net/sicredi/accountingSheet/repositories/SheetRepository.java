package net.sicredi.accountingSheet.repositories;

import net.sicredi.accountingSheet.entities.Sheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends MongoRepository<Sheet, String> {

}
