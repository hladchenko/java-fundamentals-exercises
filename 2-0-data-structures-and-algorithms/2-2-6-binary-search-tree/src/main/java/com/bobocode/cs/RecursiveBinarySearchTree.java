package com.bobocode.cs;

import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is
 * based on a linked nodes and recursion. A tree node is represented as a nested class {@link Node}.
 * It holds an element (a value) and two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a
 * href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

  private Node<T> head;

  private int size = 0;

  public static void main(String[] args) {
    RecursiveBinarySearchTree<Integer> recursiveBinarySearchTree = RecursiveBinarySearchTree.of(10, 9, 11, 8, 12, 7);

    recursiveBinarySearchTree.inOrderTraversal(System.out::println);
  }

  public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
    RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
    for (T element : elements) {
      if (tree.head == null) {
        tree.head = new Node<>(element);
        tree.size++;
      } else {
        tree.insert(tree.head, element);
      }
    }
    return tree;
  }

  private boolean insert(Node<T> node, T element) {
    if (node != null) {
      int compared = element.compareTo(node.element);
      if (compared > 0) {
        if (node.right == null) {
          node.right = new Node<>(element);
          size++;
          return true;
        } else {
          return insert(node.right, element);
        }
      } else if (compared < 0) {
        if (node.left == null) {
          node.left = new Node<>(element);
          size++;
          return true;
        } else {
          return insert(node.left, element);
        }
      }
    }
    return false;
  }

  @Override
  public boolean insert(T element) {
    if (head == null) {
      head = new Node<>(element);
      size++;
      return true;
    } else {
      return insert(head, element);
    }
  }

  @Override
  public boolean contains(T element) {
    if (element == null) {
      throw new NullPointerException();
    }
    return contains(head, element);
  }

  private boolean contains(Node<T> node, T element) {
    if (node != null) {
      int compared = element.compareTo(node.element);
      if (compared == 0) {
        return true;
      } else if (compared < 0) {
        return contains(node.left, element);
      } else {
        return contains(node.right, element);
      }
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public int depth() {
    return depth(head, 0);
  }

  private int depth(Node<T> node, int depth) {
    if (node != null) {
      int leftDepth = depth, rightDepth = depth;
      if (node.left != null) {
        leftDepth = depth(node.left, depth + 1);
      }
      if (node.right != null) {
        rightDepth = depth(node.right, depth + 1);
      }
      return Math.max(leftDepth, rightDepth);
    }
    return depth;
  }

  @Override
  public void inOrderTraversal(Consumer<T> consumer) {
    if (head == null || consumer == null) throw new NullPointerException();
    inOrderTraversal(head, consumer);
  }

  private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
    if (node != null) {

      if (node.left != null) {
        inOrderTraversal(node.left, consumer);
      }

      consumer.accept(node.element);

      if (node.right != null) {
        inOrderTraversal(node.right, consumer);
      }

    }
  }

  static class Node<E> {

    E element;
    Node<E> left;
    Node<E> right;

    public Node(E element) {
      this.element = element;
    }
  }
}
