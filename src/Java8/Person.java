package Java8;


public class Person {
    static class Persona {

        private int age;
        private String name;

        Persona(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public String toString() {
            return name + age;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
    }
    }
}
