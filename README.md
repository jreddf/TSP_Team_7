On first pull, you might have to wait for gradle to build. after this in the terminal (if youre using intellij) you need to run
"./gradlew genSources"

If this doesn't work:
First, make sure to get gradle installed. Install it from here if you don't already have it: https://gradle.org/install/

Next, run gradle wrapper. It will build gradle-wrapper.jar, which you need to use gradlew.

After this, go to rogue/src/main/net.tsp7.rogue/mixin/ExampleMixin, right click on MinecraftServer and goto definition. 
If a blue bar comes up saying to choose souces, click on it and select the jar that has -sources at the end.

Quick document that has pretty much everything I learned so far
https://docs.google.com/document/d/1XlwSoextRz6X9zyci4uK_fufg9JzNh88EcK1zGgEWEA/edit?tab=t.0
