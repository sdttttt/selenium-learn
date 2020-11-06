package com.sdttttt

import kotlinx.coroutines.runBlocking
import org.apache.logging.log4j.Logger
import org.junit.Test
import org.openqa.selenium.chrome.ChromeDriver
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val path: String = if (args.size > 0) {
        args[0]
    } else {
        "classpath: chromedriver.exe"
    }

    val driver = ChromeDriver()
    // Baidu(driver, path).run()
    if (MG4399(driver, path).run()) {
        exitProcess(0)
    } else {
        exitProcess(1)
    }
}