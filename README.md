# Erklärung von Mock-Objekten anhand einer Mietverwaltungssoftware

## Start
* IntelliJ
* JDK 11
* Gradle Dependencies installieren
* Erster Start über _Gradle > MockMieter > Tasks > application > run_

### Lombok
Das Projekt nutzt zur besseren Übersichtlichkeit [Lombok](https://projectlombok.org/). Von daher sollte sichergestellt werden, dass IntelliJ das [Lombok-Plugin](https://projectlombok.org/setup/intellij) aktiviert und der Annotation Processor aktiviert ist: _Preferences > Build, Execution, Deployment > Compiler > Annotation Processors > Enable annotation processing box + Obtain processors from project classpath_

## Troubleshooting
> Class has been compiled by a more recent version of the Java Environment

Stelle sicher, dass über _Settings > Build, Execution, Deployment > Build Tools > Gradle_ die GradleJVM mit der Projekt-JDK übereinstimmt.
