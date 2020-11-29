package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionList {

    private static final String COMMA_DELIMITER = ";";
    private List<List<String>> records;

    public QuestionList() throws FileNotFoundException {
        records = new ArrayList<>();
         try (Scanner scanner = new Scanner(new File("files/pytania.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }catch (Exception e){

        }


    }

    public List<List<String>> getQuestions() {
        return records;
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
