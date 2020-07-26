package org.challenge;

import org.challenge.general.DateManager;
import org.challenge.general.JsonHelper;
import org.challenge.web.tools.WebDriverFactory;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.Fail;
import org.challenge.dataSet.EnvDataSet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.*;
import org.testng.ITestResult;
import java.io.File;

public class BaseTest {

    private String env;
    public EnvDataSet environment;
    private String browser;
    public WebDriver driver = null;

    @Parameters({"theEnv", "theDriver"})
    @BeforeClass
    public void generalTestSetup(@Optional String theEnv, @Optional String theDriver){

        if (theEnv!=null){
            env = theEnv;
        } else {
            env = "qa";
        }

        if (theDriver!=null){
            browser = theDriver;
        } else {
            browser = "chrome";
        }
        try {
            final JsonHelper helper = new JsonHelper();
            environment = (EnvDataSet) helper.getDataSetFromJsonFile("envs.json", "environment", env);
        } catch (final NullPointerException e) {
            final String warningText = "WARNING!. Users file not found in Test Resources folder, please check it.";
            Reporter.log(warningText);
            Fail.fail(warningText);
        }
    }

    @BeforeMethod
    public void setDriver() {
        driver = this.getDriver();
    }

    public WebDriver getDriver() {
        WebDriver webDriver = null;
        try {
            webDriver = WebDriverFactory.getDriver(browser);
            webDriver.manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webDriver;
    }

    @AfterMethod
    public void invokeScreenshotOnFailure(ITestResult res) {
        if (driver != null) {
            if (ITestResult.FAILURE == res.getStatus()) {
                String id = DateManager.getDateId();
                try {
                    TakesScreenshot ts = (TakesScreenshot) driver;
                    File src = ts.getScreenshotAs(OutputType.FILE);
                    String fileName = "./test-output/html/screenshots/" + res.getName() + "-" + id + ".png";
                    FileUtils.copyFile(src, new File(fileName));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            driver.close();
        }
    }
}