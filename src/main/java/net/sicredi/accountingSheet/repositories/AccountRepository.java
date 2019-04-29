package net.sicredi.accountingSheet.repositories;

import net.sicredi.accountingSheet.domain.entity.Account;
import net.sicredi.accountingSheet.repositories.custom.AccountRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>, AccountRepositoryCustom {
}
