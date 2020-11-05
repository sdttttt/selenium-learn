package com.sdttttt

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

import java.util.concurrent.TimeUnit

fun main(args: Array<String>): Unit = runBlocking {

    if (args.size > 0) {
        System.setProperty("webdriver.chrome.driver", args[0])
    } else {
        System.setProperty("webdriver.chrome.driver", "D:\\GO study\\bin\\chromedriver.exe")
    }

    val driver = ChromeDriver()
    try {
        with(driver) {
            manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS)

            // 打开对应网址
            navigate().to("https://baidu.com")

            println("当前网址是：$currentUrl")
            println("当前标题是：$title")
            println("当前窗口句柄：$windowHandle")

            manage().window().position = Point(0,0)
            delay(Duration.ofSeconds(1).toMillis())

            manage().window().maximize()
            delay(Duration.ofSeconds(1).toMillis())

            // 在指定元素输入内容
            findElement(By.id("kw")).sendKeys("webdriver" + Keys.ENTER)
            // driver.findElement(By.cssSelector("input[type=submit]")).click()
            // findElement(By.id("su")).click()
            val baiduTab = windowHandle

            if (windowHandles.size == 1) {
                println("只打开了一个窗口")
            }

            delay(Duration.ofSeconds(1).toMillis())

            findElement(By.id("content_left"))
                .findElements(By.cssSelector("div.result.c-container.new-pmd")).forEach {
                    it
                        .findElement(By.cssSelector("h3 > a"))
                        .click()
                }

            delay(Duration.ofSeconds(1).toMillis())

            switchTo().newWindow(WindowType.TAB)

            delay(Duration.ofSeconds(1).toMillis())
            for (handle in windowHandles) {
                if (baiduTab.contentEquals(handle)) {
                    switchTo().window(baiduTab)
                }
            }

            delay(Duration.ofSeconds(1).toMillis())

            windowHandles.forEach {
                delay(Duration.ofSeconds(1).toMillis())
                switchTo().window(it).close()
            }

            delay(Duration.ofSeconds(1).toMillis())
        }
    } catch (e: Exception) {
        println(e.message)
    } finally {
        driver.quit()
    }
}


fun numberOfWindowsToBe(): (WebDriver) -> Boolean = { it.windowHandles.size > 1 }
