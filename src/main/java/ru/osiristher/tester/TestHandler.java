package ru.osiristher.tester;

import ru.osiristher.tester.interfaces.Handleable;

import java.util.LinkedList;

/**
 * Created by DesiresDesigner on 17.02.15.
 */
public class TestHandler implements Handleable {
    LinkedList<String> resultsList;

    public void setResultsList(LinkedList<String> resultsList){
        this.resultsList = resultsList;
    }

    @Override
    synchronized public void handle() {
        System.out.println(resultsList.removeLast());
    }
}
