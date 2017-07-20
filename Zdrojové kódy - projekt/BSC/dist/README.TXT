=== Getting Started ===

To run this application from the command line , try:
java -jar "...path...\BSC.jar"

for example (1): java -jar "C:\BSC.jar"
or
for example (2): cd C:\...path...\      and then enter     java -jar BSC.jar

After starting, if you want to load file, choose "Y" and enter the filename or path to the file:

for example: filename.txt		or          C:\...path...\filename.txt

If you want enter only the filename, file must be stored in same folder as running jar file, and user must run the jar file from this folder, too (example(2)).

=== Assumptions ===

If user enter a different option as is requested ('Y/N' or 'quit'), application show a warning message.
Requested options ('Y/N' or 'quit') can be entered uppercase or lowercase.
If user enter a non-existing filename, application show a warning message.
If data stored in file has invalid input format at some line, method show a warning message with index of this line.
If user enter record in invalid format (other than 3 uppercase letter and amount), method show a warning message.
If user haven't loaded data from file and haven't saved any record, no data will be shown to output. After he save first record, all amounts will be shown every one minute.
The application show exchange rate (USD) of this currencies: EUR, GBP, HKD, RMB.

