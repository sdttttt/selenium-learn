package com.sdttttt

import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebDriver
import kotlin.math.log

class MG4399(driver: WebDriver, driverPath: String): AbstractSelenium(driver, driverPath) {

    override val logger: Logger = LogManager.getLogger(this::class)

    override fun runTest(): Unit = runBlocking {
        with(driver) {
            navigate().to("https://cz.4399.com/")

            logger.bigInfo("当前网址是：$currentUrl", "当前标题是：$title", "窗口句柄: $windowHandle")
            val selectGameButton = findElement(By.cssSelector("input[value='选择游戏']"))
            stop(1)

            logger.info("点击选择游戏按钮.")
            selectGameButton.click()
            stop(2)

            val gameList = findElement(By.cssSelector("div#allgame_list_click > ul#freegame"));
            gameList.findElement(By.cssSelector("li > label[for='g10029'] > img")).click()
            logger.info("选择bqyx.")
            stop(2)

            findElement(By.cssSelector("input[name='pay_user'][type='text']"))
                .sendKeys("sdttttt"+ Keys.TAB + "sdttttt")
            logger.info("输入用户名, 然后换格, 再次输入用户名")
            stop(2)

            findElement(By.cssSelector("input[type='radio'][id='p_wxewm500']")).click()
            logger.info("选择金额, 我选了500块.")
            stop(2)

            findElement(By.cssSelector("div.btn_dv > input[type='button'][name='subbut']")).click()
            logger.info("然后充值.")
            stop(3)

            logger.info("这里他就等我扫码了, 接下来就不演示了")
            stop(5)
            close()
        }
    }
}