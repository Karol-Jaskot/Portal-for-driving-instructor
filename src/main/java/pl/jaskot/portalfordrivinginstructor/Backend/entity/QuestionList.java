package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuestionList {

    private static final String COMMA_DELIMITER = ";";
    private int basicQuestNumber = 20, bThree = 10, bTwo = 6, bOne =4;
    private int specialQuestNumber = 12, sThree = 6, sTwo = 4, sOne = 2;
    private String starndardPath = "files/standardQuest.csv";

    private String pathBasicOne = "files/basicOneQuest.csv";
    private String pathBasicTwo = "files/basicTwoQuest.csv";
    private String pathBasicThree = "files/basicThreeQuest.csv";

    private String pathSpecialOne = "files/specialOneQuest.csv";
    private String pathSpecialTwo = "files/specialTwoQuest.csv";
    private String pathSpecialThree = "files/specialThreeQuest.csv";

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
        try (Scanner scanner = new Scanner(new File(starndardPath));) {
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
            path = pathBasicOne;
            value = bOne;
        } else if(i == 2){
            path = pathBasicTwo;
            value = bTwo;
        } else if(i == 3){
            path = pathBasicThree;
            value = bThree;
        } else if(i == 4){
            path = pathSpecialOne;
            value = sOne;
        } else if(i == 5){
            path = pathSpecialTwo;
            value = bTwo;
        } else if(i == 6){
            path = pathSpecialThree;
            value = bThree;
        } else {
            path = starndardPath;
            value = 10;
        }

        try (Scanner scanner = new Scanner(new File(path));) {
            while (scanner.hasNextLine()) {
                randomRecords.add(getRecordFromLine(scanner.nextLine()));
            }
        }catch (Exception e){ }

        Collections.shuffle(randomRecords);

        for(int x = 0; x< value; x++){
            records.add(randomRecords.get(x));
        }

        return records;
    }
}
