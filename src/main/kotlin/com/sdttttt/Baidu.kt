package com.sdttttt

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.*
import java.time.Duration
import java.util.concurrent.TimeUnit

class Baidu(driver: WebDriver, driverPath: String) : AbstractSelenium(driver, driverPath) {

    override val logger: Logger = LogManager.getLogger(this::class)

    override fun runTest(): Unit = runBlocking {
            with(driver) {
                manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS)

                // 打开对应网址
                navigate().to("https://baidu.com")
                logger.info("""

                    当前网址是：$currentUrl
                    当前标题是：$title
                
                    窗口句柄: $windowHandle
                """)

                manage().window().position = Point(0,0)
                delay(Duration.ofSeconds(1).toMillis())

                logger.info("最大化窗口.")
                manage().window().maximize()
                delay(Duration.ofSeconds(1).toMillis())

                // 在指定元素输入内容
                findElement(By.id("kw")).sendKeys("Baidu", Keys.ENTER)
                logger.info("输入关键词, 回车.")
                // driver.findElement(By.cssSelector("input[type=submit]")).click()
                // findElement(By.id("su")).click()
                val baiduTab: String = windowHandle

                if (windowHandles.size == 1) {
                    logger.info("只打开了一个窗口")
                }

                stop(1)

                logger.info("找到所有搜索到的网页, 一个个打开...")
                findElement(By.id("content_left"))
                    .findElements(By.cssSelector("div.result.c-container.new-pmd")).forEach {
                        it
                            .findElement(By.cssSelector("h3 > a"))
                            .click()
                    }
                logger.info("打开完成了.")
                delay(Duration.ofSeconds(1).toMillis())

                switchTo().newWindow(WindowType.TAB)
                logger.info("打开一个空白窗口.")

                delay(Duration.ofSeconds(1).toMillis())
                for (handle in windowHandles) {
                    if (baiduTab.contentEquals(handle)) {
                        switchTo().window(baiduTab)
                    }
                }

                delay(Duration.ofSeconds(1).toMillis())

                logger.info("一个个关闭所有的窗口...")
                windowHandles.forEach {
                    delay(Duration.ofSeconds(1).toMillis())
                    with(switchTo().window(it)){
                        logger.info("关闭 **${title}.**")
                        close()
                    }
                }
                logger.info("关好了.")

                delay(Duration.ofSeconds(1).toMillis())
            }
    }

}