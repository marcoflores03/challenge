package org.challenge.web.tools;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {

    public static void setChromeDriver() throws Exception {
        String WINDOWS_PATH = "chromedriver_win32/chromedriver.exe";

        if (SystemUtils.IS_OS_WINDOWS) {
            System.setProperty("webdriver.chrome.driver",
                    WebDriverFactory.class.getClassLoader().getResource(WINDOWS_PATH).getPath());
        } else {
            throw new Exception("OS not supported");
        }
    }

    /**
     * The method used to get the desired Driver.
     * @param driver: the desired Driver. Values supported: "chrome".
     * @return An instance of the desired Driver to be used in the tests.
     */
    public static WebDriver getDriver(String driver) throws Exception {
        return getDriver(driver, null, null);
    }

    /**
     * The method used to get the desired Driver.
     * @param driver: the desired Driver. Values supported: "chrome".
     * @param proxyUrl: the proxy url to enter the environment where the tests will be executed. Format: URL:PORT.
     * @param noProxyUrl: URLs to be excluded from proxy server usage.
     * @return An instance of the desired Driver to be used in the tests.
     */
    public static WebDriver getDriver(String driver, String proxyUrl, String noProxyUrl) throws Exception {
        if (driver.equals("chrome")) {
            setChromeDriver();
            if (proxyUrl != null && noProxyUrl != null) {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                Proxy proxy = new Proxy();
                proxy.setHttpProxy(proxyUrl);
                proxy.setFtpProxy(proxyUrl);
                proxy.setSslProxy(proxyUrl);
                proxy.setSocksProxy(proxyUrl);
                proxy.setNoProxy(noProxyUrl);
                capabilities.setCapability("proxy", proxy);
                return new ChromeDriver(capabilities);
            } else {
                return new ChromeDriver();
            }
        } else if (driver.equals("chromeMobile")) {
            setChromeDriver();
            Map<String, String> mobileEmulation = new HashMap<>();
            //mobileEmulation.put("deviceName", "Nexus 5");
            //mobileEmulation.put("deviceName", "iPad");
            mobileEmulation.put("deviceName","iPhone X");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
            return new ChromeDriver(chromeOptions);
        } else {
            return null;
        }
    }
}