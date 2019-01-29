package ru.job4j.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Analize.
 * @author Ivan Belyaev
 * @since 28.01.2019
 * @version 1.0
 */
class Analize {
    /**
     * The method returns statistics about the change in the collection.
     * Number of users added. Number of users changed. The number of deleted users.
     * @param previous - initial data.
     * @param current - changed data.
     * @return statistics about the change in the collection.
     */
    Info diff(List<User> previous, List<User> current) {
        Map<User, String> previousMap = new HashMap<>();
        for (User user : previous) {
            previousMap.put(user, user.name);
        }
        Map<User, String> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user, user.name);
        }

        int newUsers = 0;
        int modifiedUsers = 0;
        int deletedUsers = 0;
        for (Map.Entry<User, String> userStringEntry : previousMap.entrySet()) {
            User user = userStringEntry.getKey();
            if (currentMap.containsKey(user)) {
                if (!user.name.equals(currentMap.get(user))) {
                    modifiedUsers++;
                }
            } else {
                deletedUsers++;
            }
        }
        for (Map.Entry<User, String> userStringEntry : currentMap.entrySet()) {
            if (!previousMap.containsKey(userStringEntry.getKey())) {
                newUsers++;
            }
        }

        return new Info(newUsers, modifiedUsers, deletedUsers);
    }

    /**
     * User.
     */
    public static class User {
        /** User id. */
        private int id;
        /** User name. */
        private String name;

        /**
         * The constructor creates the object User.
         * @param id - user id.
         * @param name - user name.
         */
        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "User{"
                    + "id=" + id
                    + ", name='" + name + '\''
                    + '}';
        }
    }

    /**
     * Info.
     */
    public static class Info {
        /** Number of new users. */
        private int newUsers;
        /** Number of modified users. */
        private int modifiedUsers;
        /** Number of deleted users. */
        private int deletedUsers;

        /**
         * The constructor creates the object Info.
         * @param newUsers - number of new users.
         * @param modifiedUsers - number of modified users.
         * @param deletedUsers - number of deleted users.
         */
        Info(int newUsers, int modifiedUsers, int deletedUsers) {
            this.newUsers = newUsers;
            this.modifiedUsers = modifiedUsers;
            this.deletedUsers = deletedUsers;
        }

        /**
         * The method returns number of new users.
         * @return number of new users.
         */
        public int getNewUsers() {
            return newUsers;
        }

        /**
         * The method returns number of modified users.
         * @return number of modified users.
         */
        public int getModifiedUsers() {
            return modifiedUsers;
        }

        /**
         * The method returns number of deleted users.
         * @return number of deleted users.
         */
        public int getDeletedUsers() {
            return deletedUsers;
        }
    }
}
