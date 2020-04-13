package com.automation.tests;

import com.automation.page.LoginPage;
import com.automation.page.activities.CalendarEventsPage;
import com.automation.utilities.DateTimeUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Homework5 extends AbstractTestBase {
    private WebDriver driver = Driver.getDriver();
    @Test
    public void TestCase1(){

        test = report.createTest("Verify that “view”, “edit” and “delete” options are available");

        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");

        calenderEventsPage.moveTo();
        test.info("Login as store manager");//log some steps
        calenderEventsPage.viewEditDeleteBtn();

        test.pass("the button was verified");

    }

    @Test
    public void TestCase2(){
        test = report.createTest("Verify that “Title” column still displayed");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickGridBtn();
        assertEquals(calenderEventsPage.selectAllGrid(), "Title");
        test.pass("The 'Title' column displayed");
    }


    @Test
    public void TestCase3(){
        test = report.createTest("Verify that “Title” column still displayed");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();
        calenderEventsPage.saveAndCloseBtn();
        calenderEventsPage.checkSaveOptions();
        test.pass("The  “Save And Close”, “Save And New”\n" +
                "and “Save” options are available");
    }

    @Test
    public void TestCase4(){
        test = report.createTest("Verify that “All Calendar Events” page subtitle is\n" +
                "displayed");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();
        calenderEventsPage.clickOnCancelBtn();

        assertTrue(driver.findElement(By.className("oro-subtitle")).isDisplayed());
        test.pass("the title verified");
    }

    @Test
    public void TestCase5() {
        test = report.createTest("Verify that difference between end and start time is exactly 1 hour");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();

        String startTime = calenderEventsPage.getStartTime();
        String endTime= calenderEventsPage.getEndTime();
        String format = "h:m a";

        long actual = DateTimeUtilities.getTimeDifference(startTime,endTime,format);
        assertEquals(actual, 1, "Test fail");

        test.pass("Time differences verified");
    }

    @Test
    public void TestCase7() {
        test = report.createTest("Verify date and time input are displayed");
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();
        calenderEventsPage.clickOnAllDayEvent();
        String startDate = "[id^='date_selector_oro_calendar_event_form_start']";
        String endDate = "[id^='date_selector_oro_calendar_event_form_end']";

        assertTrue(driver.findElement(By.cssSelector(startDate)).isDisplayed());
        assertTrue(driver.findElement(By.cssSelector(endDate)).isDisplayed());

        String startTime = "[id^='time_selector_oro_calendar_event_form_start']";
        String endTime = "[id^='time_selector_oro_calendar_event_form_end']";
        assertTrue(driver.findElement(By.cssSelector(startTime)).isEnabled());
        assertTrue(driver.findElement(By.cssSelector(endTime)).isEnabled());

        test.pass("Input boxes verified");
    }

    @Test
    public void TestCase8() {
        test = report.createTest("Default options is Daily" );
        LoginPage loginPage = new LoginPage();
        CalendarEventsPage calenderEventsPage = new CalendarEventsPage();

        loginPage.login();
        calenderEventsPage.navigateTo("Activities", "Calendar Events");
        calenderEventsPage.clickToCreateCalendarEvent();
        calenderEventsPage.clickOnRepeatBtn();
        assertEquals(calenderEventsPage.getDailyDefaultOption(), "Daily");
        test.pass("Default options is verified");
    }


}
