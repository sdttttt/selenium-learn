package com.sdttttt

import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.WebDriver

class MG4399(driver: WebDriver, driverPath: String): AbstractSelenium(driver, driverPath) {

    override val logger: Logger = LogManager.getLogger(this::class)

    override fun runTest(): Unit = runBlocking {
        with(driver) {
            navigate().to("https://cz.4399.com/")
            logger.bigInfo("当前网址是：$currentUrl", "当前标题是：$title", "窗口句柄: $windowHandle")

        }
    }
}