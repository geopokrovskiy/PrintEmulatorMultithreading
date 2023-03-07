# PrintEmulatorMultithreading
Technical test for the Central Bank of Russia.

A program that emulates the operation of the Document Print Manager.

1. The program must be written using Java 8 or higher.
2. Print Manager can handle multiple types of documents (3-5 types).
3. Each document type must have unique details: print duration, document type name, paper size.
4. The dispatcher places an unlimited number of documents in the print queue. In this case, each document can be processed only if no other document is being processed at the same time, the processing time of each document is equal to the duration of printing this document.
5. The dispatcher must have the following methods:
* Stop dispatcher. Printing of documents in the queue is cancelled. The output should be a list of unprinted documents.
* Accept document for printing. The method must not block program execution.
* Cancel printing of the received document if it has not already been printed.
* Get a sorted list of printed documents. The list can be sorted by choice: by printing order, by document type, by printing time, by paper size.
* Calculate the average printing time of printed documents
