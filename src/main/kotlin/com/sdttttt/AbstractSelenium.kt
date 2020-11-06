package com.sdttttt

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.Logger
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.time.Duration

abstract class AbstractSelenium(protected open val driver: WebDriver, protected open val driverPath: String) {

    protected abstract val logger: Logger

    fun beforeCheck(): Boolean {

        logger.bigInfo("OS: ${System.getProperty("os.name")}", "ARCH: ${System.getProperty("os.arch")}")

        if (driver is ChromeDriver) {
            if (System.getProperty("os.name").startsWith("Windows")) {
                val driverType = "webdriver.chrome.driver"
                val driverPath = "D:\\GO study\\bin\\chromedriver.exe"
                logger.info("""
            
                    TYPE: $driverType
                    PATH: $driverPath
            """)
                System.setProperty(driverType, driverPath)
                return true
            } else {
                logger.bigError("当前不支持Linux.")
            }
        } else {
            logger.bigError("当前不支持Chrome以外的WebDriver.")
        }
        return false
    }

    abstract fun runTest(): Unit

    fun run(): Boolean {
        if (!beforeCheck()) return false
        try {
            runTest()
        } catch (e: Exception) {
            logger.error("""
                
                    沃日, 出错了: ${e.message}    
            """)
            return false
        } finally {
            driver.quit()
            logger.info("退出浏览器.")
        }

        logger.info("测试结束.")
        return true
    }

    fun stop(seconds: Long): Unit = runBlocking{
        delay(Duration.ofSeconds(seconds).toMillis())
    }

    fun Logger.bigInfo(vararg args: String) {
        if (args.size < 1) return
        var message: String = ""
        args.forEach {
            message += it + "\n                    "
        }
        info("""
       
                    $message""")
    }

    fun Logger.bigError(vararg args: String) {
        if (args.size < 1) return
        var message: String = ""
        args.forEach {
            message += it + "\n                    "
        }
        error("""
       
                    $message""")
    }

}