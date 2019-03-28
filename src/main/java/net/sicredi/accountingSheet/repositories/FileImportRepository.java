package net.sicredi.accountingSheet.repositories;

import net.sicredi.accountingSheet.domain.entity.FileImport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileImportRepository extends MongoRepository<FileImport, String> {
}
