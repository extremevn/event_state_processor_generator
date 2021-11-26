package vn.com.extremevn.evpg.generator.component

import vn.com.extremevn.evpg.generator.BaseGenerator

/**
 * A generator for processor class
 */
class ProcessorClassGenerator(
    name: String,
    exceptionName: String
) : BaseGenerator(name, templateName = "processor", exceptionName = exceptionName) {

    /**
    * @return physical file name of processor class
     */
    override fun fileName() = "${processorSnakeCase()}_processor.${fileExtension()}"
}