package Controlador;

import Modelo.*;
import usarExcepciones.EmpleadoNoEncontrado;
import usarExcepciones.SueldoSuperiorAMaximo;

import javax.imageio.IIOException;
import java.io.*;


public class Lista {
    static int counter = 0;

    public class Node {
        int indice;
        Empleado main;
        Node nextNode;
        Node prevNode;

        public Node(Empleado em) {
            main = em;
            this.indice = main.getNum_empleado();
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }

        public int getNumEmp() {
            return this.indice;
        }

        @Override
        public String toString() {
            return "" + main.toString();
        }
    }

    Node firstNode;
    Node lastNode;
    Node act;

    public Lista() {
    }

    public void add(Empleado em) {
        Node temp = new Node(em);
        counter++;
        if (firstNode == null) {
            temp.setNextNode(null);
            temp.setPrevNode(null);
            firstNode = temp;
            lastNode = temp;
            counter = 1;
        } else {
            lastNode.setNextNode(temp);
            temp.setPrevNode(lastNode);
            lastNode = temp;
            counter++;
        }
        act = temp;
    }

    public Node getAct() {
        return act;
    }

    public int getCounter() {
        return counter;
    }

    public void setAct(Node act) {
        this.act = act;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getNextNode() {
        return act.nextNode;
    }

    public void goFirst() {
        setAct(getFirstNode());
    }

    public void goLast() {
        setAct(getLastNode());
    }

    public void goNext() {
        setAct(getNextNode());
    }

    public void goPrev() {
        setAct(getPrevNode());
    }

    public void borrarNodo() {
        Node actual = getAct();
        Node prev = actual.getPrevNode();
        Node next = actual.getNextNode();
        counter--;
        if (isFirst() && isLast()) {
            setFirstNode(null);
            setLastNode(null);
        } else if (isFirst()) {
            next.setPrevNode(null);
            setFirstNode(next);
        } else if (isLast()) {
            prev.setNextNode(null);
            setLastNode(prev);
        } else {
            prev.setNextNode(next);
            next.setPrevNode(prev);
        }
    }

    public Node getPrevNode() {
        return act.prevNode;
    }

    public Node getLastNode() {
        return lastNode;
    }

    public void setFirstNode(Node n) {
        firstNode = n;
    }

    public void setLastNode(Node n) {
        lastNode = n;
    }

    public boolean isLast() {
        return getAct() == getLastNode();
    }

    public boolean isFirst() {
        return getAct() == getFirstNode();
    }

    public Node getElementListIndex(int x) {
        if (x <= counter) {
            this.setAct(getFirstNode());
            for (int i = 0; i < x; i++) {
                this.setAct(getNextNode());
            }
            return getAct();
        } else
            return null;
    }

    public boolean existe(int numEmp) {
        goFirst();
        boolean found = false;
        while (!isLast() && !found) {
            if (act.getNumEmp() == numEmp)
                found = true;
            else
                goNext();
        }
        if (isLast())
            if (act.getNumEmp() == numEmp)
                found = true;
        return found;
    }

    public Node goNumEmp(int numEmp) {
        goFirst();
        while (act.getNumEmp() != numEmp) {
            goNext();
        }
        return act;
    }

    public void intercambiar(int numEmp1, int numEmp2) throws EmpleadoNoEncontrado {
        if (!existe(numEmp1))
            throw new EmpleadoNoEncontrado(numEmp1);
        else if (!existe(numEmp2))
            throw new EmpleadoNoEncontrado(numEmp2);
        else {
            Node nod1 = goNumEmp(numEmp1);
            Node nod2 = goNumEmp(numEmp2);
            int nod1_index = nod1.indice;
            Empleado nod1_emp = nod1.main;
            nod1.indice = nod2.indice;
            nod1.main = nod2.main;
            nod2.indice = nod1_index;
            nod2.main = nod1_emp;
        }
    }

    public void sort() {
        Node actual;
        Node siguiente;
        boolean intercambio;
        do {
            intercambio = false;
            actual = getFirstNode();
            while (actual != null && actual.nextNode != null) {
                siguiente = actual.nextNode;
                if (actual.indice > siguiente.indice) {
                    try {
                        intercambiar(actual.indice, siguiente.indice);
                    } catch (EmpleadoNoEncontrado e) {
                        throw new RuntimeException(e);
                    }
                    intercambio = true;
                }
                actual = siguiente;
            }
        } while (intercambio);
    }

    public void escribirArchivo(String ruta) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
            goFirst();
            while (!isLast()) {
                oos.writeObject(act.main);
                goNext();
            }
            oos.writeObject(act.main);
            oos.close();
        } catch (Exception e) {
            System.out.println("No se puede guardar en este archivo");
        }
    }


    public void leerArchivo(String ruta) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            Object aux = ois.readObject();

            while (aux != null) {
                add((Empleado) aux);
                aux = ois.readObject();
            }
            ois.close();
        } catch (EOFException e) {
            System.out.println("Se termino de leer el archivo");
        } catch (Exception e) {
            System.out.println("No se puede leer en este archivo");
        }
    }

    public void imprimir() {
        goFirst();
        while (!isLast()) {
            System.out.println(getAct().toString());
            goNext();
        }
        System.out.println(getAct().toString());
    }

    public void crearAleatoriosDiez() throws SueldoSuperiorAMaximo {
        int numero = ((int) (Math.random() * 1001)) + 1000;
        
        for (int i = 0; i < 10; i++) {
            int op = (int) (Math.random() * 2);
            
            while (existe(numero)) {
                numero = ((int) (Math.random() * 1001)) + 1000;
            }

            if (op == 0) {
                Analista aux = new Analista(numero, "Juan", 2000, 2500, 0, 0);
                add(aux);
            } else {
                Programador aux = new Programador(numero, "Pedro", 2000, 2500, 0, "Java");
                add(aux);
            }
        }
    }
}
