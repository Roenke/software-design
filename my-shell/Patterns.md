List of used design patterns:
* Strategy. The `CommandBase` with implementations: `AssignCommand, CatCommand, EchoCommand etc.`
* Factory. `CommandFactory`
* Singleton. `EnvironmentImpl, WeakQuote$MyWeakQuotePool`
* Wrapper. `ArgumentSubstitutionWrapper`
* Composition. `CompositeQuote, PipeHandler`
* Flyweight. `WeakQuote`
* Factory method `WeakQuote`
* Adapter `WeakQuote$MyWeakQuotePool, CommandArg`
* Lazy initialization `EnvironmentImpl, WeakQuote$MyWeakQuotePool`
* Object pool `WeakQuote$MyWeakQuotePool`
* Command `Executable`
* Interpreter `ReadEvalPrintLoop`
* Null object `CommandFactory.DEFAULT_COMMAND_CLASS`
* Chain-of-responsibility `PipeHandler`
* Proxy `CommandArg`

The complete list of the software design patterns: https://en.wikipedia.org/wiki/Software_design_pattern
