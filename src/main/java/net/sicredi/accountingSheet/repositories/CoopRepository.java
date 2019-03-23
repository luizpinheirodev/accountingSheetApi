package net.sicredi.accountingSheet.repositories;

import net.sicredi.accountingSheet.domain.entity.Coop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoopRepository extends MongoRepository<Coop, String> {
}
