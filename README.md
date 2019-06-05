# task-estonia-insly
task-estonia-insly
General project
My automation project builds 3 parts.
First part is main automation scripts and "before login" scripts.
The second part is "after login" scripts and controls.
The third part is defining for test data’s. My project reading to test data’s from an excel file.

First part:
I’m importing required selenium libraries.
I’m defining Global variables for excel import
I’m defining Selenium driver variables
I’m defining Variables for "excel data"
I’m sharing Excel data’s:  between classes.
I'm defining test case's steps.
I’m opening chrome automation browser and I’m adding a wait for browser load.
I'm checking the home page from the "home page title" and the “H1 tag”.
I'm checking the loading of the "Administrator account details form".
I'm checking the loading of the "Terms and Conditions" link.
I'm checking the loading of the "Privacy Policy" link.
I'm checking the loading of the "Processing Personal Data" link.
I’m starting to file importing
I'm waiting for the loading of the "broker name" field.
I'm taking the first data as “company name” and adding “System current Time” to it for unique data. I'm sending it as “broker name”
I’m reading country from excel file, finding that country from the list and selecting it.
I'm checking “Web Address” field for filled or empty.
I’m reading “Company profile” from excel file, finding that Company profile from the list and selecting it.
I’m reading “Number of employees” from excel file, finding that “Number of employees” from the list and selecting it.
I’m reading “Describing Yourself” from excel file, finding that “Describing Yourself” from the list and selecting it.
I'm taking the sixth data as “work email” and adding “System current Time” to it for unique data. I'm sending it as “broker admin email”
I'm taking the seventh data as “manager name” sending it as “broker admin name”.
I’m looking and waiting “suggest a secure password” field and copying it.






