# **Calibre-Web Automated Testing Repository**

## **Overview**
This repository contains the automated tests for validating the functionality, performance, and usability of Calibre-Web's Book Management System. The focus is on ensuring the reliability of the "Book Details" window and the search functionality.

## **Testing Strategy and Objectives**
The purpose of the tests is to verify the following:
1. **Book Details Window**:
   - Accurate display of book metadata such as cover photo, name, author, published date, publisher (if available), and description.
   - Functionality of the "Read" checkbox, "Download" button, and "Archive" checkbox.
2. **Search Functionality**:
   - Correct implementation of search by title, author, and publisher.
   - Case-insensitive and partial match support.
3. **Data Integrity**:
   - Correct categorization of books into "Read," "Unread," and "Archived Books" sections.
4. **Usability**:
   - Seamless user experience with no issues when interacting with the system.

The testing approach will involve:
- Designing and reviewing test cases based on requirements.
- Executing test cases and logging defects.
- Retesting after defect resolution and performing regression testing.

## **Setup**

### **Installation**
1. **Create and Activate a Virtual Environment**:
   - Create a virtual environment to isolate your installation and avoid dependency conflicts.
   - Activate the virtual environment.

2. **Install Calibre-Web**:
   - Install Calibre-Web using the Python package manager.

3. **Start Calibre-Web**:
   - Start the Calibre-Web application.

4. **Access the Application**:
   - Open your browser and navigate to [http://localhost:8083](http://localhost:8083).
   - Use the default login credentials:
     - **Username**: `admin`
     - **Password**: `admin123`.

---

## **How to Run Tests**
Follow these steps to execute the automated tests:

1. **Clone the Repository**:
   - Clone the repository to your local machine.
   - Navigate into the repository directory.

2. **Install Dependencies**:
   - Ensure all dependencies are installed by running the required installation command.

3. **Run the Tests**:
   - Execute the test suite to run all tests.

4. **View Test Results**:
   - Test results will be displayed in the terminal.

---

## **Test Coverage**
This repository includes tests for the following features:
- **Book Details Window**: Verification of the correct display of book metadata and functionality of related features (e.g., "Read" checkbox, "Download" button).
- **Search Functionality**: Ensures that searches by title, author, and publisher work as expected, including case-insensitive and partial matches.

---

## **Completion Criteria**
The testing process will be considered complete when:
1. All high-priority test cases have passed successfully.
2. All logged defects have been resolved and retested.
3. Regression tests confirm that no new defects were introduced.


