package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    private int count;
    @Parameter(names = "-f", description = "Target file")
    private String file;
    @Parameter(names = "-d", description = "Data format")
    private String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
            generator.run();
        } catch (NullPointerException | ParameterException ex) {
            jCommander.usage();
        }
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        switch (format) {
            case "csv":
                saveAsCsv(contacts, new File(file));
                break;
            case "xml":
                saveAsXml(contacts, new File(file));
                break;
            case "json":
                saveAsJson(contacts, new File(file));
                break;
            default:
                System.out.println("Unrecognized format " + format);
                break;
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s, %s, %s, %s\n", contact.getFirstname(),
                        contact.getLastname(), contact.getMobilePhone(), contact.getEmail()));
            }
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            long leftLimit = 10000000001L;
            long rightLimit = 99999999999L;
            long randomNumber = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
            contacts.add(new ContactData()
                    .withFirstname("Henrik")
                    .withLastname(Integer.toString(i))
                    .withMobilePhone(String.format("+%s", Long.toString(randomNumber)))
                    .withEmail(String.format("Henrik_%s@google.com", i))
            );
        }
        return contacts;
    }
}
