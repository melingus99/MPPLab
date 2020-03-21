package repository.FileRepository;

import domain.LabProblem;
import domain.Student;
import domain.validators.Validator;
import domain.validators.ValidatorException;
import repository.InMemoryRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class LabProblemFileRepository extends InMemoryRepository<Long, LabProblem> {
    private String fileName;

    public LabProblemFileRepository(Validator<LabProblem> validator, String fileName) {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private void loadData() {
        Path path = Paths.get(fileName);

        try {
            Files.lines(path).forEach(line -> {
                List<String> items = Arrays.asList(line.split(","));

                Long id=Long.valueOf(items.get(0));
                String name = items.get(1);
                String dueTime = items.get(2);

                LabProblem labProblem= new LabProblem(name,dueTime);
                labProblem.setId(id);

                try {
                    super.add(labProblem);
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
                    bufferedWriter.write(entity.getId() + "," + entity.getName() + "," + entity.getDueTime());
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
