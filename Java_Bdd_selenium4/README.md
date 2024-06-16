# Cucumber.framework
# commands to run cross browser way: 

# Default browser:
mvn test

# other ways:
mvn test -Dbrowser=firefox
mvn test -Dbrowser=chrome

# headless cross browser:
mvn test -Dbrowser=headless-chrome
mvn test -Dbrowser=headless-firefox


