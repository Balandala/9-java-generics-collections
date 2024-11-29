package com.example.task02;

import org.omg.CORBA_2_3.portable.OutputStream;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private final List<E> list = new ArrayList<>();

    public SavedList(File file) {
        this.file = file;
        readFromFile();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {

        E old = list.set(index, element);
        writeToFile();
        return old;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        E old = list.remove(index);
        writeToFile();
        return old;
    }

    private void writeToFile() {
        try (ObjectOutputStream stream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            stream.writeObject(list);
        } catch (IOException e) {
        }
    }

    private void readFromFile(){
        if (!file.exists())
            return;

        try (ObjectInputStream stream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            list.removeAll(list);
            List<E> temp = (List<E>)stream.readObject();
            list.addAll(temp);

        }
        catch (IOException  | ClassNotFoundException e) {
        }
    }
}
