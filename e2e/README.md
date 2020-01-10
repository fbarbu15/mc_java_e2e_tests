### Code is written in Java 8 and this is a maven project
* Java is not my primary programming language (that is python) and I didn't use it since several months ago so I may have some code/style mistakes
* As a design pattern I used a variant of Page Object Model that I've used in on different projects in the past
* I've used TestNG and Selenide (https://selenide.org/) as testing frameworks

### Requirement 1
* See /tests/src/test/resources/TestPlan/TestPlan.xlsx

### Requirement 2
* See /tests/src/test/java/tests/BudgetPageTests.java

### Requirement 3
* The last test in the list fails, see comment on TestPlan for test id 10
* "mvn clean test" starts the tests on Chrome by default
* added test logging to: /tests/Application.log

### HTML REPORTS:

* You need to install allure to generate the html reports: https://docs.qameta.io/allure/#_windows
* Make sure that e2e\allure-results is emtpy before running the tests
* Then run the tests and after the tests are done in the e2e folder run this: allure serve allure-results/
* This will start a web server with the allure report
* See a sample on how allure looks: /tests/src/test/resources/TestPlan/allure_sample.png

### MULTI-BROWSER SUPPORT:

Just run the test like this depending on what browser you need:

* mvn clean test -Dselenide.browser=chrome
* mvn clean test -Dselenide.browser=firefox 
* mvn clean test -Dselenide.browser=opera

(On IE the budget app doesn't work on my machine) 

### Cucumber

* IMO Cucumber increases complexity and ads some constrains without adding that much value. Here's a good article on this: https://www.jackkinsella.ie/articles/why-bother-with-cucumber-testing
* At the highest level (/tests/src/test/java/tests/BudgetPageTests.java) I've designed the test in gherkin language and I think this is enough in most of the cases
* Instead of cucumber I added docker for a Bonus Point :)

### DOCKER (Run test suite on multiple browsers in parallel):

* Change testNG runner in pom.xml to <suiteXmlFile>src/test/resources/runner/testngGrid.xml</suiteXmlFile>
* If docker is not running: "docker-machine start default"
* After docker-machine starts: "docker-compose up" will get the images and start the containers.
* You can see the containers up and running with Kitematic or you can event log in to them with VNC (pass is "secret") and see the browsers running.
* You can see the Selenium Grid here http://192.168.99.100:4444/grid/console
* I didn't manage to connect to localhost:8000 from inside the containers (probably a NW config that I'm missing) so I had to use the url: https://budget.modus.app/budget
* Run the tests normally: mvn clean test

### CI-JOB:

If this was a real project I would've added a CI pipeline using Jenkins or Gitlab that has: 
 * a job that builds and starts the app
 * a job that runs the tests
 * a job that generates reports and sends mails
 * a schedule that runs pipeline nightly/periodically
 * removes all need for manual stuff
