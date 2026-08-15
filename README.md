# Maven Lifecycle Demo

A Java 25 project built to practice the **Maven default build lifecycle**
(validate → compile → test → package → verify → install → deploy), packaged
as a deployable **WAR**, with a small styled front end — "Blueprint Garage",
a spec-sheet style car showcase — served as the app's my jenkins pageee .

## Project structure

```
maven-lifecycle-demo/
├── pom.xml
├── README.md
├── .gitignore
└── src
    ├── main
    │   ├── java/com/practice/calculator/
    │   │   ├── App.java          # plain Java class, kept for lifecycle practice
    │   │   └── Calculator.java   # simple logic to compile/test
    │   ├── resources/
    │   └── webapp/                        <-- new: the WAR's web content
    │       ├── index.html                 # Blueprint Garage car showcase UI
    │       └── WEB-INF/web.xml            # deployment descriptor
    └── test
        └── java/com/practice/calculator/
            └── CalculatorTest.java  # JUnit 5 tests
```

## Packaging: JAR → WAR

The `pom.xml` now declares `<packaging>war</packaging>` instead of `jar`.
That single change means:
- `maven-war-plugin` replaces `maven-jar-plugin` for the `package` phase — it
  bundles everything under `src/main/webapp` plus your compiled classes into
  a `.war` file, ready to drop into a servlet container.
- A `jakarta.servlet-api` dependency was added with `scope=provided` — needed
  to *compile* against servlet classes, but supplied by the container
  (Tomcat/Jetty) at *runtime*, so it isn't bundled into the WAR itself.
- `src/main/webapp/WEB-INF/web.xml` is the deployment descriptor every WAR
  needs — it tells the container the app's name and its welcome page.

## Requirements

- JDK 25 installed and on your PATH (`java -version` should show 25)
- Maven 3.9+ (`mvn -version`)

## Phase-by-phase practice

Run these one at a time and watch which plugin fires for each phase —
matches the diagram exactly:

| Command | Phase | Plugin used | What happens |
|---|---|---|---|
| `mvn validate` | validate | — | Checks the project is correct, `pom.xml` is well-formed |
| `mvn compile` | compile | maven-compiler-plugin | Compiles `src/main/java` → `target/classes` |
| `mvn test` | test | maven-surefire-plugin | Runs `CalculatorTest` |
| `mvn package` | package | maven-war-plugin | Builds `target/maven-lifecycle-demo.war` |
| `mvn verify` | verify | (surefire runs again + checks) | Runs any integration checks |
| `mvn install` | install | maven-install-plugin | Copies the war into your local `~/.m2/repository` |
| `mvn deploy` | deploy | maven-deploy-plugin | Would push to a remote repo (disabled here — see note below) |

Remember: running a later phase **automatically runs every phase before it**.
So `mvn install` alone will validate → compile → test → package → verify →
install in one shot. Try running each phase individually first, then run
`mvn install` and watch it chain through all of them.

### Extra commands worth practicing

```bash
mvn clean                 # deletes target/ (built-in "clean" lifecycle)
mvn clean install         # clean, then run the whole default lifecycle
mvn compile -X            # verbose/debug output, see plugin resolution
mvn dependency:tree       # visualize dependencies
mvn install:install-file -Dfile=<jar> -DgroupId=... -DartifactId=... -Dversion=... -Dpackaging=jar
```

### Run the packaged app

**Option A — quick local run, no separate server install:**
```bash
mvn clean jetty:run
```
Then open **http://localhost:8080/** — you'll see the Blueprint Garage
showcase page.

**Option B — deploy the WAR to Tomcat (the classic way):**
```bash
mvn clean package
```
Then copy the resulting `target/maven-lifecycle-demo.war` into Tomcat's
`webapps/` folder, start Tomcat, and open
`http://localhost:8080/maven-lifecycle-demo/`.

## Note on `deploy`

The `deploy` phase is set to `skip=true` in `pom.xml` since there's no
remote repository (like Nexus/Artifactory or GitHub Packages) configured
yet. If you want to practice a *real* deploy, you can:

1. Set up a free [GitHub Packages](https://docs.github.com/en/packages) Maven repo, or
2. Point `<distributionManagement>` in `pom.xml` at your own Nexus/Artifactory instance,

then remove `<skip>true</skip>` and run `mvn deploy`.

## Pushing this to GitHub

```bash
cd maven-lifecycle-demo
git init
git add .
git commit -m "Initial commit: Maven lifecycle practice project"
git branch -M main
git remote add origin https://github.com/<your-username>/maven-lifecycle-demo.git
git push -u origin main
```
