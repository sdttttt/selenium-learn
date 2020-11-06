package com.sdttttt

import kotlinx.coroutines.runBlocking
import org.openqa.selenium.chrome.ChromeDriver
import kotlin.system.exitProcess

fun main(args: Array<String>): Unit = runBlocking {

    var path: String

    if (args.size > 0) {
        path = args[0]
    } else {
        path = "classpath: chromedriver.exe"
    }

    val driver = ChromeDriver()
    BaiduSelenium(driver, path).runTest()

    exitProcess(0)
}