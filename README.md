# EventStateProcessor Generator Plugin
[![Version](https://img.shields.io/jetbrains/plugin/v/:pluginId)](https://plugins.jetbrains.com/plugin/18106-event-state-processor-generator)

## Description

Event State Processor Generator plugin is compatible with [IntelliJ](https://www.jetbrains.com/idea/) and [Android Studio](https://developer.android.com/studio/). It provides source code generation for the [EventStateProcessor Library](https://github.com/extremevn/event_state_processor) to increase code productivity in [Flutter](https://flutter.dev/) apps development.

## Installation

You can find the plugin in the official IntelliJ and Android Studio [marketplace](https://plugins.jetbrains.com/)

##### Support version

Android Studio && IntelliJ IDEA version >= `2017.3`

## How to use

Simply right-click on the File Project view, go to `New -> Even State Processor Files`, give it a name and click on `OK` to see all the boilerplate generated.

Or using shortcut `Ctr + Alt + E + G`

### Snippets

#### Processor

| Shortcut                | Description                                                          |
| ----------------------- | -------------------------------------------------------------------- |
| `importesp`   	      | imports `import package:eventstateprocessor/eventstateprocessor.dart`|                         |
| `classprocessor`       | create a `$<Name>Processor` class                                     |
| `classevent`           | create a `$<Name>Event` class                                         |
| `classscreen`          | create a `$<Name>Screen` widget                                       |
| `classstate`           | create a `$<Name>State` class                                         |

### Issues and feedback
If there is specific issues, bugs, or feature requests please report to our [mailing list](https://groups.google.com/g/espg-group)

### Contributor
- [Justin Lewis](https://github.com/justin-lewis) (Maintainer)
- [dung95bk](https://github.com/dung95bk) (Developer)

### License
[MIT](LICENSE)
