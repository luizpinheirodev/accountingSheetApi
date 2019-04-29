package net.sicredi.accountingSheet.repositories.custom;

import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import net.sicredi.accountingSheet.domain.entity.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class AccountRepositoryCustomImpl implements AccountRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public AccountRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<AccountDTO> findByAccountStartingWith(String number) {
        Aggregation aggregation = Aggregation.newAggregation(
                //Aggregation.project("account", "value"),
                //Aggregation.match(Criteria.where("account").is(number)),
                match(Criteria.where("account").regex("^" + number)),
                group("account").sum("value").as("total"),
                project("total").and("account").previousOperation()
        );
        AggregationResults groupResult = mongoTemplate.aggregate(
                aggregation, Sheet.class, AccountDTO.class
        );

        //List<AccountDTO> listTest = groupResult.getMappedResults();

        return groupResult.getMappedResults();
    }
}
