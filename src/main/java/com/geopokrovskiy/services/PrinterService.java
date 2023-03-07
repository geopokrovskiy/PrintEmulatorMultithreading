package com.geopokrovskiy.services;

import com.geopokrovskiy.model.AbstractDocument;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrinterService implements Runnable {

    private Queue<AbstractDocument> abstractDocumentQueue;

    private List<AbstractDocument> printedDocs;

    private volatile boolean shutDown;

    public PrinterService() {
        this.abstractDocumentQueue = new ConcurrentLinkedDeque<>();
        this.printedDocs = new CopyOnWriteArrayList<>();
    }

    public Queue<AbstractDocument> getAbstractDocumentQueue() {
        return abstractDocumentQueue;
    }

    public void setAbstractDocumentQueue(Queue<AbstractDocument> abstractDocumentQueue) {
        this.abstractDocumentQueue = abstractDocumentQueue;
    }

    public List<AbstractDocument> getPrintedDocs() {
        return printedDocs;
    }

    public void setPrintedDocs(List<AbstractDocument> printedDocs) {
        this.printedDocs = printedDocs;
    }

    public boolean isShutDown() {
        return shutDown;
    }

    public void setShutDown(boolean shutDown) {
        this.shutDown = shutDown;
    }

    @Override
    public void run() {
        try {
            while (!this.shutDown) {
                if (!this.abstractDocumentQueue.isEmpty()) {
                    AbstractDocument document = this.abstractDocumentQueue.poll();
                    System.out.println("A new " + document.getType() + " document is now being printed!");
                    Thread.sleep(document.getPrintDuration() * 1000);
                    System.out.println("The " + document.getType() + " document has just been printed!");
                    this.printedDocs.add(document);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("The manager has been stopped!");
        }
    }

    public synchronized void addToQueue(AbstractDocument document) {
        this.abstractDocumentQueue.add(document);
    }

    public synchronized void removeFromQueue(int index){
        AbstractDocument abstractDocument = this.abstractDocumentQueue.stream().toList().get(index - 1);
        this.abstractDocumentQueue.remove(abstractDocument);
    }
}
