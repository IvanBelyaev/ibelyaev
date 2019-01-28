package ru.job4j.statistics;

import java.util.List;
import java.util.Objects;

class Store {
    Info diff(List<User> previous, List<User> current) {
        int newUsers = 0;
        int modifiedUsers = 0;
        int notModifiedUsers = 0;
        int deletedUsers = 0;
        for (User user : previous) {
            if (current.contains(user)) {
                if (user.name.equals(current.get(current.indexOf(user)).name)) {
                    notModifiedUsers++;
                } else {
                    modifiedUsers++;
                }
            } else {
                deletedUsers++;
            }
        }
        for (User user : current) {
            if (!previous.contains(user)) {
                newUsers++;
            }
        }

        return new Info(newUsers, modifiedUsers, deletedUsers);
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
