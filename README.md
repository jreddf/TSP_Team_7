On first pull, you might have to wait for gradle to build. after this in the terminal (if youre using intellij) you need to run
"./gradlew genSources"

After this, go to ExampleMixin, right click on MinecraftServer and goto definition. 
If a blue bar comes up saying to choose souces, click on it and select the jar that has -sources at the end.
