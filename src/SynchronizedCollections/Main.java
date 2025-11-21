package SynchronizedCollections;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {


    static void main() {

        NameList nameList = new NameList();
        nameList.add("first");
        class myThread extends Thread{
            @Override
            public void run() {
                System.out.println(nameList.removeFirst());
            }
        }
        myThread mythread = new myThread();
        mythread.setName("one");
        mythread.start();
        new myThread().start();
    }

    static class NameList{

        private List list = Collections.synchronizedList(new ArrayList<>());

        public synchronized void add(String name){
            list.add(name);
        }

        public synchronized String removeFirst(){
            if(list.size() > 0){
                if(Thread.currentThread().getName().equals("one")){
                    Thread.yield();
                }
                return list.removeFirst().toString();
            }
            return null;
        }
    }
}
