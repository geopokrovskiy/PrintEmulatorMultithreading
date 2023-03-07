package com.geopokrovskiy.services;

import com.geopokrovskiy.factory.DocumentFactory;
import com.geopokrovskiy.model.AbstractDocument;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ManagerService implements Runnable {

    private Map<String, DocumentFactory> map;

    private Scanner scanner;

    private PrinterService printerService;

    public ManagerService(Map<String, DocumentFactory> map) {
        this.scanner = new Scanner(System.in);
        this.map = map;
        this.printerService = new PrinterService();
    }


    private synchronized void addNewDocument() {
        AbstractDocument document;
        while (true) {
            System.out.println("Which document would you like to print?");
            System.out.println("2 --- A2");
            System.out.println("3 --- A3");
            System.out.println("4 --- A4");
            int type = scanner.nextInt();
            String docType = "A" + type;
            DocumentFactory documentFactory = this.map.get(docType);
            if (documentFactory != null) {
                document = documentFactory.newInstance();
                this.printerService.addToQueue(document);
                break;
            }
        }
    }

    @Override
    public void run() {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(printerService);
            while (!this.printerService.isShutDown()) {
                System.out.println();
                System.out.println("Hello! Welcome to the printing manager. What do you want to do?");
                System.out.println("1 --- Add a new document to the line");
                System.out.println("2 --- Remove a document from the line");
                System.out.println("3 --- Calculate the average duration of treatment of printed documents");
                System.out.println("4 --- Get a sorted list of the printed documents");
                System.out.println("5 --- Shut down the manager");
                int option = this.scanner.nextInt();
                if (option == 1) {
                    this.addNewDocument();
                }
                if (option == 2) {
                    if (this.printerService.getAbstractDocumentQueue().isEmpty()) {
                        System.out.println("No documents in the line!");
                    } else {
                        System.out.println("WHICH DOCUMENT WOULD YOU LIKE TO REMOVE FROM THE LINE? ENTER A NUMBER FROM 1 to " +
                                this.printerService.getAbstractDocumentQueue().size());
                        int numberDocToRemove = scanner.nextInt();
                        if (numberDocToRemove > 0 && numberDocToRemove <= this.printerService.getAbstractDocumentQueue().size()) {
                            this.printerService.removeFromQueue(numberDocToRemove);
                        }
                    }
                }
                if (option == 3) {
                    if (this.printerService.getPrintedDocs().isEmpty()) {
                        System.out.println("Nothing has been printed yet!");
                    } else {
                        double time = (double) ((int) (this.printerService.getPrintedDocs().
                                stream().mapToDouble(AbstractDocument::getPrintDuration).
                                sum() /
                                this.printerService.getPrintedDocs().size() * 100)) / 100;
                        System.out.println("Average duration of treatment of printed documents is " + time + " s!");
                    }
                }
                if (option == 4) {
                    if (this.printerService.getPrintedDocs().isEmpty()) {
                        System.out.println("Nothing has been printed yet!");
                    } else {
                        System.out.println("HOW WOULD YOU LIKE TO SORT THE PRINTED DOCUMENTS?");
                        System.out.println("1 --- BY ORDER");
                        System.out.println("2 --- BY DOCUMENT TYPE");
                        System.out.println("3 --- BY DURATION OF TREATMENT");
                        System.out.println("4 --- BY PAPER SIZE");
                        int sortOption = this.scanner.nextInt();
                        switch (sortOption) {
                            case 1:
                                System.out.println(this.printerService.getPrintedDocs());
                                break;
                            case 2:
                                System.out.println(this.printerService.getPrintedDocs().
                                        stream().
                                        sorted(new Comparator<>() {
                                            @Override
                                            public int compare(AbstractDocument o1, AbstractDocument o2) {
                                                return o1.getType().compareTo(o2.getType());
                                            }
                                        }).collect(Collectors.toList()));
                                break;
                            case 3:
                                System.out.println(this.printerService.getPrintedDocs().
                                        stream().
                                        sorted(Comparator.comparingLong(AbstractDocument::getPrintDuration)).collect(Collectors.toList()));
                                break;
                            case 4:
                                System.out.println(this.printerService.getPrintedDocs().
                                        stream().
                                        sorted((Comparator.comparingInt(AbstractDocument::getArea))).collect(Collectors.toList()));
                                break;
                            default:
                                System.out.println("TRY AGAIN!");
                        }
                    }
                }
                if (option == 5) {
                    System.out.println(this.printerService.getAbstractDocumentQueue());
                    this.printerService.setShutDown(true);
                }
            }
        }
    }
}
