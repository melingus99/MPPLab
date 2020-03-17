package repository;

import domain.validators.ValidatorException;
import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Student;
import domain.validators.Validator;

public class StudentXmlRepository extends InMemoryRepository<Long, Student> {
    private String fileName;

    public StudentXmlRepository(Validator<Student> validator) throws IOException, SAXException, ParserConfigurationException {
        super(validator);
        this.fileName = fileName;

        loadData();
    }

    private static Student createStudentFromElement(Element studentElement) {

        Long id = Long.valueOf(studentElement.getAttribute("id"));
        Node groupNode = studentElement.getElementsByTagName("group").item(0);
        long group = Long.valueOf(groupNode.getTextContent());
        Node nameNode = studentElement.getElementsByTagName("name").item(0);
        String name = nameNode.getTextContent();
        Student student = new Student(name, group);
        student.setId(id);
        return student;
    }

    private void loadData() throws ParserConfigurationException, IOException, SAXException {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(this.fileName);

        Element root = document.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int index = 0; index < children.getLength(); index++) {
            Node studentNode = children.item(index);
            if (studentNode instanceof Element) {
                Student newStudent = createStudentFromElement((Element) studentNode);
                try {
                    super.add(newStudent);
                } catch (ValidatorException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Node studentToNode(Student student, Document document){
        Element studentElement = document.createElement("student");
        studentElement.setAttribute("id", student.getId().toString());
        Element groupElement = document.createElement("group");
        groupElement.setTextContent(student.getGroup().toString());
        studentElement.appendChild(groupElement);
        Element nameElement = document.createElement("name");
        nameElement.setTextContent(student.getName());
        studentElement.appendChild(groupElement);
        return studentElement;
    }

    public void saveStudent(Student student) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(fileName);

        Element root = document.getDocumentElement();
        Node studentNode = studentToNode(student, document);
        root.appendChild(studentNode);

        Transformer transformer= TransformerFactory
                .newInstance()
                .newTransformer();
        transformer.transform(new DOMSource(document),
                new StreamResult(new File("./data/bookstore2.xml")));
    }
}


