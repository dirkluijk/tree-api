# Tree API

A small Java library for creating tree structures.

## API Documentation
Find the JavaDoc documentation at http://dirkluijk.github.io/tree-api/.

![Travis](https://travis-ci.org/dirkluijk/tree-api.svg?branch=master)

## Features

* A generic ``Node`` interface with a ``AbstractNode`` and a ``NameNode`` sample implementation
* A ``Tree`` helper to store the nodes in and search for descendants
* A ``TreeIterator`` to iterate over a tree
* A ``NodeComparator`` to compare nodes and maintain the tree-based ordering inside a ``Tree``

## How to use

Creating your custom node:

```java
class Employee implements Node<Employee> {

    private Employee manager;
    private String name;
    private int salary;

    public Employee(String name) {
        this(name, null);
    }

    public Employee(String name, Employee manager) {
        this.name = name;
        this.manager = manager;
    }

    @Override
    public Employee getParent() {
        return manager;
    }

    @Override
    public void setParent(Employee parent) {
        this.manager = parent;
    }

    @Override
    public boolean isLeaf() {
        return false;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
```

Creating nodes:

```java
Employee dave = new Employee("Dave");
Employee john = new Employee("John", dave);
Employee charles = new Employee("Charles", dave);
```

Storing nodes in a tree, e.g. using the included ``NodeTree``:

```java
Tree<Employee> employees = new NodeTree<>();

employees.add(john);
employees.add(dave);
employees.add(charles);
```

The tree will be ordered automatically, using the parent-child structure. Siblings are ordered alphabetically by default (based on the ``toString`` implementation):

* Dave
* Charles
* John

The tree provides some interesting features:

```java
// will return a new tree containing only john and charles
employees.descendantsOf(dave);	

employees.descendantsCount(dave); // 2
employees.descendantsCount(charles); // 0

```

There is also a special ``TreeIterator`` available:

```java
TreeIterator it = employees.iterator();
while(it.hasNext() {
	Employee current = it.next();
	
	it.level(); // the level of the current node
	it.path();  // the path, e.g. "/dave/charles"
}
```
