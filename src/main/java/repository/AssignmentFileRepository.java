package repository;

import domain.Assignment;
import domain.Student;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class AssignmentFileRepository extends InMemoryRepository<Long, Assignment> {
    private String fileName;

    public AssignmentFileRepository(Validator<Assignment> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id = Long.valueOf(items.get(0));
                Long idStudent = Long.valueOf(items.get(1));
                Long idLabProblem = Long.valueOf(items.get((2)));
                float grade=Float.valueOf(items.get(3));

                Assignment assignment=new Assignment(idStudent,idLabProblem);
                assignment.setId(id);
                assignment.setGrade(grade);

                try {
                    super.add(assignment);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public void saveToFile() {
        Path path = Paths.get(fileName);

        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
            this.findAll().forEach(entity->
            {
                try {
                    bufferedWriter.write(entity.getId() + "," + entity.getStudentId() + "," + entity.getLabProblemId()+","+entity.getGrade());
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
