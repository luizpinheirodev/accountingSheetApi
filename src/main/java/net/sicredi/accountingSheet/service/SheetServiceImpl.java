package net.sicredi.accountingSheet.service;

import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import net.sicredi.accountingSheet.domain.dto.SheetDTO;
import net.sicredi.accountingSheet.domain.entity.Sheet;
import net.sicredi.accountingSheet.mapper.SheetMapper;
import net.sicredi.accountingSheet.repositories.SheetRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class SheetServiceImpl implements SheetService {

    private final SheetRepository sheetRepository;
    private final SheetMapper sheetMapper;
    private final MongoTemplate mongoTemplate;

    public SheetServiceImpl(SheetRepository sheetRepository, SheetMapper sheetMapper, MongoTemplate mongoTemplate) {
        this.sheetRepository = sheetRepository;
        this.sheetMapper = sheetMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Collection<SheetDTO>> findAll() {
        return Optional.ofNullable(sheetRepository.findAll()).flatMap(sheetMapper::toDTO);
    }


    public List<AccountDTO> findAccountAndTotal(String number){
        GroupOperation groupByAccount = group("account").sum("value").as("total");
        MatchOperation filter = match(new Criteria("account").is(number));

        Aggregation aggregation = newAggregation(
                groupByAccount, filter
        );
        AggregationResults<AccountDTO> result = mongoTemplate.aggregate(
                aggregation, Sheet.class, AccountDTO.class
        );

        List<AccountDTO> accountList = result.getMappedResults();
        return accountList;
    }


}
