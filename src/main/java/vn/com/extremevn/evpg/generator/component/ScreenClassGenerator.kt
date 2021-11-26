package vn.com.extremevn.evpg.generator.component

import vn.com.extremevn.evpg.generator.BaseGenerator

/**
 * A generator for screen widget
 */
class ScreenClassGenerator(
    name: String,
    exceptionName: String
) : BaseGenerator(name, templateName = "screen", exceptionName = exceptionName) {

    /**
     * @return physical file name of screen widget class
     */
    override fun fileName() = "${processorSnakeCase()}_screen.${fileExtension()}"
}