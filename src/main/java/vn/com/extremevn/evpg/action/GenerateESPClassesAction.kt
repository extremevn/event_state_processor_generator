package vn.com.extremevn.evpg.action

import com.intellij.lang.java.JavaLanguage
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import vn.com.extremevn.evpg.generator.BaseGenerator
import vn.com.extremevn.evpg.generator.BaseGeneratorFactory

/**
 * Action that generate source code files for a screen: state class, event class, processor class, screen class
 */
class GenerateESPClassesAction : AnAction(), GenerateESPClassesDialog.Listener {

    /**
     * Allows an action to retrieve information about the context in which it was invoked.
     */
    private lateinit var dataContext: DataContext

    /**
     * Called when user click [New -> Even State Processor Files]
     * Show A [GenerateESPClassesDialog] for name setting
     */
    override fun actionPerformed(e: AnActionEvent) {
        val dialog = GenerateESPClassesDialog(this)
        dialog.show()
    }

    /**
     * Called when user click `ok` in [GenerateESPClassesDialog]
     * Create list of generator from [BaseGeneratorFactory] for [generate] corresponding file
     * @param name input string
     */
    override fun onGenerateBlocClicked(name: String?, isCreateFolder: Boolean, exceptionName: String) {
        name?.run {
            val generators = BaseGeneratorFactory.buildGenerators(this, exceptionName)
            generate(this, generators, isCreateFolder)
        }
    }

    /**
     * Init [dataContext] variable
     */
    override fun update(e: AnActionEvent) {
        e.dataContext.run {
            dataContext = this
            val presentation = e.presentation
            presentation.isEnabled = true
        }
    }

    /**
     * Generate source files
     * @param sourceGenerators list of source file generators
     */
    private fun generate(name: String, sourceGenerators: List<BaseGenerator>, isCreateFolder: Boolean) {
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val view = LangDataKeys.IDE_VIEW.getData(dataContext)
        val directory = view?.orChooseDirectory ?: return
        ApplicationManager.getApplication().runWriteAction {
            CommandProcessor.getInstance().executeCommand(
                project,
                {
                    if (isCreateFolder) {
                        val subDirectoryName = createDirectoryName(name.toLowerCase(), directory) ?: return@executeCommand
                        val subDirectory = directory.createSubdirectory(subDirectoryName.toLowerCase())
                        sourceGenerators.forEach { createSourceFile(project!!, it, subDirectory) }
                    } else {
                        sourceGenerators.forEach { createSourceFile(project!!, it, directory) }
                    }
                },
                "Generate Even State Processor source files",
                null
            )
        }
    }

    /**
     * Check for existence of directory name, if existed then add suffix number to directory name otherwise return folder name in lowercase
     * @param name directory name to check
     * @param directory parent directory of directory to check
     * @param suffix suffix of directory name to check
     */
    private fun createDirectoryName(name: String, directory: PsiDirectory, suffix: Int = -1): String? {
        val existingSubDirectory = directory.findSubdirectory("$name${if (suffix == -1) "" else suffix.toString()}")
        return if (existingSubDirectory != null) {
            val newSuffix: Int = when (suffix) {
                100 -> {
                    return null
                }
                else -> {
                    suffix + 1
                }
            }
            createDirectoryName(name, directory, newSuffix)
        } else {
            if (suffix == -1) {
                name
            } else {
                "$name${suffix}"
            }
        }
    }

    /**
     * Write physical source file in directory
     * @param project current project in which file are generated
     * @param generator for generate source file content
     * @param directory directory which file will be written to
     */
    private fun createSourceFile(project: Project, generator: BaseGenerator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val existingPsiFile = directory.findFile(fileName)
        if (existingPsiFile != null) {
            PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)?.run {
                insertString(textLength, "\n" + generator.generate())
            }
        } else {
            directory.add(
                PsiFileFactory.getInstance(project)
                    .createFileFromText(fileName, JavaLanguage.INSTANCE, generator.generate())
            )
        }
    }
}