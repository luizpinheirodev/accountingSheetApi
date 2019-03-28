package net.sicredi.accountingSheet.repositories;

import net.sicredi.accountingSheet.domain.entity.Sheet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepository extends MongoRepository<Sheet, String> {

    //List<Sheet> findByCooperative(String cooperative);
    //List<Sheet> findByCooperativeAndAccount(String cooperative, String account);
    //void saveAll(List<Sheet> sheets);

}
