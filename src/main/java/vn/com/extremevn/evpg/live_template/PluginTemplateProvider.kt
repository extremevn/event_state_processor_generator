package vn.com.extremevn.evpg.live_template

import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider

/**
 * Live template resource provider
 */
class PluginTemplateProvider : DefaultLiveTemplatesProvider {
    override fun getDefaultLiveTemplateFiles(): Array<String> {
        return arrayOf("liveTemplates/esp")
    }

    override fun getHiddenLiveTemplateFiles(): Array<String>? {
        return null
    }
}
