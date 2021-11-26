package vn.com.extremevn.evpg.generator.component

import vn.com.extremevn.evpg.generator.BaseGenerator

/**
 * A generator for event class
 */
class EventClassGenerator(
    name: String, exceptionName: String
) : BaseGenerator(name, templateName = "event", exceptionName = exceptionName) {

    /**
     * @return physical file name of event class
     */
    override fun fileName() = "${processorSnakeCase()}_event.${fileExtension()}"
}