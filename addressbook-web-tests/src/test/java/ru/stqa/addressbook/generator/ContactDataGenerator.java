package ru.stqa.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "contacts count")
    public int count;

    @Parameter(names = "-f", description = "file with contact data")
    public String file;

    @Parameter(names = "-d", description = "data format")
    public String format;

    public static void main(String args[]) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jcom = new JCommander(generator);
        try {
            jcom.parse(args);
        } catch (ParameterException ex) {
            jcom.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveContactsAsCSV(contacts, new File(file));
        } else if (format.equals("json")) {
            saveContactsAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format");
        }
    }

    private void saveContactsAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveContactsAsCSV(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getEmail(),
                    contact.getEmail2(), contact.getEmail3(), contact.getHomePhoneNumber()));
        }
        writer.close();
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName("genFirstName" + i).withLastName("genLastName" + i)
                    .withEmail("genEmail" + i).withEmail2("genEmail2 " + i).withEmail3("genEmail3 " + i)
                    .withHomePhoneNumber("345345"));
        }
        return contacts;
    }
}
