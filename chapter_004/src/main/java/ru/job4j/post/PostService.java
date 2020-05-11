package ru.job4j.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PostService.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 30.04.2020
 */
public class PostService {
    /**
     * The method combines all users with common emails.
     * @param userToEmails list of users with their emails.
     * @return a list of users in which all user emails are combined.
     */
    public Map<String, List<String>> uniteEmails(Map<String, List<String>> userToEmails) {
        Map<String, List<String>> result = new HashMap<>();
        Map<String, String> emailToUser = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : userToEmails.entrySet()) {
            String user = entry.getKey();
            for (String email : entry.getValue()) {
                if (!emailToUser.containsKey(email)) {
                    emailToUser.put(email, user);
                    List<String> emailsOfUser = result.get(user);
                    if (emailsOfUser == null) {
                        emailsOfUser = new ArrayList<>();
                        emailsOfUser.add(email);
                        result.put(user, emailsOfUser);
                    } else {
                        emailsOfUser.add(email);
                    }
                } else {
                    user = emailToUser.get(email);
                }
            }
        }
        return result;
    }
}
