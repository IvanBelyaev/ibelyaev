package ru.job4j.tracker;

/**
 * Class for editing applications.
 */
class EditItem implements UserAction {
    @Override
    public int key() {
        return 2;
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Enter id: ");
        Item item = tracker.findById(id);
        if (item == null) {
            System.out.println("This id does not exist.");
        } else {
            System.out.printf("id: %s, name: %s, description: %s, created date: %d\n",
                    item.getId(), item.getName(), item.getDesctiption(), item.getCreate());
            item.setName(input.ask("name: "));
            item.setDesctiption(input.ask("description: "));
        }
    }
}


/**
 * MenuTracker.
 * @author Ivan Belyaev
 * @since 26.09.2017
 * @version 1.0
 */
public class MenuTracker {
    /** The input / output system. */
    private Input input;
    /** Storage applications. */
    private Tracker tracker;
    /** A repository of all the action. */
    private UserAction[] actions = new UserAction[6];

    /**
     * The constructor creates the object MenuTracker.
     * @param input - the input / output system.
     * @param tracker - storage applications.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Method fills the vault of the action.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindItemById();
        this.actions[5] = this.new FindItemByName();
    }

    /**
     * Method displays the main menu.
     */
    public void show() {
        System.out.println();
        for (UserAction action : this.actions) {
            System.out.println(action.info());
        }
        System.out.println("6. Exit Program");
    }

    /**
     * The method performs the selected action.
     * @param key - the number of the menu item.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Class to add applications.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("name: ");
            String description = input.ask("description: ");
            tracker.add(new Item(name, description, System.currentTimeMillis()));
            System.out.println("Item added.");
        }
    }

    /**
     * Class to display all applications.
     */
    private static class ShowAllItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] allItems = tracker.findAll();
            for (Item item : allItems) {
                System.out.printf("id: %s, name: %s, description: %s, created date: %d\n",
                        item.getId(), item.getName(), item.getDesctiption(), item.getCreate());
            }
        }
    }

    /**
     * Class to remove the application.
     */
    private class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);
            if (item == null) {
                System.out.println("This id does not exist.");
            } else {
                tracker.delete(item);
                System.out.println("Item deleted.");
            }
        }
    }

    /**
     * Class to search for the application ID.
     */
    private class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter id: ");
            Item item = tracker.findById(id);

            if (item == null) {
                System.out.println("This id does not exist.");
            } else {
                System.out.printf("id: %s, name: %s, description: %s, created date: %d\n",
                        item.getId(), item.getName(), item.getDesctiption(), item.getCreate());

                boolean flag = true;
                while (flag) {
                    this.showMenuForItem();
                    String answer = input.ask("Select the menu item: ");
                    switch (answer) {
                        case "0":
                            this.addComment(item);
                            break;
                        case "1":
                            this.showAllComments(item);
                            break;
                        case "2":
                            flag = false;
                            break;
                        default:
                            System.out.println("Error. Try again.");
                    }
                }
            }
        }

        /**
         * The method displays a menu to review the application.
         */
        private void showMenuForItem() {
            System.out.println();
            System.out.println("0. Add new comment");
            System.out.println("1. Show all comments");
            System.out.println("2. Exit menu");
        }

        /**
         * The method adds a request.
         * @param item - application.
         */
        private void addComment(Item item) {
            item.addComment(input.ask("comment: "));
        }

        /**
         * Method displays all applications.
         * @param item - appllication.
         */
        private void showAllComments(Item item) {
            for (String comment : item.getComments()) {
                System.out.println(comment);
            }
        }
    }

    /**
     * Class to search for the application name.
     */
    private class FindItemByName implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("name: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                System.out.printf("id: %s, name: %s, description: %s, created date: %d\n",
                        item.getId(), item.getName(), item.getDesctiption(), item.getCreate());
            }
        }
    }
}
