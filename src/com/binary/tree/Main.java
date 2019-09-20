package com.binary.tree;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();
        BinarySearchTree tree = new BinarySearchTree();

        tree.remove(20);

        tree.insert(50);

        for (int i = 0; i < 10; i++){
            tree.insert(Math.abs(rand.nextInt())%100);
        }

        tree.insert(16);
        tree.viewTree();
        tree.remove(50);
        System.out.println("");
        tree.viewTree();

        }
    }
}
