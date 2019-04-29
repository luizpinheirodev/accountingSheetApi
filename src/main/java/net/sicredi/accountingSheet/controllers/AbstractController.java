package net.sicredi.accountingSheet.controllers;

import io.vavr.control.Either;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractController<D> {

    protected ResponseEntity buildResponse(final Either<Throwable, D> result) throws Throwable {
        if (result.isLeft()) {
            try {
                throw result.getLeft();
            } catch (ChangeSetPersister.NotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        return ResponseEntity.ok(result.get());
    }

}
