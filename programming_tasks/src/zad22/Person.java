package zad22;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Person {

    private final String name;
    private final String surname;
    private final LocalDate birthDate;
    private final String city;

    public Person(String name, String surname, LocalDate birthDate, String city) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.city = city;
    }

    public static void main(String[] args) {
        String fileName = "persons";

        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("John", "Doe", LocalDate.of(1990, 1, 1), "New York"),
                new Person("Alice", "Smith", LocalDate.of(1995, 2, 2), "Los Angeles"),
                new Person("Bob", "Johnson", LocalDate.of(2000, 3, 3), "Chicago"),
                new Person("Eve", "Williams", LocalDate.of(2005, 4, 4), "Houston")
        ));

        writeToZip(fileName, personList);

        List<Person> readPersonList = readFromZip(fileName);

        for (Person person : readPersonList) {
            System.out.println(person.getName() + " " + person.getSurname() + " " + person.getBirthDate() + " " + person.getCity());
        }
    }

    private static List<Person> readFromZip(String fileName) {
        List<Person> personList = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileName + ".zip"))) {
            zipInputStream.getNextEntry();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] personData = line.split(",");
                personList.add(new Person(personData[0], personData[1], LocalDate.parse(personData[2]), personData[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    private static void writeToZip(String fileName, List<Person> personList) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(fileName + ".zip"))) {
            ZipEntry zipEntry = new ZipEntry(fileName + ".txt");
            zipOutputStream.putNextEntry(zipEntry);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(zipOutputStream));

            for (Person person : personList) {
                bufferedWriter.write(person.getName() + ",");
                bufferedWriter.write(person.getSurname()+ ",");
                bufferedWriter.write(person.getBirthDate().toString() + ",");
                bufferedWriter.write(person.getCity() + "\n");
            }

            bufferedWriter.flush();
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCity() {
        return city;
    }
}
