package net.sicredi.accountingSheet.util.importFiles;

public class ImportCsv {

    //private MultipartFile file;

    //public ImportCsv(MultipartFile file) {
    //    this.file = file;
    //}




    /*

    public List readAll() {

        CSVReader csvReader = null;
        List<String[]> allData = null;

        try {
            FileReader filereader = new FileReader(file)
            CSVParser parser = new CSVParserBuilder().withSeparator(';').withStrictQuotes(false).build();
            csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();
            allData = csvReader.readAll();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
        return allData;
    }
*/

    /*Importação linha a linha para array

    public void readAll(){
        try {
            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
}
