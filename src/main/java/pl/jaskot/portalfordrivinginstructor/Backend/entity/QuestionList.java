package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.extern.java.Log;
import pl.jaskot.portalfordrivinginstructor.Backend.configuration.FilesPaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Log
public class QuestionList {

    private static final String COMMA_DELIMITER = ";";
    private int basicQuestNumber = 20, bThree = 10, bTwo = 6, bOne =4;
    private int specialQuestNumber = 12, sThree = 6, sTwo = 4, sOne = 2;

    private List<List<String>> records, randomRecords, examQuestions, basicQuestions, specialQuestion;


    public QuestionList()  {}

    public List<List<String>> getQuestionsForExam(){
        examQuestions = new ArrayList<>();
        basicQuestions = new ArrayList<>();
        specialQuestion = new ArrayList<>();

        // get basic questions
        // ======================================================
        basicQuestions = getExamQuestions(1);
        basicQuestions.addAll(getExamQuestions(2));
        basicQuestions.addAll(getExamQuestions(3));
        Collections.shuffle(basicQuestions);

        // get special questions
        specialQuestion = getExamQuestions(4);
        specialQuestion.addAll(getExamQuestions(5));
        specialQuestion.addAll(getExamQuestions(6));
        Collections.shuffle(specialQuestion);

        examQuestions.addAll(basicQuestions);
        examQuestions.addAll(specialQuestion);

        return examQuestions;
    }

    public List<List<String>> getQuestions() throws FileNotFoundException{
        records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FilesPaths.getStandardPath()));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }catch (Exception e){ }
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

    // 1>bOne, 2>bTwo, 3>bThree, 4>sOne, 5>sTwo, 6>sThree
    private List<List<String>> getExamQuestions(int i){
        records = new ArrayList<>();
        randomRecords = new ArrayList<>();

        String path;
        int value;
        if(i == 1){
            path = FilesPaths.getPathBasicOne();
            value = bOne;
        } else if(i == 2){
            path = FilesPaths.getPathBasicTwo();
            value = bTwo;
        } else if(i == 3){
            path = FilesPaths.getPathBasicThree();
            value = bThree;
        } else if(i == 4){
            path = FilesPaths.getPathSpecialOne();
            value = sOne;
        } else if(i == 5){
            path = FilesPaths.getPathSpecialTwo();
            value = bTwo;
        } else if(i == 6){
            path = FilesPaths.getPathSpecialThree();
            value = bThree;
        } else {
            path = FilesPaths.getStandardPath();
            value = 10;
        }

        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                randomRecords.add(getRecordFromLine(scanner.nextLine()));
            }
        }catch (Exception e){
            log.warning(e.toString());
        }

        Collections.shuffle(randomRecords);

        for(int x = 0; x< value; x++){
            records.add(randomRecords.get(x));
        }

        return records;
    }
}
