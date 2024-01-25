# Decisions

* Following the clean code architecture for readability, re-usability and testability.
* Using MVVM design pattern.
* Retrofit for calling api.
* Coil for loading images as it's more compatible with kotlin and it has a better performance.
* LeakCanary for memory leak tracks.
* Jetpack compose for UI as it's more compatible with Kotlin, I can implement the same with xml too if needed.
* Coroutines with Flow: more compatible with Kotlin and easier to learn than RX.
* DaggerHilt : for the same reasons of Coroutines about easy implementation and it's easier to implement than Dagger 2 and better than Koin as Koin build the generated classes on the run time but Coroutines generates them in the build time.
* I've mapped the data from data layer to be ui model according to the requirements to have just one view for the list when the data is fetched, I know where is that requirement coming from (xml listview/recyclerview which handles the viewholder there according to the index or the class type of the binding view there)
* Kotlin DSL
    - Syntax: The syntax of a build.gradle.kts file is in Kotlin, which is a statically-typed programming language that runs on the Java Virtual Machine (JVM). This allows you to take advantage of Kotlin language features when defining your build script.
    - Type Safety: One of the main benefits of using Kotlin for build scripts is the increased type safety. Kotlin is a statically-typed language, which means that the compiler can catch certain types of errors before runtime, providing better reliability and code completion support in IDEs.
    - Extension: The .kts extension indicates that the file contains Kotlin script code. This is in contrast to the traditional build.gradle files, which are written in Groovy.
    - Gradle DSL (Domain-Specific Language): The build script is written in a DSL provided by Gradle. This DSL allows you to define tasks, dependencies, plugins, and other aspects of your build configuration.

# Testing Strategies:
- The happy scenario : Stable internet, open the app spinner for loading will be shown up till the data is fetched from the api then the list of recipes will be shown and the spinner will disappear.
- No internet : Just disconnect your internet on the device and the loading api will be called and then there'll be a returned error which represents that no internet.
- Slow internet till getting timeout: on Emulator you can customize the power of the internet so you can slow it as much as you can and from there you'll see a timeout error on the app.
- Unknown error : any other error type is considered as Unknown error.


#### As an extra point here I've implemented a behavior to control the dark and light theme.