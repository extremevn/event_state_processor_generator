package vn.com.extremevn.evpg.generator.component

import vn.com.extremevn.evpg.generator.BaseGenerator

/**
 * A generator for state class
 */
class StateClassGenerator(
    name: String,
    exceptionName: String
) : BaseGenerator(name, templateName = "state", exceptionName = exceptionName) {

    /**
     * @return physical file name of state class
     */
    override fun fileName() = "${processorSnakeCase()}_state.${fileExtension()}"
}