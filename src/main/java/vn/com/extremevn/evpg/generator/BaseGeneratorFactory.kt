package vn.com.extremevn.evpg.generator

import vn.com.extremevn.evpg.generator.component.EventClassGenerator
import vn.com.extremevn.evpg.generator.component.ProcessorClassGenerator
import vn.com.extremevn.evpg.generator.component.ScreenClassGenerator
import vn.com.extremevn.evpg.generator.component.StateClassGenerator

/**
 * A Factory that provide list of source code generator
 */
object BaseGeneratorFactory {
    /**
     * Build a list of source code generator with specify screen [name]
     * @param name The name of this screen
     *
     * @return list of [BaseGenerator] with input screen name
     */
    fun buildGenerators(name: String, exceptionName: String): List<BaseGenerator> {
        val bloc = ProcessorClassGenerator(name, exceptionName)
        val event = EventClassGenerator(name, exceptionName)
        val state = StateClassGenerator(name, exceptionName)
        val screen = ScreenClassGenerator(name, exceptionName)
        return listOf(bloc, event, state, screen)
    }
}