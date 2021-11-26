package vn.com.extremevn.evpg.generator

import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import com.fleshgrinder.extensions.kotlin.toUpperCamelCase
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader

/**
 * A generator allow you to load template file and replace all variable in it with specify value
 */
abstract class BaseGenerator(
    private val name: String,
    exceptionName: String,
    templateName: String
) {

    companion object {
        const val TEMPLATE_PROCESSOR_PASCAL_CASE = "processor_pascal_case"
        const val TEMPLATE_PROCESSOR_SNAKE_CASE = "processor_snake_case"
        const val TEMPLATE_EXCEPTION_NAME = "exception_name_pascal_case"
    }

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
            TEMPLATE_PROCESSOR_PASCAL_CASE to processorPascalCase(),
            TEMPLATE_PROCESSOR_SNAKE_CASE to processorSnakeCase(),
            TEMPLATE_EXCEPTION_NAME to exceptionName
        )
        try {
            val templateFolder = "dart"
            val resource = "/templates/$templateFolder/$templateName.dart.template"
            val resourceAsStream = BaseGenerator::class.java.getResourceAsStream(resource)
            templateString = InputStreamReader(resourceAsStream!!, Charsets.UTF_8).readText()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    /**
     * Get physical file name of generated file
     *
     * @return [name] of generated file
     */
    abstract fun fileName(): String

    /**
     * Get filename extension
     */
    fun fileExtension() = "dart"

    /**
     * Replace all variable in template file with specify value
     *
     * @return string as source code content of template file
     */
    fun generate(): String = StrSubstitutor(templateValues).replace(templateString)

    /**
     * Format this input [name] in **lower_snake_case** and remove all "_processor" substring
     *
     * @return new [name] after formatted as **lower_snake_case**
     */
    protected fun processorSnakeCase() = name.toLowerSnakeCase().replace("_processor", "")

    /**
     * Format this input [name] in **upper_camel_case** and remove all "Processor" substring
     *
     * @return new [name] after formatted as **upper_camel_case**  formatted
     */
    private fun processorPascalCase(): String = name.toUpperCamelCase().replace("Processor", "")
}