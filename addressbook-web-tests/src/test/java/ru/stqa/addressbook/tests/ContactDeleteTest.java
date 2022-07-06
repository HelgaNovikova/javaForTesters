package ru.stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase{

    @Test
    public void testContactDelete(){
        app.getNavigationHelper().goHome();
        app.getContactHelper().chooseContact();
        app.getContactHelper().deleteContact();
    }
}
