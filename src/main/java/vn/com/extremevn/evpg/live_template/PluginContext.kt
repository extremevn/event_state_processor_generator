package vn.com.extremevn.evpg.live_template

import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiFile

/**
 * Live template context
 */
class PluginContext : TemplateContextType("esp", "EventStateProcessor Generator") {
    override fun isInContext(file: PsiFile, offset: Int): Boolean {
        return file.name.endsWith(".dart")
    }
}