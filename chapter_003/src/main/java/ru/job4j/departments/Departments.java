package ru.job4j.departments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Departments.
 * @author Ivan Belyaev
 * @since 23.04.2018
 * @version 1.0
 */
public class Departments {
    /**
     * The method forms a complete departmental structure and returns it sorted in ascending order.
     * @param givenDepartments - departments that are given (perhaps not all).
     * @return returns a complete list of departments sorted in ascending order.
     */
    public static List<String> ascendingSort(List<String> givenDepartments) {
        Set<String> allDepartments = addMissingDepartments(givenDepartments);

        Set<String> sortedDepartments = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;

                String[] array1 = o1.split("\\\\");
                String[] array2 = o2.split("\\\\");

                int i = 0;
                for (i = 0; i < array1.length && i < array2.length; i++) {
                    result = array1[i].compareTo(array2[i]);
                    if (result != 0) {
                        break;
                    }
                }

                if (result == 0 && i < array1.length) {
                    result = 1;
                }

                if (result == 0 && i < array2.length) {
                    result = -1;
                }

                return result;

            }
        });

        sortedDepartments.addAll(allDepartments);

        return new ArrayList<>(sortedDepartments);
    }

    /**
     * The method forms a complete departmental structure and returns it sorted in descending order.
     * @param givenDepartments - departments that are given (perhaps not all).
     * @return returns a complete list of departments sorted in descending order.
     */
    public static List<String> descendingSort(List<String> givenDepartments) {
        Set<String> allDepartments = addMissingDepartments(givenDepartments);

        Set<String> sortedDepartments = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;

                String[] array1 = o1.split("\\\\");
                String[] array2 = o2.split("\\\\");

                int i = 0;
                for (i = 0; i < array1.length && i < array2.length; i++) {
                    result = array2[i].compareTo(array1[i]);
                    if (result != 0) {
                        break;
                    }
                }

                if (result == 0 && i < array1.length) {
                    result = 1;
                }

                if (result == 0 && i < array2.length) {
                    result = -1;
                }

                return result;

            }
        });

        sortedDepartments.addAll(allDepartments);

        return new ArrayList<>(sortedDepartments);
    }

    /**
     * The method returns a complete set of departments.
     * @param givenDepartments - departments that are given (perhaps not all).
     * @return returns a complete set of departments.
     */
    private static Set<String> addMissingDepartments(List<String> givenDepartments) {
        Set<String> allDepartments = new HashSet<>();

        for (String branch : givenDepartments) {
            StringBuilder department = new StringBuilder();
            for (String codeDepartment : branch.split("\\\\")) {
                department.append(codeDepartment);
                allDepartments.add(department.toString());
                department.append("\\");
            }
        }

        return allDepartments;
    }
}