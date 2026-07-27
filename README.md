# Selenium Java Automation Framework

## Overview

This project is a Selenium WebDriver automation framework developed using **Java**, **TestNG**, and **Maven** following the **Page Object Model (POM)** design pattern. The framework automates multiple UI scenarios ranging from basic to advanced interactions and demonstrates reusable framework architecture, reporting, synchronization, and parallel execution.

---

# Assignment Objectives

## 1. Manual Test Cases

Manual test cases have been documented in Excel covering various application scenarios.

The manual test suite includes:

- Login Module
- Employee CRUD Operations
- File Upload
- File Download
- Form Validation
- Search & Filter Operations

---

## 2. Automation Test Cases

A minimum of **10 automated test cases** have been implemented.

### Login Module

- Validate User Login
- Validate Invalid Credentials
- Validate Mandatory Field Validation

### Employee Module

- Add Employee
- Search Employee
- Update Employee Details
- Delete Employee
- Upload Employee Attachment
- Download Attachment
- Verify Downloaded File

### Leave Module

- Search Leave Records

---

# Website Covered

The framework automates the **OrangeHRM Demo Application**, which provides a variety of UI components including:

- Text Fields
- Numeric Fields
- Dropdowns
- Multi-select Dropdowns
- Buttons
- Tables
- Search Filters
- File Upload
- File Download
- Checkbox

---

# Framework Design

The framework follows the **Page Object Model (POM)** architecture.

### Structure

```
src
   - main
       - pages
       - locators
       - utils
       - driver
       - config
   - test
       - tests
       - listener
       - resources
```

### Framework Components

- BaseTest
- DriverFactory
- Page Classes
- Locator Classes
- WaitUtils
- ElementUtils
- ScreenShotUtils
- TestNG Listeners
- Extent Managers

---

# Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports

---

# Framework Features

- Page Object Model
- Explicit Waits
- Custom Loader Synchronization
- Parallel Execution
- Screenshot Capture on Failure
- File Upload Automation
- File Download Verification
- Reusable Utility Classes
- TestNG Listeners

---

# Build Management

The project uses **Maven**.

Dependencies are managed through:

```
pom.xml
```

Maven is also used for test execution.

```
mvn clean test
```

---

# Test Runner

The framework uses **TestNG** as the test runner.

Execution is controlled through:

```
testng.xml
```

Features:

- Parallel execution
- Configurable thread count
- Centralized suite execution

---

# Reporting

The framework integrates **Extent Reports**.

The report includes:

- Test Status
- Pass/Fail Summary
- Execution Time
- Failure Screenshots

Reports are generated automatically after execution.

---

# One-Click Execution

The complete automation suite can be executed using a single command:

```
mvn clean test

mvn clean test -Dtest=<TestClassName>
```

or for Parallel execution from testng.xml

```
mvn test -Dsurefire.suiteXmlFiles=testng.xml
```

No code changes are required before execution.

---
