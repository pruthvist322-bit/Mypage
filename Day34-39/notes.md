# Day 34–39 – Jenkins & CI/CD Pipeline

## Overview

During Day 34 to Day 39 of my DevOps learning journey, I learned about **Jenkins** and how it can be used to automate the Continuous Integration and Continuous Deployment (CI/CD) process.

I also performed a complete hands-on CI/CD demonstration by integrating Git, GitHub, Jenkins, Maven, and Apache Tomcat.

---

## What is Jenkins?

Jenkins is an open-source automation server used to automate software development processes.

It is widely used for implementing **CI/CD pipelines**.

Jenkins can automatically:

* Pull code from GitHub
* Build the application
* Run tests
* Package the application
* Deploy the application

---

## Why is Jenkins Used?

Jenkins helps automate repetitive development and deployment tasks.

Instead of manually building and deploying an application every time code changes, Jenkins can automatically perform these activities.

### Benefits of Jenkins

* Automation
* Continuous Integration
* Continuous Deployment
* Easy integration with GitHub
* Supports Maven and many other build tools
* Large plugin ecosystem
* Reduces manual deployment effort

---

## Installing Jenkins

Learned the steps required to install Jenkins on a Linux/AWS EC2 machine.

The general process included:

1. Install Java
2. Configure the Jenkins repository
3. Install Jenkins
4. Start the Jenkins service
5. Enable Jenkins to start automatically
6. Access Jenkins through the browser
7. Complete the initial Jenkins setup

After installation, Jenkins can be accessed using its server IP and configured port.

---

## Configuring Jenkins Jobs

Learned how Jenkins jobs are used to automate tasks.

### Steps Practiced

* Create a new Jenkins job
* Select the required job type
* Configure the source code repository
* Connect Jenkins with GitHub
* Configure build steps
* Save the job
* Build the job
* Check the build result and console output

---

## Connecting Jenkins with GitHub

Learned how Jenkins can pull source code from a GitHub repository.

The basic flow is:

```text
Developer
    ↓
Git
    ↓
GitHub
    ↓
Jenkins
    ↓
Maven
    ↓
Apache Tomcat
```

---

## GitHub Webhooks

Learned how **webhooks** can be used to automatically notify Jenkins when changes are pushed to GitHub.

Instead of manually triggering a Jenkins build after every code change, GitHub can send a notification to Jenkins.

### Basic Flow

```text
Code Change
     ↓
Git Commit
     ↓
Git Push
     ↓
GitHub
     ↓
Webhook
     ↓
Jenkins
     ↓
Build
```

This helps automate the Continuous Integration process.

---

## Cloning Git Repository

Learned how Jenkins can clone a GitHub repository and retrieve the latest source code.

The Git repository contains the application source code required for the build process.

---

## Adding Build Steps

Learned how to configure build steps inside a Jenkins job.

Maven was used to build the Java application.

The general process was:

```text
GitHub Repository
       ↓
     Jenkins
       ↓
     Maven
       ↓
   Build Project
       ↓
    WAR File
```

---

## Jenkins and Apache Tomcat Port Configuration

During the hands-on practice, I found that Jenkins was already using port **8080**.

Since Apache Tomcat also uses port **8080** by default, there was a port conflict.

To resolve this, I changed the Tomcat port from:

```text
8080
```

to:

```text
8081
```

### Why?

Two services cannot normally listen on the same IP address and TCP port at the same time.

After changing the Tomcat port, Jenkins and Tomcat could run on different ports.

```text
Jenkins  → 8080
Tomcat   → 8081
```

---

## Changing the Tomcat Port

Learned how to modify the Tomcat connector configuration.

The Tomcat configuration file is:

```text
conf/server.xml
```

The HTTP connector port was changed from:

```xml
<Connector port="8080" ... />
```

to:

```xml
<Connector port="8081" ... />
```

After changing the configuration, Tomcat was restarted.

The application could then be accessed using:

```text
http://<server-ip>:8081
```

---

## CI/CD Hands-on Demonstration

At the end of the learning sessions, I performed a complete CI/CD demonstration.

### CI/CD Architecture

```text
Git
 ↓
GitHub
 ↓
Jenkins
 ↓
Maven
 ↓
Apache Tomcat
 ↓
Hosted Application
```

### Process

1. Developer makes changes to the application.
2. Changes are committed using Git.
3. Code is pushed to GitHub.
4. GitHub webhook triggers Jenkins.
5. Jenkins pulls the latest code.
6. Jenkins starts the build process.
7. Maven builds the application.
8. Maven generates the application package.
9. Jenkins deploys the package to Apache Tomcat.
10. The application becomes available through the Tomcat server.

---

## CI vs CD

### Continuous Integration (CI)

CI focuses on automatically integrating and building code whenever developers make changes.

```text
GitHub → Jenkins → Build/Test
```

### Continuous Deployment (CD)

CD focuses on automatically deploying the successfully built application.

```text
Jenkins → Maven → Tomcat → Application
```

---

## Hands-on Practice

During Day 34–39, I practiced:

* Installing Jenkins
* Configuring Jenkins
* Creating Jenkins jobs
* Connecting Jenkins with GitHub
* Cloning Git repositories
* Configuring build steps
* Using Maven with Jenkins
* Understanding GitHub webhooks
* Changing the Apache Tomcat port
* Running Jenkins and Tomcat simultaneously
* Building the application automatically
* Deploying the application
* Successfully completing a CI/CD demonstration

---

## What I Learned

* What is Jenkins
* Why Jenkins is used
* How to install Jenkins
* How to configure Jenkins jobs
* How Jenkins integrates with GitHub
* What GitHub webhooks are
* How Jenkins clones Git repositories
* How to configure build steps
* How Maven works with Jenkins
* How to change the Tomcat port
* Why port conflicts occur
* CI and CD concepts
* Complete CI/CD workflow

---

## Key Takeaway

The biggest learning from Day 34–39 was understanding how different DevOps tools work together.

```text
Git
 ↓
GitHub
 ↓
Jenkins
 ↓
Maven
 ↓
Apache Tomcat
 ↓
Application
```

This hands-on demonstration helped me understand how code can move from a developer's machine to a hosted application through an automated CI/CD pipeline.


