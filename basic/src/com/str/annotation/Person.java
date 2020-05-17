package com.str.annotation;

public class Person {
    @Range(min=1, max=10)
    public String name;

    @Range(max=10)
    public String city;

    @Range(min=0, max=150)
    public int age;

    public Person(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public static void main(String[] args) {
        RangeCheck rc = new RangeCheck();
        Person p1 = new Person("cujunming", "beijing", 20);
        Person p2 = new Person("Bob", "Shanghai", 30);
        Person p3 = new Person("Jerry", "Newyork", 200);

        for (Person person : new Person[] {p1, p2, p3}) {
            try {
                rc.check(person);
                System.out.println(person + " checked ok.");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
