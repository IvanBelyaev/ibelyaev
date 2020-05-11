package ru.job4j.post;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * PostServiceTest.
 *
 * @author Ivan Belyaev
 * @version 1.0
 * @since 11.05.2020
 */
public class PostServiceTest {
    /**
     * Test.
     */
    @Test
    public void whenAddThreeUsersWithEmailsThenTwoUsersWithTheSameEmails() {
        Map<String, List<String>> input = new HashMap<>();
        input.put("user1", Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        input.put("user2", Arrays.asList("foo@gmail.com", "ups@pisem.net"));
        input.put("user3", Arrays.asList("xyz@pisem.net", "vasya@pupkin.com"));
        input.put("user4", Arrays.asList("ups@pisem.net", "aaa@bbb.ru"));
        input.put("user5", Arrays.asList("xyz@pisem.net"));

        PostService postService = new PostService();
        Map<String, List<String>> output = postService.uniteEmails(input);

        assertTrue(output.values().contains(
                Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"))
        );
        assertTrue(output.values().contains(
                Arrays.asList("xyz@pisem.net", "vasya@pupkin.com"))
        );
        assertThat(output.size(), is(2));
    }
}
