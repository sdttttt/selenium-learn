package com.sdttttt

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

import kotlinx.coroutines.*
import org.openqa.selenium.WindowType
import org.openqa.selenium.support.ui.WebDriverWait

fun main(args: Array<String>) {
    runBlocking {

        if (args.size > 0) {
            System.setProperty("webdriver.chrome.driver", args[0])
        } else {
            System.setProperty("webdriver.chrome.driver", "D:\\GO study\\bin\\chromedriver.exe")
        }


        val driver: WebDriver = ChromeDriver()

        val wait = WebDriverWait(driver, Duration.ofSeconds(2))

        try {
            with(driver) {
                // 打开对应网址
                navigate().to("https://baidu.com")

                println("当前网址是：$currentUrl")
                println("当前标题是：$title")
                println("当前窗口句柄：$windowHandle")

                // 在指定元素输入内容
                findElement(By.id("kw")).sendKeys("Hello World")
                // driver.findElement(By.cssSelector("input[type=submit]")).click()
                findElement(By.id("su")).click()
                Duration.ofSeconds(5).toMillis()
                Duration.ofSeconds(5).toMillis()


                if (windowHandles.size == 1) {
                    println("只打开了一个窗口")
                }

                switchTo().newWindow(WindowType.TAB)

                wait.until(numberOfWindowsToBe())
                delay(Duration.ofSeconds(5).toMillis())
            }
        } catch (e: Exception) {
            println(e.message)
        } finally {
            driver.quit()
        }
    }
}

fun numberOfWindowsToBe(): (WebDriver) -> Boolean = { it.windowHandles.size > 1 }
