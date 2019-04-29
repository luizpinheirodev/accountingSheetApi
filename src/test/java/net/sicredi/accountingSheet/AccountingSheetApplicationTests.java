package net.sicredi.accountingSheet;

import net.sicredi.accountingSheet.domain.dto.AccountDTO;
import net.sicredi.accountingSheet.domain.entity.Account;
import net.sicredi.accountingSheet.domain.entity.Sheet;
import net.sicredi.accountingSheet.mapper.AccountMapper;
import net.sicredi.accountingSheet.repositories.CoopRepository;
import net.sicredi.accountingSheet.repositories.SheetRepository;
import net.sicredi.accountingSheet.service.AccountService;
import net.sicredi.accountingSheet.service.SheetService;
import net.sicredi.accountingSheet.util.importFiles.ImportCsv;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountingSheetApplicationTests {

    @Autowired
    private SheetRepository sheetRepository;

    @Autowired
    private CoopRepository coopRepository;

    @Autowired
    private SheetService sheetService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
    //DecimalFormat df = new DecimalFormat("000,00");

    @Test
    public void insertSheet() throws ParseException {

        ImportCsv iC = null;
        Iterable<Sheet> sheets = null;
        //iC = new ImportCsv("C:\\Users\\luiz_geraldo\\Desktop\\Conc. Coop. Seguro Prestamista RS.csv");

        //List<String[]> allData = iC.readAll();

        List<Sheet> sheetList = new ArrayList<>();

        /*
        for (String[] line : allData.subList(1, allData.size())) {
            Sheet l = new Sheet();
            l.setCooperative(line[0]);
            l.setAgency(line[1]);
            l.setNumber(line[2]);
            l.setDate(sdf.parse(line[3]));
            l.setDescription(line[4]);
            l.setValue(new BigDecimal(line[5].replace(".", "").replace(",", ".")));
            l.setResponsibility(line[6]);
            l.setStatus(line[7]);
            l.setEmailDate(line[8]);
            l.setCriticality(line[9]);

            sheetList.add(l);
        }

        sheetRepository.saveAll(sheetList);
        */
    }

    @Test
    public void insertCoop() {
        /*
        List<Coop> coopList = new ArrayList<>();
        Coop coop1 = new Coop("0101", "COOPERATIVA DE CREDITO, POUPANCA E INVESTIMENTO SICREDI PIONEIRA RS - SICREDI PIONEIRA RS");
        Coop coop2 = new Coop("0105", "COOPERATIVA DE ECONOMIA E CREDITO  MUTUO DOS INTEGRANTES DO MINISTERIOPUBLICO DO RIO GRANDE DO SUL");
        Coop coop3 = new Coop("0106", "COOPERATIVA DE CREDITO, POUPANCA E INVESTIMENTO DOS JUIZES DO ESTADO  DO RS - SICREDI AJURIS RS");
        Coop coop4 = new Coop("0109", "COOPERATIVA DE CREDITO, POUPANCA E INVESTIMENTO ENCOSTA SUPERIOR DO   NORDESTE RS - SICREDI NORDESTE RS");

        coopList.add(coop1);
        coopList.add(coop2);
        coopList.add(coop3);
        coopList.add(coop4);

        //coopRepository.saveAll(coopList);
        */
    }

    @Test
    public void insertAccount(){
        Account a1 = new Account();
        a1.setNumber("4518090006");
        a1.setName("Convênio Seguro Prestamista");
        a1.setDescription("Conta referente aos convênios de seguro prestmista");
        AccountDTO a1DTO = accountMapper.toDTO(a1);

        Account a2 = new Account();
        a2.setNumber("4518090007");
        a2.setName("Convênio Pagamento");
        a2.setDescription("Conta referente aos convênios de diversos de pagamentos");
        AccountDTO a2DTO = accountMapper.toDTO(a2);


        accountService.createAccount(a1DTO);
        accountService.createAccount(a2DTO);

    }

    @Test
    public void sumAccounts() {
        String number = "451809000";
        List<AccountDTO> getAccountTotal = accountService.findByAccountStartingWith(number);

        for (AccountDTO list : getAccountTotal) {
            System.out.println(list.toString());
        }
    }

}
//12626.14