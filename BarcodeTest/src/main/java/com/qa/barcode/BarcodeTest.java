package com.qa.barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BarcodeTest {

    WebDriver driver;



    @BeforeMethod
    public void beforeMethod() {

        System.setProperty("webdriver.chrome.driver", "/Users/parthhansalia/Documents/MyProjects/chromedriver");
         driver = new ChromeDriver();

    }


    @Test
    public void barcodeTest() throws IOException, NotFoundException {


        driver.get("https://barcode.tec-it.com/en/?data=Hi Parth Hansalia Here");

        String barcodeURL = driver.findElement(By.tagName("img")).getAttribute("src");

        System.out.println("Barcode URL - "+barcodeURL);

        URL url = new URL(barcodeURL);

        BufferedImage bufferedImage = ImageIO.read(url);


        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);

        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

        Result result = new MultiFormatReader().decode(binaryBitmap);

        System.out.println("Decode Barcode Value - " + result.getText());



    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }




}
